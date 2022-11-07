package py.com.pegasus.test.pets.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiErrorCodes {
    GENERIC_ERROR("PET1000",
            "Ocurrió un error al realizar la operación, por favor intentelo mas tarde",
            "Ocurrió un error al realizar la operación, por favor intentelo mas tarde"),
    PET_NOT_FOUND("PET1005", "La mascota ingresada no ha sido encontrada",
            "La mascota ingresada no ha sido encontrada"),
    INVALID_REQUEST("PET1006", "Los datos ingresados para la operación son incorrectos",
            "Uno o varios campos del request son incorrectos");

    private String code;
    private String usrMsj;
    private String debugMsj;
}