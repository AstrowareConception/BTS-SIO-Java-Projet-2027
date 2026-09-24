package com.astroware.bts.media.storage;

public record StoredFile(
    String storageKey,
    String originalFilename,
    String mimeType,
    long sizeBytes,
    String sha256
) {}
