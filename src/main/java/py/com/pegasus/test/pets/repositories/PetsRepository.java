package py.com.pegasus.test.pets.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import py.com.pegasus.test.pets.models.Pet;
import py.com.pegasus.test.pets.repositories.entities.PetsEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetsRepository extends CrudRepository<PetsEntity, UUID> {
    List<PetsEntity> findAllByOwner(UUID owner);
}
