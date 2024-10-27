package dev.archimedes.moviemango.cast.domain;

import dev.archimedes.moviemango.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CastRepository implements BaseRepository<Cast, Long> {

  private final JdbcClient jdbcClient;

  @Override
  public Optional<Cast> findById(Long id) {
    return jdbcClient
        .sql("select * from cast_profile where id = :id")
        .param("id", id)
        .query(Cast.class)
        .optional();
  }

  @Override
  public boolean existsById(Long id) {
    return findById(id).isPresent();
  }

  @Override
  public Cast getReferenceById(Long id) {
    return findById(id).orElse(null);
  }

  Cast create(Cast cast) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcClient
        .sql("""
                insert into cast_profile(name, age, dob, gender, country)
                values (:name, :age, :dob, :gender::gender, :country)
            """)
        .params(
            Map.of(
                "name", cast.getName().value(), "age", cast.getAge().value(), "gender", cast.getGender().getValue(),
                "country", cast.getCountry().value()
            )
        )
        .update(keyHolder);

    Map<String, Object> keys = keyHolder.getKeys();
    Assert.notNull(keys, "Keys must be returned after inserting cast");

    Assert.notNull(keys.get("id"), "Key must contain id after inserting cast");
    Number id = (Number) keys.get("id");
    cast.setId(id.longValue());

    Assert.notNull(keys.get("created_at"), "Key must contain created_at after inserting cast");
    cast.setCreatedAt(
        ((Timestamp) keys.get("created_at")).toLocalDateTime()
    );

    return cast;
  }

  @Override
  public Cast save(Cast cast) {
    if(cast.getId() != null && existsById(cast.getId())) {
      return update(cast);
    } else {
      return create(cast);
    }
  }

  Cast update(Cast cast) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcClient
        .sql("""
            update cast_profile
            set name = :name, age = :age, gender = :gender::gender, country = :country, updated_at = now()
            where id = :id
            """)
        .params(
            Map.of(
                "name", cast.getName().value(), "age", cast.getAge().value(), "gender", cast.getGender().getValue(),
                "country", cast.getCountry().value(), "id", cast.getId()
            )
        ).update(keyHolder);

    Map<String, Object> keys = keyHolder.getKeys();
    Assert.notNull(keys, "Keys must be returned after updating cast");

    Assert.notNull(keys.get("created_at"), "Key must contain created_at after updating cast");
    cast.setCreatedAt(
        ((Timestamp) keys.get("created_at")).toLocalDateTime()
    );

    Assert.notNull(keys.get("updated_at"), "Key must contain updated_at after updating cast");
    cast.setUpdatedAt(
        ((Timestamp) keys.get("updated_at")).toLocalDateTime()
    );

    return cast;
  }

  @Override
  public void deleteById(Long id) {
    int rowsAffected = jdbcClient
        .sql("delete from cast_profile where id = :id")
        .param("id", id)
        .update();

    Assert.state(rowsAffected == 1, "Unexpected no. of rows get affected while deleting. Rows Affected: " + rowsAffected);
  }
}
