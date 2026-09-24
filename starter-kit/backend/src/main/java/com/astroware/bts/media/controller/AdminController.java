package com.astroware.bts.media.controller;

import com.astroware.bts.media.service.MediaAssetService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('CONTRIBUTOR','ADMIN')")
public class AdminController {
    private final MediaAssetService service;

    public AdminController(MediaAssetService service) {
        this.service = service;
    }

    @GetMapping
    public String index(
        @RequestParam(required = false) String search,
        Model model
    ) {
        model.addAttribute("page", service.list(search, PageRequest.of(0, 50)));
        model.addAttribute("search", search == null ? "" : search);
        return "admin";
    }

    @PostMapping("/assets")
    public String upload(
        @RequestParam String title,
        @RequestParam(required = false) String description,
        @RequestParam MultipartFile file
    ) {
        service.upload(title, description, file);
        return "redirect:/admin";
    }
}
