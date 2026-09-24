package com.astroware.bts.media.service;

public class AssetNotFoundException extends RuntimeException {
    public AssetNotFoundException(long id) {
        super("Média introuvable : " + id);
    }
}
