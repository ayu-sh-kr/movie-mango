package dev.archimedes.moviemango.profile.domain;

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
public class ProfileRepository implements BaseRepository<Profile, Long> {

  private final JdbcClient jdbcClient;

  @Override
  public Optional<Profile> findById(Long id) {
    return jdbcClient
        .sql("select * from profile where id = :id;")
        .param("id", id)
        .query(Profile.class)
        .optional();
  }

  @Override
  public boolean existsById(Long id) {
    return findById(id).isPresent();
  }

  @Override
  public Profile getReferenceById(Long id) {
    return findById(id).orElse(null);
  }

  public Profile create(Profile profile) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    int rowsAffected = jdbcClient
        .sql("""
            insert into profile(name, age, dob, street, state, zip, country, refer)
            values (:name, :age, :dob, :street, :state, :zip, :country, :refer)
            """)
        .param("name", profile.getName())
        .param("age", profile.getAge())
        .param("dob", profile.getDob())
        .param("street", profile.getStreet().value())
        .param("state", profile.getState().value())
        .param("zip", profile.getZip().value())
        .param("country", profile.getCountry().value())
        .param("refer", profile.getRefer())
        .update(keyHolder);

    Assert.state(rowsAffected == 1, "Only one rows should be affected. Affected: " + rowsAffected);

    Map<String, Object> keys = keyHolder.getKeys();

    Assert.state(keys != null, "Keys must be returned");
    Assert.notNull(keys.get("id"), "Id must be generated");

    // Handle the conversion from Integer to Long
    Number id = (Number) keys.get("id");

    profile.setId(id.longValue());

    Assert.notNull(keys.get("created_at"), "Creation timestamp must be generated");

    profile.setCreatedAt(
        ((Timestamp) keys.get("created_at")).toLocalDateTime()
    );

    return profile;
  }

  @Override
  public Profile save(Profile profile) {
    if (profile.getId() != null && existsById(profile.getId())) {
      return update(profile);
    } else {
      return create(profile);
    }
  }

  public Profile update(Profile profile) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcClient
        .sql("""
            update profile
            set name = :name, age = :age, dob = :dob, street = :street,
                state = :state, zip = :zip, country = :country, updated_at = now()
            where id = :id and refer = :refer
            """)
        .params(Map.of(
            "name", profile.getName(), "age", profile.getAge(), "dob", profile.getDob(),
            "street", profile.getStreet().value(), "state", profile.getState().value(), "zip", profile.getZip().value(),
            "country", profile.getCountry().value(), "id", profile.getId(), "refer", profile.getRefer()
        ))
        .update(keyHolder);

    Map<String, Object> keys = keyHolder.getKeys();
    Assert.state(keys != null, "Keys must be returned");

    Assert.notNull(keys.get("updated_at"), "Updated time should be returned");
    profile.setUpdatedAt(
        ((Timestamp) keys.get("updated_at")).toLocalDateTime()
    );

    return profile;
  }

  @Override
  public void deleteById(Long id) {
    jdbcClient
        .sql("delete from profile where id = :id")
        .param("id", id)
        .update();

  }

  public void deleteByAccountId(Long value) {
    jdbcClient
        .sql("delete from profile where refer = :refer")
        .param("refer", value)
        .update();
  }
}
