package com.astroware.bts.media.service;

import com.astroware.bts.media.domain.MediaAsset;
import com.astroware.bts.media.repository.MediaAssetRepository;
import com.astroware.bts.media.storage.StoredFile;
import com.astroware.bts.media.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MediaAssetServiceTest {
    @Mock MediaAssetRepository repository;
    @Mock StorageService storage;
    @Mock MultipartFile file;

    @Test
    void uploadStoresFileAndPersistsMetadata() {
        when(storage.store(file)).thenReturn(new StoredFile(
            "abc.mp3", "intro.mp3", "audio/mpeg", 1234, "deadbeef"
        ));
        when(repository.save(any(MediaAsset.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        MediaAssetService service = new MediaAssetService(repository, storage);

        var dto = service.upload("Intro", "Jingle", file);

        assertThat(dto.title()).isEqualTo("Intro");
        assertThat(dto.mimeType()).isEqualTo("audio/mpeg");
        assertThat(dto.sha256()).isEqualTo("deadbeef");

        ArgumentCaptor<MediaAsset> captor = ArgumentCaptor.forClass(MediaAsset.class);
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().getStorageKey()).isEqualTo("abc.mp3");
    }
}
