package dev.archimedes.moviemango.moviecast.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.moviecast.application.dto.request.MovieCastFetchRequest;
import dev.archimedes.moviemango.moviecast.application.dto.response.MovieCastJointResponse;
import dev.archimedes.moviemango.moviecast.domain.MovieCastRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class MovieCastFetchUseCase {

  private final MovieCastRepository movieCastRepository;

  List<MovieCastJointResponse> execute(MovieCastFetchRequest request) {
    switch (request.type()) {
      case BY_NAME -> {
        return movieCastRepository.findCastsByMovieName(request.name());
      }
      case BY_ID ->  {
        return movieCastRepository.findCastsByMovieId(request.id());
      }
    }
    return List.of();
  }

}
