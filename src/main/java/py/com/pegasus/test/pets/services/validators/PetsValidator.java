package py.com.pegasus.test.pets.services.validators;

import lombok.experimental.UtilityClass;
import py.com.pegasus.test.pets.exceptions.ApiException;
import py.com.pegasus.test.pets.exceptions.ApiExceptionBuilder;
import py.com.pegasus.test.pets.exceptions.ApiExceptionType;
import py.com.pegasus.test.pets.models.request.PatchPetData;
import py.com.pegasus.test.pets.models.request.PetData;

import java.time.LocalDate;
import java.util.Optional;

import static py.com.pegasus.test.pets.constants.ApiErrorCodes.INVALID_REQUEST;

@UtilityClass
public class PetsValidator {
    public static final String INVALID_BREED = "Raza de la mascota nula o vacia";
    public static final String INVALID_UPDATE_DATA = "Los datos a utilizar en la actualización son nulos o vacíos";

    public static void validatePetData(PetData data) throws ApiException {
        validateName(data.getName());
        validateLastName(data.getLastName());
        validateBirthDate(data.getBirthDate());
        validateBreed(data.getBreed());
    }

    public static void validatePatchPetData(PatchPetData data) throws ApiException {
        Optional.ofNullable(data)
                .filter(petData -> petData.getName() != null || petData.getLastName() != null || petData.getBirthDate() != null)
                .orElseThrow(() -> ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(),
                        INVALID_REQUEST.getUsrMsj(),
                        INVALID_UPDATE_DATA,
                        ApiExceptionType.VALIDATION));
        validateName(data.getName());
        validateLastName(data.getLastName());
        validateBirthDate(data.getBirthDate());
    }

    public static void validatDeletePetData(String id) throws ApiException {
        if (id == null || id.trim().isEmpty())
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "Identificador de mascota nulo o vacío", ApiExceptionType.VALIDATION);
    }

    public static void validatFindPetData(String id) throws ApiException {
        if (id == null || id.trim().isEmpty())
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "Identificador de mascota nulo o vacío", ApiExceptionType.VALIDATION);
    }

    public static void validateBreed(String document) throws ApiException {
        if (document == null || document.trim().isEmpty())
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    INVALID_BREED, ApiExceptionType.VALIDATION);
    }

    public static void validateName(String name) throws ApiException {
        if (name == null || name.trim().isEmpty())
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "El nombre es nulo o vacío", ApiExceptionType.VALIDATION);
    }

    public static void validateLastName(String lastName) throws ApiException {
        if (lastName == null || lastName.trim().isEmpty())
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "El apellido es nulo o vacío", ApiExceptionType.VALIDATION);
    }

    public static void validateBirthDate(LocalDate dateTime) throws ApiException {
        if (dateTime == null || LocalDate.now().isBefore(dateTime))
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "La fecha de nacimiento no es válida", ApiExceptionType.VALIDATION);
    }

    public static void validateWeight(Double weight) throws ApiException {
        if (weight == null || (weight.compareTo(Double.valueOf(0)) <= 0))
            throw ApiExceptionBuilder.buildApiExceptionFrom(INVALID_REQUEST.getCode(), INVALID_REQUEST.getUsrMsj(),
                    "El peso de la mascota es inválido", ApiExceptionType.VALIDATION);
    }
}
