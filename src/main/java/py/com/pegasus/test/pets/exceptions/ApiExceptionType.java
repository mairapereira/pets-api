package py.com.pegasus.test.pets.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiExceptionType {
    APPLICATION,
    DATABASE,
    VALIDATION,
    SECURITY
}