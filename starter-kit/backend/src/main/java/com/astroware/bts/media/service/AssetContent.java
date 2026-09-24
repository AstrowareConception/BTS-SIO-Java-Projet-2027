package com.astroware.bts.media.service;

import org.springframework.core.io.Resource;

public record AssetContent(
    Resource resource,
    String mimeType,
    String originalFilename,
    long sizeBytes
) {}
