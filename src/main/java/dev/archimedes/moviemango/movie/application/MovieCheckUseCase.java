package dev.archimedes.moviemango.movie.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.movie.application.dto.request.MovieCheckRequest;
import dev.archimedes.moviemango.movie.domain.MovieRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class MovieCheckUseCase {

  private final MovieRepository movieRepository;

  boolean execute(MovieCheckRequest request) {
    return movieRepository.existsById(request.movieId());
  }

}
