package py.com.pegasus.test.pets.services;


import py.com.pegasus.test.pets.exceptions.ApiException;
import py.com.pegasus.test.pets.models.Pet;
import py.com.pegasus.test.pets.models.request.PatchPetData;
import py.com.pegasus.test.pets.models.request.PetData;

public interface PetsService {
    Pet findById(String id) throws ApiException;

    Pet create(PetData data) throws ApiException;

    Pet update(String id, PetData data) throws ApiException;

    Pet partialUpdate(String id, PatchPetData data) throws ApiException;

    void delete(String id) throws ApiException;
}
