package pl.mfurman.owasp_sample.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

  public final static String API_HELLO = "/api/hello";
  public final static String API_HELLO_ADMIN = API_HELLO + "/admin";
  public final static String API_HELLO_USER = API_HELLO + "/user";

  @GetMapping(API_HELLO)
  public String sayHello() {
    return "Hello World";
  }

  @GetMapping(API_HELLO_ADMIN)
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String sayHelloToAdmin() {
    return "Hello Admin";
  }

  @GetMapping(API_HELLO_USER)
  @PreAuthorize("hasRole('ROLE_USER')")
  public String sayHelloToUser() {
    return "Hello User";
  }
}
