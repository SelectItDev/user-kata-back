package com.company.my_package.exception;

public class SearchFailedException extends Exception {

    public SearchFailedException() {
        super();
    }

    public SearchFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchFailedException(String message) {
        super(message);
    }

    public SearchFailedException(Throwable cause) {
        super(cause);
    }
}
