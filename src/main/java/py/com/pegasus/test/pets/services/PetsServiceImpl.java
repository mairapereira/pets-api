package py.com.pegasus.test.pets.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import py.com.pegasus.test.pets.exceptions.ApiException;
import py.com.pegasus.test.pets.exceptions.ApiExceptionBuilder;
import py.com.pegasus.test.pets.exceptions.ApiExceptionType;
import py.com.pegasus.test.pets.models.Pet;
import py.com.pegasus.test.pets.models.Pets;
import py.com.pegasus.test.pets.models.request.PatchPetData;
import py.com.pegasus.test.pets.models.request.PetData;
import py.com.pegasus.test.pets.repositories.PetsRepository;
import py.com.pegasus.test.pets.repositories.entities.PetsEntity;
import py.com.pegasus.test.pets.services.mappers.PetsMapper;

import java.util.List;
import java.util.UUID;

import static py.com.pegasus.test.pets.constants.ApiErrorCodes.PET_NOT_FOUND;
import static py.com.pegasus.test.pets.services.mappers.PetsMapper.buildPetEntityToCreate;
import static py.com.pegasus.test.pets.services.mappers.PetsMapper.buildPetEntityToUpdate;
import static py.com.pegasus.test.pets.services.validators.PetsValidator.validatDeletePetData;
import static py.com.pegasus.test.pets.services.validators.PetsValidator.validatFindPetData;
import static py.com.pegasus.test.pets.services.validators.PetsValidator.validatePatchPetData;
import static py.com.pegasus.test.pets.services.validators.PetsValidator.validatePetData;


@Service
@RequiredArgsConstructor
@Slf4j
public class PetsServiceImpl implements PetsService {
    private final PetsRepository petsRepository;
    private final SecurityAuthentication securityAuthentication;

    @Override
    public Pets findAllByOwner(String id) throws ApiException {
        log.info("Intentaremos obtener todas las mascotas con owner id {}", id);

        //validamos datos de entrada
        validatFindPetData(id);

        //obtenemos los datos de la mascota
        List<PetsEntity> petsEntities = petsRepository.findAllByOwner(UUID.fromString(id));
        List<Pet> pets = PetsMapper.toPetDomainList(petsEntities);

        log.info("Se obtuvieron {} mascotas", pets.size());
        return Pets.builder().pets(pets).build();
    }

    @Override
    public Pet findById(String id) throws ApiException {
        log.info("Intentaremos obtener los datos de la mascota con criterios de busqueda {}", id);

        //obtenemos los datos de la mascota
        Pet pet = petsRepository.findById(UUID.fromString(id))
                .map(PetsMapper::mapPetEntityToDomain)
                .orElseThrow(() -> ApiExceptionBuilder.buildApiExceptionFrom(PET_NOT_FOUND,
                        ApiExceptionType.VALIDATION));

        log.info("Se obtuvo los datos de la mascota exitosamente");
        return pet;
    }

    @Override
    public Pet create(PetData data) throws ApiException {
        log.info("Intentaremos crear mascota");

        //validamos datos de entrada
        validatePetData(data);

        //creamos mascota
        String user = securityAuthentication.getAuthentication().getName();
        PetsEntity entity = buildPetEntityToCreate(data, user);
        PetsEntity petCreated = petsRepository.save(entity);

        //retornamos mascota creada
        Pet pet = PetsMapper.mapPetEntityToDomain(petCreated);

        log.info("Mascota creada exitosamente");
        return pet;
    }

    @Override
    public Pet update(String id, PetData data) throws ApiException {
        log.info("Intentaremos actualizar los datos de la mascota");

        //validamos datos de entrada
        validatePetData(data);
        PetsEntity originalPet = petsRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> ApiExceptionBuilder.buildApiExceptionFrom(PET_NOT_FOUND,
                        ApiExceptionType.VALIDATION));

        //actualizamos todos los datos de la mascota
        String user = securityAuthentication.getAuthentication().getName();
        PetsEntity entity = PetsMapper.buildPetEntityToUpdate(data, originalPet, user);
        PetsEntity petUpdated = petsRepository.save(entity);

        //retornamos mascota actualizada
        Pet pet = PetsMapper.mapPetEntityToDomain(petUpdated);

        log.info("Mascota actualizada exitosamente");
        return pet;
    }

    @Override
    public Pet partialUpdate(String id, PatchPetData data) throws ApiException {
        log.info("Intentaremos actualizar algunos datos de la mascota");

        //validamos datos de entrada
        validatePatchPetData(data);
        PetsEntity originalPet = petsRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> ApiExceptionBuilder.buildApiExceptionFrom(PET_NOT_FOUND,
                        ApiExceptionType.VALIDATION));

        //actualiamos solo algunos datos de la mascota
        String user = securityAuthentication.getAuthentication().getName();
        PetsEntity entity = buildPetEntityToUpdate(data, originalPet, user);
        PetsEntity petUpdated = petsRepository.save(entity);

        //retornamos mascota actualizada
        Pet pet = PetsMapper.mapPetEntityToDomain(petUpdated);

        log.info("Mascota actualizada exitosamente");
        return pet;
    }

    @Override
    public void delete(String id) throws ApiException {
        log.info("Intentaremos actualizar algunos datos de la mascota");

        //validamos datos de entrada
        validatDeletePetData(id);
        PetsEntity originalPet = petsRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> ApiExceptionBuilder.buildApiExceptionFrom(PET_NOT_FOUND,
                        ApiExceptionType.VALIDATION));

        //eliminamos a la mascota
        petsRepository.delete(originalPet);

        log.info("Mascota eliminada exitosamente");
    }

}
