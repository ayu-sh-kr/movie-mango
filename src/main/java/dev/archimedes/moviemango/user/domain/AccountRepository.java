package dev.archimedes.moviemango.user.domain;

import dev.archimedes.moviemango.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepository implements BaseRepository<Account, Long> {

  private final JdbcClient jdbcClient;


  @Override
  public Optional<Account> findById(Long id) {
    return jdbcClient
        .sql("select * from account where id = :id")
        .param("id", id)
        .query(Account.class)
        .optional();
  }

  @Override
  public boolean existsById(Long id) {
    return findById(id).isPresent();
  }

  @Override
  public Account getReferenceById(Long id) {
    Optional<Account> optional = findById(id);
    return optional.orElse(null);
  }


  public Account create(Account account) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcClient
        .sql("insert into account(email, password) values (:email, :password)")
        .params(Map.of(
            "email", account.getEmail().value(), "password", account.getPassword().value()
            ))
        .update(keyHolder);

    Map<String, Object> keys = keyHolder.getKeys();


    Assert.state(keys != null, "Key must be returned");
    Assert.state(keys.get("id") != null, "ID Must be created");

    // Handle the conversion from Integer to Long
    Number id = (Number) keys.get("id");
    account.setId(id.longValue());

    Assert.state(keys.get("created_at") != null, "Creation timestamp must be generated");

    account.setCreatedAt(((Timestamp) keys.get("created_at")).toLocalDateTime());
    return account;
  }

  @Override
  public Account save(Account account) {
    if (account.getId() != null && existsById(account.getId())) {
      return update(account);
    } else {
      return create(account);
    }
  }

  public Account update(Account account) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcClient
        .sql("""
                update account
                set email = :email, password = :password, updated_at = :updatedAt
                where id = :id
            """)
        .param("email", account.getEmail().value())
        .param("password", account.getPassword().value())
        .param("updatedAt", LocalDateTime.now())
        .param("id", account.getId())
        .update(keyHolder);

    Map<String, Object> keys = keyHolder.getKeys();

    System.out.println(keys);

    Assert.state(keys != null, "Key must be returned");
    Assert.state(keys.get("updated_at") != null, "Update timestamp must be generated");

    account.setUpdatedAt(((Timestamp)keys.get("updated_at")).toLocalDateTime());

    return account;
  }

  @Override
  public void deleteById(Long id) {
    jdbcClient
        .sql("delete from account where id = :id")
        .param("id", id)
        .update();
  }

  public Optional<Account> findByEmail(String email) {
    return jdbcClient
        .sql("select * from account where email = :email")
        .param("email", email)
        .query(Account.class)
        .optional();
  }

  public boolean existsByEmail(String email) {
    return findByEmail(email).isPresent();
  }
}
