package pe.com.examples.azureclientapi.repository;

import org.springframework.data.repository.CrudRepository;
import pe.com.examples.azureclientapi.entity.Client;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, String> {
    Optional<Client> findById(String id);

}
