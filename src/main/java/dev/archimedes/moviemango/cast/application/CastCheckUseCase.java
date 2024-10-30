package dev.archimedes.moviemango.cast.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.cast.application.dto.request.CastCheckRequest;
import dev.archimedes.moviemango.cast.domain.CastRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CastCheckUseCase {

  private final CastRepository castRepository;

  boolean execute(CastCheckRequest request) {
    return castRepository.existsById(request.castId());
  }

}
