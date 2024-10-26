package dev.archimedes.moviemango.movie.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.movie.application.dto.request.MovieSearchRequest;
import dev.archimedes.moviemango.movie.domain.Movie;
import dev.archimedes.moviemango.movie.domain.MovieRepository;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UseCase
@RequiredArgsConstructor
public class MovieSearchUseCase {


  private final MovieRepository movieRepository;

  List<Movie> execute(MovieSearchRequest request) {

    switch (request.fetchType()) {
      case ALL -> {
        return movieRepository.findAllByLimit(request.limit());
      }
      case TITLE -> {
        return movieRepository.findAllWhereTitleEqualAndLimit(request.title(), request.limit());
      }
      case FILTERED -> {

        Set<Movie> movies = new HashSet<>();

        request.filters()
            .forEach(unit -> movies.addAll(movieRepository.findAllByFilter(unit)));

        return movies.stream()
            .toList();
      }
      case CATEGORICAL -> {
        return movieRepository.findAllByFilter(request.category());
      }
    }
    return null;

  }

}
