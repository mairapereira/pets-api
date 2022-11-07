package py.com.pegasus.test.pets.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ApiException extends Exception {
    private String code;
    private String userMessage;
    private String debugMessage;
    private ApiExceptionType type;

    public ApiException(String code, String userMessage, String debugMessage, ApiExceptionType type) {
        super(debugMessage);
        this.code = code;
        this.userMessage = userMessage;
        this.debugMessage = debugMessage;
        this.type = type;
    }
}