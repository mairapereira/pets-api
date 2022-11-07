package py.com.pegasus.test.pets.services.mappers;

import lombok.experimental.UtilityClass;
import py.com.pegasus.test.pets.models.Pet;
import py.com.pegasus.test.pets.models.request.PatchPetData;
import py.com.pegasus.test.pets.models.request.PetData;
import py.com.pegasus.test.pets.repositories.entities.PetsEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class PetsMapper {
    public static Pet mapPetEntityToDomain(PetsEntity entity) {
        return Optional.ofNullable(entity)
                .map(ent -> Pet.builder()
                        .id(ent.getId())
                        .name(ent.getName())
                        .lastName(ent.getLastName())
                        .birthDate(ent.getBirthDate())
                        .breed(ent.getBreed())
                        .weight(ent.getWeight())
                        .owner(ent.getOwner().toString())
                        .createdAt(ent.getCreatedAt())
                        .createdBy(ent.getCreatedBy())
                        .modifiedAt(ent.getModifiedAt())
                        .modifiedBy(ent.getModifiedBy())
                        .build())
                .orElse(null);

    }

    public static List<Pet> toPetDomainList(List<PetsEntity> entityList) {
        return Optional.ofNullable(entityList)
                .map(list -> list.stream()
                        .map(pet -> mapPetEntityToDomain(pet))
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public static PetsEntity buildPetEntityToCreate(PetData data, String user) {
        OffsetDateTime now = OffsetDateTime.now();
        return Optional.ofNullable(data)
                .map(req -> PetsEntity.builder()
                        .name(data.getName())
                        .lastName(data.getLastName())
                        .birthDate(data.getBirthDate())
                        .breed(data.getBreed())
                        .weight(data.getWeight())
                        .owner(UUID.fromString(data.getOwner()))
                        .createdAt(now)
                        .createdBy(user)
                        .modifiedAt(now)
                        .modifiedBy(user)
                        .build())
                .orElse(null);
    }

    public static PetsEntity buildPetEntityToUpdate(PetData data, PetsEntity originalPet, String user) {
        OffsetDateTime now = OffsetDateTime.now();
        return Optional.ofNullable(data)
                .map(req -> PetsEntity.builder()
                        .id(originalPet.getId())
                        .name(data.getName())
                        .lastName(data.getLastName())
                        .birthDate(data.getBirthDate())
                        .breed(data.getBreed())
                        .weight(data.getWeight())
                        .owner(UUID.fromString(data.getOwner()))
                        .createdAt(originalPet.getCreatedAt())
                        .createdBy(originalPet.getCreatedBy())
                        .modifiedAt(now)
                        .modifiedBy(user)
                        .build())
                .orElse(null);
    }

    public static PetsEntity buildPetEntityToUpdate(PatchPetData data, PetsEntity originalPet, String user) {
        OffsetDateTime now = OffsetDateTime.now();
        return Optional.ofNullable(data)
                .map(req -> PetsEntity.builder()
                        .id(originalPet.getId())
                        .name(data.getName())
                        .lastName(data.getLastName())
                        .birthDate(data.getBirthDate())
                        .breed(data.getBreed())
                        .weight(data.getWeight())
                        .createdAt(originalPet.getCreatedAt())
                        .createdBy(originalPet.getCreatedBy())
                        .modifiedAt(now)
                        .modifiedBy(user)
                        .build())
                .orElse(null);
    }
}
