package dev.archimedes.moviemango.movie.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.movie.application.dto.request.MovieUpdateRequest;
import dev.archimedes.moviemango.movie.domain.Movie;
import dev.archimedes.moviemango.movie.domain.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

@UseCase
@RequiredArgsConstructor
public class MovieUpdateUseCase {

  private final MovieRepository movieRepository;

  Movie execute(MovieUpdateRequest request) {

    Assert.state(
        movieRepository.existsById(request.id()),
        "Movie must exist for given 'ID' to perform update. Id: " + request.id()
    );

    Movie movie = new Movie(
        request.title(), request.description(), request.release(), request.duration(),
        request.genre(), request.director(), request.rating(), request.language(),
        request.origin(), null, null
    );
    movie.setId(request.id());

    return movieRepository.save(movie);
  }

}
