package dev.archimedes.moviemango.moviecast.domain;

import dev.archimedes.moviemango.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieCastRepository implements BaseRepository<MovieCast, Long> {

  private final JdbcClient jdbcClient;


  @Override
  public Optional<MovieCast> findById(Long id) {
    return jdbcClient
        .sql("select * from movie_cast where id = :id")
        .param("id", id)
        .query(MovieCast.class)
        .optional();
  }

  @Override
  public boolean existsById(Long id) {
    return findById(id).isPresent();
  }

  @Override
  public MovieCast getReferenceById(Long id) {
    return findById(id).orElse(null);
  }

  public MovieCast create(MovieCast movieCast) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcClient
        .sql("""
                insert into movie_cast(cast_id, movie_id, role, created_at)
                values (?, ?, ?::movie_role, now())
            """)
        .params(
            List.of(
                movieCast.getCastId(), movieCast.getMovieId(), movieCast.getRole()
            )
        )
        .update(keyHolder);

    Map<String, Object> keys = keyHolder.getKeys();
    Assert.notNull(keys, "Keys must be returned after inserting into movie_cast");


    Assert.isTrue(
        keys.containsKey("id"), "Key must contain id"
    );
    movieCast.setId(
        ((Number) keys.get("id")).longValue()
    );


    Assert.isTrue(
        keys.containsKey("created_at"), "Key must contain created_at"
    );
    movieCast.setCreatedAt(
        ((Timestamp) keys.get("created_at")).toLocalDateTime()
    );

    return movieCast;
  }

  @Override
  public MovieCast save(MovieCast movieCast) {
    return null;
  }

  public MovieCast update(MovieCast movieCast) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcClient
        .sql("""
                update movie_cast
                set cast_id = ?, movie_id = ?, role = ?::movie_role, updated_at = now()
                where id = ?
            """)
        .params(
            List.of(movieCast.getCastId(), movieCast.getMovieId(), movieCast.getRole(), movieCast.getId())
        )
        .update(keyHolder);

    Map<String, Object> keys = keyHolder.getKeys();
    Assert.notNull(keys, "Keys must be returned after updating movie_cast");

    Assert.isTrue(keys.containsKey("created_at"), "Key must contain created_at");
    movieCast.setCreatedAt(
        ((Timestamp) keys.get("created_at")).toLocalDateTime()
    );

    Assert.isTrue(keys.containsKey("updated_at"), "Key must contain updated_at");
    movieCast.setUpdatedAt(
        ((Timestamp) keys.get("updated_at")).toLocalDateTime()
    );

    return movieCast;
  }

  @Override
  public void deleteById(Long id) {
    jdbcClient
        .sql("delete from movie_cast where id = :id")
        .param("id", id)
        .update();
  }

  public Optional<MovieCast> findByIdAndCastIdAndMovieId(Long id, Long castId, Long movieId) {
    return jdbcClient
        .sql("select * from movie_cast where id = ? and cast_id = ? and movie_id = ?")
        .params(List.of(id, castId, movieId))
        .query(MovieCast.class)
        .optional();
  }

  public boolean existsByIdAndCastIdAndMovieId(Long id, Long castId, Long movieId) {
    return findByIdAndCastIdAndMovieId(id, castId, movieId).isPresent();
  }
}
