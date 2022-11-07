package py.com.pegasus.test.pets.exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.pegasus.test.pets.models.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static py.com.pegasus.test.pets.constants.ApiErrorCodes.GENERIC_ERROR;


@AllArgsConstructor
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Throwable.class)
    public @ResponseBody ErrorResponse handleException(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       Exception ex) {
        log.info("Parseando error en GlobalExceptionHandler", ex);
        ErrorResponse errorResponse = null;
        if (ex instanceof ApiException) {

            ApiException apiException = (ApiException) ex;

            HttpStatus httpStatus = ApiHttpErrorsMapper.findHttpStatusByErrorCode(apiException.getType())
                    .orElse(ApiHttpErrorsMapper.APPLICATION_ERROR.getHttpStatus());

            errorResponse = buildErrorResponse(apiException.getCode(),
                    apiException.getUserMessage(),
                    apiException.getDebugMessage());

            response.setStatus(httpStatus.value());

        } else {

            errorResponse = buildErrorResponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.getUsrMsj(), GENERIC_ERROR.getDebugMsj());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        }

        return errorResponse;

    }

    private ErrorResponse buildErrorResponse(String code, String userMsj, String debugMsj) {
        return ErrorResponse.builder()
                .code(code)
                .userMessage(userMsj)
                .debugMessage(debugMsj)
                .build();
    }

}
