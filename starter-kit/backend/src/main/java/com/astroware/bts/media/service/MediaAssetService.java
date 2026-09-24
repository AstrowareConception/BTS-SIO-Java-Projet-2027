package com.astroware.bts.media.service;

import com.astroware.bts.media.domain.MediaAsset;
import com.astroware.bts.media.dto.AssetDto;
import com.astroware.bts.media.repository.MediaAssetRepository;
import com.astroware.bts.media.storage.StoredFile;
import com.astroware.bts.media.storage.StorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaAssetService {
    private final MediaAssetRepository repository;
    private final StorageService storage;

    public MediaAssetService(MediaAssetRepository repository, StorageService storage) {
        this.repository = repository;
        this.storage = storage;
    }

    @Transactional(readOnly = true)
    public Page<AssetDto> list(String search, Pageable pageable) {
        Page<MediaAsset> page = (search == null || search.isBlank())
            ? repository.findAll(pageable)
            : repository.findByTitleContainingIgnoreCase(search.trim(), pageable);

        return page.map(this::toDto);
    }

    @Transactional(readOnly = true)
    public AssetDto get(long id) {
        return toDto(findEntity(id));
    }

    @Transactional
    public AssetDto upload(String title, String description, MultipartFile file) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Le titre est obligatoire.");
        }

        StoredFile stored = storage.store(file);

        MediaAsset asset = new MediaAsset();
        asset.setTitle(title.trim());
        asset.setDescription(description == null ? "" : description.trim());
        asset.setOriginalFilename(stored.originalFilename());
        asset.setStorageKey(stored.storageKey());
        asset.setMimeType(stored.mimeType());
        asset.setSizeBytes(stored.sizeBytes());
        asset.setSha256(stored.sha256());

        return toDto(repository.save(asset));
    }

    @Transactional(readOnly = true)
    public AssetContent content(long id) {
        MediaAsset asset = findEntity(id);
        return new AssetContent(
            storage.load(asset.getStorageKey()),
            asset.getMimeType(),
            asset.getOriginalFilename(),
            asset.getSizeBytes()
        );
    }

    private MediaAsset findEntity(long id) {
        return repository.findById(id)
            .orElseThrow(() -> new AssetNotFoundException(id));
    }

    private AssetDto toDto(MediaAsset asset) {
        return new AssetDto(
            asset.getId(),
            asset.getTitle(),
            asset.getDescription(),
            asset.getOriginalFilename(),
            asset.getMimeType(),
            asset.getSizeBytes(),
            asset.getSha256(),
            asset.getStatus(),
            asset.getCreatedAt()
        );
    }
}
