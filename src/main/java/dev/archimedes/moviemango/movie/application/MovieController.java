package dev.archimedes.moviemango.movie.application;

import dev.archimedes.moviemango.movie.application.dto.request.MovieCreateRequest;
import dev.archimedes.moviemango.movie.application.dto.request.MovieDeleteRequest;
import dev.archimedes.moviemango.movie.application.dto.request.MovieSearchRequest;
import dev.archimedes.moviemango.movie.application.dto.request.MovieUpdateRequest;
import dev.archimedes.moviemango.movie.application.dto.response.MovieActionResponse;
import dev.archimedes.moviemango.movie.domain.Movie;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {


  private final MovieCreateUseCase movieCreateUseCase;
  private final MovieUpdateUseCase movieUpdateUseCase;
  private final MovieDeleteUseCase movieDeleteUseCase;
  private final MovieSearchUseCase movieSearchUseCase;

  @PostMapping("/v1/movie")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Use this API to create a Movie record")
  MovieActionResponse create(@RequestBody MovieCreateRequest request) {
    Movie executed = movieCreateUseCase.execute(request);
    return new MovieActionResponse(executed);
  }

  @PatchMapping("/v1/movie")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Use this API to update an existing Movie record")
  MovieActionResponse update(@RequestBody MovieUpdateRequest request) {
    Movie executed = movieUpdateUseCase.execute(request);
    return new MovieActionResponse(executed);
  }

  @DeleteMapping("/v1/movie")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Use this API to delete an existing Movie record")
  void delete(@RequestBody MovieDeleteRequest request) {
    movieDeleteUseCase.execute(request);
  }

  @GetMapping("/v1/movie")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Use this API to fetch movie records based on filter, sorts and limit.")
  List<MovieActionResponse> fetch(@RequestBody MovieSearchRequest request) {
    return movieSearchUseCase.execute(request)
        .stream().map(MovieActionResponse::new)
        .toList();
  }

}
