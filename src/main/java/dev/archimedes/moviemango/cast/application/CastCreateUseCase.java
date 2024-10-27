package dev.archimedes.moviemango.cast.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.cast.application.dto.request.CastCreateRequest;
import dev.archimedes.moviemango.cast.domain.Cast;
import dev.archimedes.moviemango.cast.domain.CastRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CastCreateUseCase {

  private final CastRepository castRepository;

  Cast execute(CastCreateRequest request) {
    return castRepository.save(
        new Cast(
            request.name(), request.age(), request.dob(), request.gender(),
            request.country(), null, null
        )
    );
  }
}
