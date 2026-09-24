package com.astroware.bts.media.dto;

import com.astroware.bts.media.domain.AssetStatus;
import java.time.Instant;

public record AssetDto(
    Long id,
    String title,
    String description,
    String originalFilename,
    String mimeType,
    long sizeBytes,
    String sha256,
    AssetStatus status,
    Instant createdAt
) {}
