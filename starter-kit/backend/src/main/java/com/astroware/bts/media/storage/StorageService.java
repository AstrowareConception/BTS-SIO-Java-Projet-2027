package com.astroware.bts.media.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    StoredFile store(MultipartFile file);
    Resource load(String storageKey);
}
