package com.urlshortener.urlshortener.service;

import com.urlshortener.urlshortener.dto.UrlRequest;
import com.urlshortener.urlshortener.dto.UrlResponse;
import com.urlshortener.urlshortener.entity.Url;
import com.urlshortener.urlshortener.entity.User;
import com.urlshortener.urlshortener.repository.UrlRepository;
import com.urlshortener.urlshortener.repository.UserRepository;
import com.urlshortener.urlshortener.util.Base62Util;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final UserRepository userRepository;
    private final Base62Util base62Util;

    private static final String BASE_URL = "http://localhost:8080/";
    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UrlResponse createShortUrl(UrlRequest request) {
        User user = getCurrentUser();

        Url url = new Url();
        url.setOriginalUrl(request.getOriginalUrl());
        url.setCreatedAt(LocalDateTime.now().format(FORMATTER));
        url.setClickCount(0L);
        url.setActive(true);
        url.setUser(user);

        if (request.getExpiresAt() != null && !request.getExpiresAt().isEmpty()) {
            url.setExpiresAt(request.getExpiresAt());
        }

        Url saved = urlRepository.save(url);

        String shortCode = (request.getCustomAlias() != null && !request.getCustomAlias().isEmpty())
                ? request.getCustomAlias()
                : base62Util.encode(saved.getId());

        saved.setShortCode(shortCode);
        urlRepository.save(saved);

        return mapToResponse(saved);
    }

    @Cacheable(value = "urls", key = "#shortCode")
    public String getOriginalUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        if (!url.isActive()) {
            throw new RuntimeException("URL is inactive");
        }

        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);

        return url.getOriginalUrl();
    }

    public List<UrlResponse> getAllUrls() {
        User user = getCurrentUser();
        return urlRepository.findAll()
                .stream()
                .filter(url -> url.getUser() != null && url.getUser().getId().equals(user.getId()))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void deleteUrl(Long id) {
        urlRepository.deleteById(id);
    }

    private UrlResponse mapToResponse(Url url) {
        UrlResponse response = new UrlResponse();
        response.setId(url.getId());
        response.setOriginalUrl(url.getOriginalUrl());
        response.setShortCode(url.getShortCode());
        response.setShortUrl(BASE_URL + url.getShortCode());
        response.setCreatedAt(url.getCreatedAt());
        response.setExpiresAt(url.getExpiresAt());
        response.setClickCount(url.getClickCount());
        response.setActive(url.isActive());
        return response;
    }
}