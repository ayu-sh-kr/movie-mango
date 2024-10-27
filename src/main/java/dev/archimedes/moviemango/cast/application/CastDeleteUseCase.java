package dev.archimedes.moviemango.cast.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.cast.application.dto.request.CastDeleteRequest;
import dev.archimedes.moviemango.cast.domain.CastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

@UseCase
@RequiredArgsConstructor
public class CastDeleteUseCase {


  private final CastRepository castRepository;

  void execute(CastDeleteRequest request) {
    Assert.state(
        castRepository.existsById(request.id()),
        "Cast must exists for the given ID to perform delete. Id: " + request.id()
    );

    castRepository.deleteById(request.id());
  }

}
