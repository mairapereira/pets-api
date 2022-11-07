package py.com.pegasus.test.pets.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {
    @Schema(description = "Identificador único de la mascota")
    private UUID id;

    @Schema(description = "Nombre de la mascota")
    private String name;

    @Schema(description = "Apellido de la mascota")
    private String lastName;

    @Schema(description = "Fecha de nacimiento")
    private LocalDate birthDate;

    @Schema(description = "Raza o especie")
    private String breed;

    @Schema(description = "Peso de la mascota")
    private Double weight;

    @Schema(description = "Dueño de la mascota")
    private String owner;

    @Schema(description = "Fecha de creación de la mascota")
    private OffsetDateTime createdAt;

    @Schema(description = "Usuario que creó la mascota")
    private String createdBy;

    @Schema(description = "Fecha de última modificación de datos de la mascota")
    private OffsetDateTime modifiedAt;

    @Schema(description = "Usuario que realizó la última modificación de datos de la mascota")
    private String modifiedBy;
}