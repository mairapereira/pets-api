package py.com.pegasus.test.pets.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import py.com.pegasus.test.pets.exceptions.ApiException;
import py.com.pegasus.test.pets.exceptions.ApiExceptionType;
import py.com.pegasus.test.pets.models.Pet;
import py.com.pegasus.test.pets.models.request.PatchPetData;
import py.com.pegasus.test.pets.models.request.PetData;
import py.com.pegasus.test.pets.services.PetsService;

import static py.com.pegasus.test.pets.constants.ApiSettings.API_VERSION;
import static py.com.pegasus.test.pets.constants.ApiErrorCodes.GENERIC_ERROR;
import static py.com.pegasus.test.pets.exceptions.ApiExceptionBuilder.buildApiExceptionFrom;

@RestController
@RequestMapping(value = API_VERSION + "/pets")
@RequiredArgsConstructor
@Slf4j
public class PetsController {
    public static final String ERROR_INESPERADO_CHECKOUT = "Ocurrió un error inesperado crear la mascota";
    private final PetsService petService;

    @Operation(summary = "Obtiene los datos dado un identificador de mascota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Pet.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error inesperado")})

    @GetMapping("/{pet-id}")
    @ResponseStatus(code = HttpStatus.OK)
    private Pet findPet(@PathVariable(name = "pet-id") String petId) throws ApiException {
        try {
            return petService.findById(petId);
        } catch (ApiException ae) {
            log.error("Ocurrio un error obtener los datos de la mascota", ae);
            throw ae;
        } catch (Exception e) {
            log.error(ERROR_INESPERADO_CHECKOUT, e);
            throw buildApiExceptionFrom(GENERIC_ERROR, ApiExceptionType.APPLICATION);
        }
    }

    @Operation(summary = "Crea una mascota con sus datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Pet.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error inesperado")})

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    private Pet createPet(@RequestBody PetData request) throws ApiException {
        try {
            return petService.create(request);
        } catch (ApiException ae) {
            log.error("Ocurrio un error al crear la mascota", ae);
            throw ae;
        } catch (Exception e) {
            log.error(ERROR_INESPERADO_CHECKOUT, e);
            throw buildApiExceptionFrom(GENERIC_ERROR, ApiExceptionType.APPLICATION);
        }
    }

    @Operation(summary = "Actualiza los datos de una mascota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Pet.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error inesperado")})

    @PutMapping("/{pet-id}")
    @ResponseStatus(code = HttpStatus.OK)
    private Pet updatePet(
            @PathVariable(name = "pet-id") String petId,
            @RequestBody PetData request) throws ApiException {
        try {
            return petService.update(petId, request);
        } catch (ApiException ae) {
            log.error("Ocurrio un error al actualizar la mascota", ae);
            throw ae;
        } catch (Exception e) {
            log.error(ERROR_INESPERADO_CHECKOUT, e);
            throw buildApiExceptionFrom(GENERIC_ERROR, ApiExceptionType.APPLICATION);
        }
    }

    @Operation(summary = "Actualiza los datos de una mascota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Pet.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error inesperado")})

    @PatchMapping("/{pet-id}")
    @ResponseStatus(code = HttpStatus.OK)
    private Pet partialUpdatePet(
            @PathVariable(name = "pet-id") String petId,
            @RequestBody PatchPetData request) throws ApiException {
        try {
            return petService.partialUpdate(petId, request);
        } catch (ApiException ae) {
            log.error("Ocurrio un error al actualizar la mascota", ae);
            throw ae;
        } catch (Exception e) {
            log.error(ERROR_INESPERADO_CHECKOUT, e);
            throw buildApiExceptionFrom(GENERIC_ERROR, ApiExceptionType.APPLICATION);
        }
    }

    @Operation(summary = "Elimina una mascota dado su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error inesperado")})

    @DeleteMapping("/{pet-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    private void partialUpdatePet(@PathVariable(name = "pet-id") String petId) throws ApiException {
        try {
            petService.delete(petId);
        } catch (ApiException ae) {
            log.error("Ocurrio un error al eliminar la mascota", ae);
            throw ae;
        } catch (Exception e) {
            log.error(ERROR_INESPERADO_CHECKOUT, e);
            throw buildApiExceptionFrom(GENERIC_ERROR, ApiExceptionType.APPLICATION);
        }
    }
}