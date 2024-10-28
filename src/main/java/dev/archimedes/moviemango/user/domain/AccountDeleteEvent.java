package dev.archimedes.moviemango.user.domain;

import org.springframework.modulith.PackageInfo;

@PackageInfo
public record AccountDeleteEvent(
    Long id
) {
}
