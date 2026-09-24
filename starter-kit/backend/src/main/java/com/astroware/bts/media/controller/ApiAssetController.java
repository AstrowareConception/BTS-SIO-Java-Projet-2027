package com.astroware.bts.media.controller;

import com.astroware.bts.media.dto.AssetDto;
import com.astroware.bts.media.service.AssetContent;
import com.astroware.bts.media.service.MediaAssetService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/assets")
public class ApiAssetController {
    private final MediaAssetService service;

    public ApiAssetController(MediaAssetService service) {
        this.service = service;
    }

    @GetMapping
    public Page<AssetDto> list(
        @RequestParam(required = false) String search,
        Pageable pageable
    ) {
        return service.list(search, pageable);
    }

    @GetMapping("/{id}")
    public AssetDto get(@PathVariable long id) {
        return service.get(id);
    }

    @GetMapping("/{id}/content")
    public ResponseEntity<Resource> content(@PathVariable long id) {
        AssetContent content = service.content(id);

        MediaType type;
        try {
            type = MediaType.parseMediaType(content.mimeType());
        } catch (InvalidMediaTypeException ex) {
            type = MediaType.APPLICATION_OCTET_STREAM;
        }

        return ResponseEntity.ok()
            .contentType(type)
            .contentLength(content.sizeBytes())
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                ContentDisposition.inline()
                    .filename(content.originalFilename())
                    .build()
                    .toString()
            )
            .body(content.resource());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('CONTRIBUTOR','ADMIN')")
    public ResponseEntity<AssetDto> upload(
        @RequestParam String title,
        @RequestParam(required = false) String description,
        @RequestPart MultipartFile file
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.upload(title, description, file));
    }
}
