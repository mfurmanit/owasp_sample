package pl.mfurman.owasp_sample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mfurman.owasp_sample.model.Person;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

  @Query(value = "SELECT * FROM public.person WHERE name = :name", nativeQuery = true)
  List<Person> findByNameSafe(@Param("name") final String name);

  List<Person> findByName(final String name);
}
