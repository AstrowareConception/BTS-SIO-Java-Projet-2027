package com.astroware.bts.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ApiClient {
    private final HttpClient http = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    private final String baseUrl;
    private final String authorization;

    public ApiClient(String baseUrl, String username, String password) {
        this.baseUrl = baseUrl.replaceAll("/+$", "");
        String token = Base64.getEncoder().encodeToString(
            (username + ":" + password).getBytes(StandardCharsets.UTF_8)
        );
        this.authorization = "Basic " + token;
    }

    public CompletableFuture<List<AssetDto>> list(String search) {
        String query = search == null || search.isBlank()
            ? ""
            : "?search=" + java.net.URLEncoder.encode(search, StandardCharsets.UTF_8);

        HttpRequest request = request("/api/assets" + query).GET().build();

        return http.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(this::requireSuccess)
            .thenApply(response -> {
                var type = new TypeToken<PageResponse<AssetDto>>() {}.getType();
                PageResponse<AssetDto> page = gson.fromJson(response.body(), type);
                return page.getContent();
            });
    }

    public CompletableFuture<Path> download(AssetDto asset) {
        HttpRequest request = request("/api/assets/" + asset.id() + "/content")
            .GET()
            .build();

        return http.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray())
            .thenApply(response -> {
                if (response.statusCode() < 200 || response.statusCode() >= 300) {
                    throw new IllegalStateException("HTTP " + response.statusCode());
                }
                try {
                    String suffix = extension(asset.originalFilename());
                    Path temp = Files.createTempFile("bts-media-", suffix);
                    Files.write(temp, response.body());
                    temp.toFile().deleteOnExit();
                    return temp;
                } catch (IOException e) {
                    throw new IllegalStateException("Impossible d'écrire le fichier temporaire.", e);
                }
            });
    }

    private HttpRequest.Builder request(String path) {
        return HttpRequest.newBuilder(URI.create(baseUrl + path))
            .header("Authorization", authorization)
            .header("Accept", "application/json");
    }

    private HttpResponse<String> requireSuccess(HttpResponse<String> response) {
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IllegalStateException(
                "HTTP " + response.statusCode() + " — " + response.body()
            );
        }
        return response;
    }

    private String extension(String filename) {
        if (filename == null) return ".bin";
        int dot = filename.lastIndexOf('.');
        return dot >= 0 ? filename.substring(dot) : ".bin";
    }
}
