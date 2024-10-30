package dev.archimedes.moviemango.moviecast.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.moviecast.application.dto.request.MovieCastCreateRequest;
import dev.archimedes.moviemango.moviecast.domain.MovieCast;
import dev.archimedes.moviemango.moviecast.domain.MovieCastRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;

/// **MovieCastCreateUseCase** is a use case class responsible for adding cast to a movie.
///
/// Perform validation to check whether the given movie and cast exist to ensure valid `Cast` is
/// added to the valid `Movie`
/// Perform validation related to the request and persist record using [MovieCastRepository]
/// @see dev.archimedes.moviemango.moviecast.domain.MovieCast
/// @see MovieCastRepository
/// @see MovieCastCreateRequest
@UseCase
public class MovieCastCreateUseCase {

  private final MovieCastRepository movieCastRepository;
  private final RestClient restClient;
  private static final String BASE_URL = "http://localhost:8080";

  public MovieCastCreateUseCase(MovieCastRepository movieCastRepository) {
    this.movieCastRepository = movieCastRepository;
    this.restClient = RestClient.builder()
        .baseUrl(BASE_URL)
        .build();
  }


  public MovieCast execute(MovieCastCreateRequest request) {

    Assert.state(
        Boolean.TRUE.equals(checkExistence("cast", request.castId())),
        "Cast must exist to create a movie-cast relation"
    );

    Assert.state(
        Boolean.TRUE.equals(checkExistence("movie", request.movieId())),
        "Movie must exist to create a movie-cast relation"
    );

    return movieCastRepository.save(
        new MovieCast(
            request.castId(), request.movieId(), request.role(), null, null
        )
    );
  }

  private Boolean checkExistence(String domain, Long id) {
    ResponseEntity<Boolean> entity = restClient.get()
        .uri("/v1/{domain}/{id}/exists", domain, id)
        .retrieve()
        .toEntity(Boolean.class);
    return entity.getBody();
  }

}
