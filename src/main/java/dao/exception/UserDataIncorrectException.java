package dao.exception;

public class UserDataIncorrectException extends RuntimeException {
    public UserDataIncorrectException() {
        super();
    }

    public UserDataIncorrectException(String message) {
        super(message);
    }

    public UserDataIncorrectException(Throwable cause) {
        super(cause);
    }

}
