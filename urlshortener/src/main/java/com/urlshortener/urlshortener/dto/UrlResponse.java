package com.urlshortener.urlshortener.dto;

import lombok.Data;

@Data
public class UrlResponse {

    private Long id;
    private String originalUrl;
    private String shortCode;
    private String shortUrl;
    private String createdAt;
    private String expiresAt;
    private Long clickCount;
    private boolean active;
}