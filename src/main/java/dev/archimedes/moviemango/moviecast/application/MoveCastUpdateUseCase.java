package dev.archimedes.moviemango.moviecast.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.moviecast.application.dto.request.MovieCastUpdateRequest;
import dev.archimedes.moviemango.moviecast.domain.MovieCast;
import dev.archimedes.moviemango.moviecast.domain.MovieCastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

/// **MoveCastUpdateUseCase** is use case service to update the cast-movie relation model
///
/// Perform validation on the request and ensure that a relation occur to update else
/// throws [IllegalStateException]
/// @see dev.archimedes.moviemango.moviecast.domain.MovieCastRepository
/// @see dev.archimedes.moviemango.moviecast.domain.MovieCast
@UseCase
@RequiredArgsConstructor
public class MoveCastUpdateUseCase {

  private final MovieCastRepository movieCastRepository;


  MovieCast execute(MovieCastUpdateRequest request) {
    Assert.state(
        movieCastRepository.existsByIdAndCastIdAndMovieId(request.id(), request.castId(), request.movieId()),
        "No movie-cast relation exist to update, create one to start with."
    );

    MovieCast movieCast = new MovieCast(request.castId(), request.movieId(), request.role(), null, null);
    movieCast.setId(request.id());
    return movieCastRepository.save(
        movieCast
    );
  }

}
