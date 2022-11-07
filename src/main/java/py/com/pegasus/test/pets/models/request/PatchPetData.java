package py.com.pegasus.test.pets.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema
public class PatchPetData {
    @Schema(description = "Nombre de la mascota", required = false)
    private String name;

    @Schema(description = "Apellido de la mascota", required = false)
    private String lastName;

    @Schema(description = "Fecha de nacimiento", required = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @Schema(description = "Raza o especie")
    private String breed;

    @Schema(description = "Peso de la mascota")
    private Double weight;
}