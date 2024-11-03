package dev.archimedes.moviemango.moviecast.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.moviecast.application.dto.request.MovieCastFetchRequest;
import dev.archimedes.moviemango.moviecast.application.dto.response.MovieCastJointResponse;
import dev.archimedes.moviemango.moviecast.domain.MovieCastRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

/// `MovieCastFetchUseCase` is a service use-case to fetch cast data by id or the cast name.
///
/// For match with name it prefers `name like :value` and based on that choose all the matching records and
/// fetch it.
///
/// For match with id, it will fetch the unique record associated with the id.
///
/// @see MovieCastRepository
/// @see dev.archimedes.moviemango.moviecast.domain.MovieCast
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
