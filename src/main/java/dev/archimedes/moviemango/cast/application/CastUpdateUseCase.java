package dev.archimedes.moviemango.cast.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.cast.application.dto.request.CastUpdateRequest;
import dev.archimedes.moviemango.cast.domain.Cast;
import dev.archimedes.moviemango.cast.domain.CastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

@UseCase
@RequiredArgsConstructor
public class CastUpdateUseCase {

  private final CastRepository castRepository;

  Cast execute(CastUpdateRequest request) {
    Assert.state(
        castRepository.existsById(request.id()),
        "Cast must exist for give 'ID' to perform update. Id: " + request.id()

    );

    Cast cast = new Cast(
        request.name(), request.age(), request.dob(), request.gender(),
        request.country(), null, null
    );
    cast.setId(request.id());
    return castRepository.save(
        cast
    );
  }

}
