package pl.lstypka.springSecurityDistributedSystem.config.dto;

/**
 * Created by Lukasz Stypka on 2015-11-20.
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FaultDto {

    private String errorCode;
    private String message;

    @JsonCreator
    public FaultDto(@JsonProperty("errorCode") String errorCode, @JsonProperty("message") String message) {
        this.errorCode = errorCode;
        setMessage(message);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if(message == null) {
            message = "General error";
        }
        this.message = message;
    }

}