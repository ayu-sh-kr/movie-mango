package dev.archimedes.moviemango.movie.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.movie.application.dto.request.MovieDeleteRequest;
import dev.archimedes.moviemango.movie.domain.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

@UseCase
@RequiredArgsConstructor
public class MovieDeleteUseCase {


  private final MovieRepository movieRepository;

  void execute(MovieDeleteRequest request) {

    Assert.state(
        movieRepository.existsById(request.id()),
        "Movie must exist with given 'ID' for delete to perform. Id: " + request.id()
    );

    movieRepository.deleteById(request.id());
  }

}
