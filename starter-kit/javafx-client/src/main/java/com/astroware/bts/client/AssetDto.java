package com.astroware.bts.client;

public record AssetDto(
    Long id,
    String title,
    String description,
    String originalFilename,
    String mimeType,
    long sizeBytes,
    String sha256,
    String status,
    String createdAt
) {
    @Override
    public String toString() {
        return title + " — " + mimeType;
    }
}
