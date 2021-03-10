package pl.klewek.springbootmusic.exception;

public final class UserDoesNotExistsException extends RuntimeException {

    public UserDoesNotExistsException() {
    }

    public UserDoesNotExistsException(String message) {
        super(message);
    }

    public UserDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDoesNotExistsException(Throwable cause) {
        super(cause);
    }
}
