package dev.archimedes.moviemango.user.application;

import dev.archimedes.moviemango.user.application.dtos.request.AccountCreateRequest;
import dev.archimedes.moviemango.user.application.dtos.request.AccountDeleteRequest;
import dev.archimedes.moviemango.user.application.dtos.response.AccountGetResponse;
import dev.archimedes.moviemango.user.domain.Account;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AccountController {


  private final AccountCreateUseCase accountCreateUseCase;
  private final AccountFetchUseCase accountFetchUseCase;
  private final AccountDeleteUseCase accountDeleteUseCase;


  @PostMapping("/v1/account")
  @Operation(summary = "Create an account for the user.")
  public ResponseEntity<Object> create(@RequestBody AccountCreateRequest request) {
    Account executed = accountCreateUseCase.execute(request);
    return new ResponseEntity<>(Map.of("id", executed.getId()), HttpStatus.CREATED);
  }

  @GetMapping("/v1/account")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get the account of user by the account id.")
  public AccountGetResponse get(@RequestParam Long id) {
    Account executed = accountFetchUseCase.execute(id);
    return new AccountGetResponse(executed);
  }


  @DeleteMapping("/v1/account")
  @ResponseStatus(HttpStatus.ACCEPTED)
  @Operation(summary = "Delete the account of the user by account id")
  public void delete(@RequestParam Long id) {
    accountDeleteUseCase.execute(new AccountDeleteRequest(id));
  }

}
