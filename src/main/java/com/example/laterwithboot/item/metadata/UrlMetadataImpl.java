package com.example.laterwithboot.item.metadata;

import lombok.Builder;

import java.time.Instant;

@lombok.Value
@Builder(toBuilder = true)
public class UrlMetadataImpl implements UrlMetadata {

    String normalUrl;
    String resolvedUrl;
    String mimeType;
    String title;
    boolean hasImage;
    boolean hasVideo;
    Instant dateResolved;
}
