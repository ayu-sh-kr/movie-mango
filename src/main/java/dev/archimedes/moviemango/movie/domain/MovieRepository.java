package dev.archimedes.moviemango.movie.domain;

import dev.archimedes.moviemango.BaseRepository;
import dev.archimedes.moviemango.movie.application.dto.FilterUnit;
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
public class MovieRepository implements BaseRepository<Movie, Long> {

  private final JdbcClient jdbcClient;

  @Override
  public Optional<Movie> findById(Long id) {
    return jdbcClient
        .sql("select * from movie where id = :id")
        .param("id", id)
        .query(Movie.class)
        .optional();
  }

  @Override
  public boolean existsById(Long id) {
    return findById(id).isPresent();
  }

  @Override
  public Movie getReferenceById(Long id) {
    return findById(id).orElse(null);
  }

  protected Movie create(Movie movie) {

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcClient
        .sql("""
            insert into movie(title, description, release, duration, genre, director, rating, language, origin, created_at)
            values(:title, :description, :release, :duration, :genre::movie_genre, :director, :rating, :language, :origin, now())
            """)
        .params(
            Map.of(
                "title", movie.getTitle().value(), "description", movie.getDescription().value(), "release", movie.getRelease(),
                "duration", movie.getDuration(), "genre", movie.getGenre().value().value, "director", movie.getDirector(),
                "rating", movie.getRating().value(), "language", movie.getLanguage().value(), "origin", movie.getOrigin().value()
            )
        )
        .update(keyHolder);

    Map<String, Object> keys = keyHolder.getKeys();
    Assert.notNull(keys, "Keys must returned when inserting into movie");

    Assert.notNull(keys.get("id"), "Id must be returned in keys");
    Number id = (Number) keys.get("id");
    movie.setId(id.longValue());

    Assert.notNull(keys.get("created_at"), "Creation time must be generated when inserting record in movie");
    movie.setCreatedAt(
        ((Timestamp) keys.get("created_at")).toLocalDateTime()
    );

    return movie;
  }

  @Override
  public Movie save(Movie movie) {

    if (movie.getId() != null && existsById(movie.getId())) {
      return update(movie);
    } else {
      return create(movie);
    }
  }

  protected Movie update(Movie movie) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    int rowsAffected = jdbcClient
        .sql("""
            update movie
            set title = :title, description = :description, release = :release, duration = :duration,
                genre = :genre::movie_genre, director = :director, rating = :rating, language = :language,
                origin = :origin, updated_at = now()
            where id = :id
            """)
        .params(
            Map.of(
                "title", movie.getTitle().value(), "description", movie.getDescription().value(), "release", movie.getRelease(),
                "duration", movie.getDuration(), "genre", movie.getGenre().value().value, "director", movie.getDirector(),
                "rating", movie.getRating().value(), "language", movie.getLanguage().value(), "origin", movie.getOrigin().value(),
                "id", movie.getId()
            )
        )
        .update(keyHolder);

    Assert.state(rowsAffected == 1, "Unexpected rows deleted, when updating movie. Rows Affected: " + rowsAffected);

    Map<String, Object> keys = keyHolder.getKeys();
    Assert.notNull(keys, "Keys must returned when inserting into movie");

    Assert.notNull(keys.get("created_at"), "Creation time must have been returned when updating movie");
    movie.setCreatedAt(
        ((Timestamp) keys.get("created_at")).toLocalDateTime()
    );

    Assert.notNull(keys.get("updated_at"), "Update time must have been returned when updating movie");
    movie.setCreatedAt(
        ((Timestamp) keys.get("updated_at")).toLocalDateTime()
    );

    return movie;

  }

  @Override
  public void deleteById(Long id) {
    int rowsAffected = jdbcClient
        .sql("delete from movie where id = :id")
        .param("id", id)
        .update();

    Assert.state(rowsAffected == 1, "Unexpected rows deleted, when deleting movie. Rows Affected: " + rowsAffected);
  }

  public List<Movie> findAllByLimit(Integer limit) {
    return jdbcClient
        .sql("select * from movie limit :limit")
        .param("limit", limit)
        .query(Movie.class)
        .list();
  }

  public List<Movie> findAllWhereTitleEqualAndLimit(String title, Integer limit) {
    return jdbcClient
        .sql("select * from movie where title like :title limit :limit")
        .param("title", "%" + title + "%")
        .param("limit", limit)
        .query(Movie.class)
        .list();
  }

  public List<Movie> findAllByFilter(FilterUnit unit) {
    switch (unit.type()) {

      case RATING -> {
        return jdbcClient
            .sql("select * from movie where rating = :rating")
            .param("rating", unit.rating())
            .query(Movie.class)
            .list();
      }

      case DIRECTOR -> {
        return jdbcClient
            .sql("select * from movie where lower(director) like :director")
            .param("director", "%" + unit.value().toLowerCase() + "%")
            .query(Movie.class)
            .list();
      }

      case GENRE -> {
        return jdbcClient
            .sql("select * from movie where genre = :genre::movie_genre")
            .param("genre", AvailableGenre.fromString(unit.value()).value)
            .query(Movie.class)
            .list();
      }

      case ORIGIN -> {
        return jdbcClient
            .sql("select * from movie where lower(origin) like :origin")
            .param("origin", "%" + unit.value().toLowerCase() + "%")
            .query(Movie.class)
            .list();
      }

      case DURATION -> {
        return jdbcClient
            .sql("select * from movie where duration >= :duration")
            .param("duration", unit.duration())
            .query(Movie.class)
            .list();
      }

      case LANGUAGE -> {
        return jdbcClient
            .sql("select * from movie where lower(language) like :language")
            .param("language", "%" + unit.value().toLowerCase() + "%")
            .query(Movie.class)
            .list();
      }

      case RELEASE -> {
        return jdbcClient
            .sql("select * from movie where release = :release")
            .param("release", unit.release())
            .query(Movie.class)
            .list();
      }

      default -> {
        return null;
      }
    }
  }
}
