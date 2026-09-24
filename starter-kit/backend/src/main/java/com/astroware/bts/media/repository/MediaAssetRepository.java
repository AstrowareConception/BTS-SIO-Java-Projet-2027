package com.astroware.bts.media.repository;

import com.astroware.bts.media.domain.MediaAsset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaAssetRepository extends JpaRepository<MediaAsset, Long> {
    Page<MediaAsset> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
