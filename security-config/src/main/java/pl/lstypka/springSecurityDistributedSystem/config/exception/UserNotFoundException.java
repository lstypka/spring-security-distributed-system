package pl.lstypka.springSecurityDistributedSystem.config.exception;

/**
 * Created by Łukasz on 2015-11-20.
 */
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }
}