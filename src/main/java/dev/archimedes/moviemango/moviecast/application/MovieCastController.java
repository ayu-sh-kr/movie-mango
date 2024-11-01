package dev.archimedes.moviemango.moviecast.application;

import dev.archimedes.moviemango.moviecast.application.dto.request.MovieCastCreateRequest;
import dev.archimedes.moviemango.moviecast.application.dto.request.MovieCastFetchRequest;
import dev.archimedes.moviemango.moviecast.application.dto.request.MovieCastUpdateRequest;
import dev.archimedes.moviemango.moviecast.application.dto.response.MovieCastActionResponse;
import dev.archimedes.moviemango.moviecast.application.dto.response.MovieCastJointResponse;
import dev.archimedes.moviemango.moviecast.domain.MovieCast;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieCastController {


  private final MovieCastCreateUseCase movieCastCreateUseCase;
  private final MoveCastUpdateUseCase moveCastUpdateUseCase;
  private final MovieCastFetchUseCase movieCastFetchUseCase;

  @PostMapping("/v1/movie-cast")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Use this API to create a movie-cast relation, i.e attach a cast to movie with role")
  MovieCastActionResponse create(@RequestBody MovieCastCreateRequest request) {
    MovieCast executed = movieCastCreateUseCase.execute(request);
    return new MovieCastActionResponse(executed);
  }


  @PatchMapping("/v1/movie-cast")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Use this API to update the role of a cast in the movie via movie-cast relation.")
  MovieCastActionResponse update(@RequestBody MovieCastUpdateRequest request) {
    MovieCast executed = moveCastUpdateUseCase.execute(request);
    return new MovieCastActionResponse(executed);
  }

  @GetMapping("/v1/movie-cast")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Use this API to fetch the casts in the movie by name or ID")
  List<MovieCastJointResponse> get(@RequestBody MovieCastFetchRequest request) {
    return movieCastFetchUseCase.execute(request);
  }

}
