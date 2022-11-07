package py.com.pegasus.test.pets.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

import static py.com.pegasus.test.pets.exceptions.ApiExceptionType.APPLICATION;
import static py.com.pegasus.test.pets.exceptions.ApiExceptionType.DATABASE;
import static py.com.pegasus.test.pets.exceptions.ApiExceptionType.SECURITY;
import static py.com.pegasus.test.pets.exceptions.ApiExceptionType.VALIDATION;


@AllArgsConstructor
@Getter
public enum ApiHttpErrorsMapper {
    APPLICATION_ERROR(APPLICATION, HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ERROR(DATABASE, HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(VALIDATION, HttpStatus.BAD_REQUEST),
    SECURITY_ERROR(SECURITY, HttpStatus.UNAUTHORIZED);

    private ApiExceptionType exceptionType;
    private HttpStatus httpStatus;

    static Optional<HttpStatus> findHttpStatusByErrorCode(ApiExceptionType exceptionType) {
        return Arrays.stream(ApiHttpErrorsMapper.values())
                .filter(errorMap -> errorMap.getExceptionType().equals(exceptionType))
                .map(ApiHttpErrorsMapper::getHttpStatus)
                .findFirst();
    }
}
