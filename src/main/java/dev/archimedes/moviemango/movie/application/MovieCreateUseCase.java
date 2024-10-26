package dev.archimedes.moviemango.movie.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.movie.application.dto.request.MovieCreateRequest;
import dev.archimedes.moviemango.movie.domain.Movie;
import dev.archimedes.moviemango.movie.domain.MovieRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class MovieCreateUseCase {

  private final MovieRepository movieRepository;

  Movie execute(MovieCreateRequest request) {
    return movieRepository.save(
        new Movie(
            request.title(), request.description(), request.release(), request.duration(),
            request.genre(), request.director(), request.rating(), request.language(),
            request.origin(), null, null
        )
    );
  }

}
