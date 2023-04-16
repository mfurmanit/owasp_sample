package pl.mfurman.owasp_sample.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.mfurman.owasp_sample.model.Person;
import pl.mfurman.owasp_sample.services.PersonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

  public final static String API_PERSON = "/api/person";
  public final static String API_PERSON_UNSAFE = API_PERSON + "/unsafe/{name}";
  public final static String API_PERSON_SAFE = API_PERSON + "/safe/{name}";
  public final static String API_PERSON_JPA = API_PERSON + "/jpa/{name}";

  private final PersonService service;

  @GetMapping(API_PERSON_UNSAFE)
  public List<Person> getUnsafe(@PathVariable final String name) {
    return service.getUnsafe(name);
  }

  @GetMapping(API_PERSON_SAFE)
  public List<Person> getSafe(@PathVariable final String name) {
    return service.getSafe(name);
  }

  @GetMapping(API_PERSON_JPA)
  public List<Person> getWithJpa(@PathVariable final String name) {
    return service.getWithJpa(name);
  }
}
