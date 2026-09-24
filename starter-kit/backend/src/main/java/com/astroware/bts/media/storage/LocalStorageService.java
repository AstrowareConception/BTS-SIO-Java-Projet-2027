package com.astroware.bts.media.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.UUID;

@Service
public class LocalStorageService implements StorageService {
    private final Path root;

    public LocalStorageService(@Value("${app.storage.root}") String root) {
        try {
            this.root = Paths.get(root).toAbsolutePath().normalize();
            Files.createDirectories(this.root);
        } catch (IOException e) {
            throw new IllegalStateException("Impossible d'initialiser le stockage", e);
        }
    }

    @Override
    public StoredFile store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Le fichier est vide.");
        }

        String original = StringUtils.cleanPath(
            file.getOriginalFilename() == null ? "file.bin" : file.getOriginalFilename()
        );

        String extension = "";
        int dot = original.lastIndexOf('.');
        if (dot >= 0 && dot < original.length() - 1) {
            extension = original.substring(dot).toLowerCase();
        }

        String key = UUID.randomUUID() + extension;
        Path target = root.resolve(key).normalize();

        if (!target.startsWith(root)) {
            throw new IllegalArgumentException("Chemin de stockage invalide.");
        }

        try {
            byte[] bytes = file.getBytes();
            Files.write(target, bytes, StandardOpenOption.CREATE_NEW);
            return new StoredFile(
                key,
                original,
                file.getContentType() == null ? "application/octet-stream" : file.getContentType(),
                bytes.length,
                sha256(bytes)
            );
        } catch (IOException e) {
            throw new IllegalStateException("Impossible de stocker le fichier.", e);
        }
    }

    @Override
    public Resource load(String storageKey) {
        try {
            Path file = root.resolve(storageKey).normalize();
            if (!file.startsWith(root)) {
                throw new IllegalArgumentException("Chemin de stockage invalide.");
            }
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new IllegalStateException("Fichier introuvable.");
            }
            return resource;
        } catch (IOException e) {
            throw new IllegalStateException("Impossible de lire le fichier.", e);
        }
    }

    private String sha256(byte[] bytes) {
        try {
            return HexFormat.of().formatHex(
                MessageDigest.getInstance("SHA-256").digest(bytes)
            );
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 indisponible.", e);
        }
    }
}
