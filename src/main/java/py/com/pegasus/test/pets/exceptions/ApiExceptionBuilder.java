package py.com.pegasus.test.pets.exceptions;

import lombok.experimental.UtilityClass;
import py.com.pegasus.test.pets.constants.ApiErrorCodes;

@UtilityClass
public class ApiExceptionBuilder {

    public static ApiException buildApiExceptionFrom(ApiErrorCodes errorCode, ApiExceptionType type) {
        return ApiException.builder()
                .code(errorCode.getCode())
                .userMessage(errorCode.getUsrMsj())
                .debugMessage(errorCode.getDebugMsj())
                .type(type)
                .build();

    }

    public static ApiException buildApiExceptionFrom(String code, String userMsj, String debugMsj, ApiExceptionType type) {
        return ApiException.builder()
                .code(code)
                .userMessage(userMsj)
                .debugMessage(debugMsj)
                .type(type)
                .build();

    }
}