package pl.mfurman.owasp_sample.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mfurman.owasp_sample.model.Person;
import pl.mfurman.owasp_sample.repositories.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

  @PersistenceContext
  private final EntityManager entityManager;

  private final PersonRepository repository;

  @SuppressWarnings("unchecked")
  public List<Person> getUnsafe(final String name) {
    final String query = "SELECT * FROM public.person WHERE name = '" + name + "'";
    return entityManager.createNativeQuery(query, Person.class).getResultList();
  }

  public List<Person> getSafe(final String name) {
    return repository.findByNameSafe(name);
  }

  public List<Person> getWithJpa(final String name) {
    return repository.findByName(name);
  }
}
