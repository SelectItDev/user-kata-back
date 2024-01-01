package com.company.my_package.exception;

public class ResourceNotSavedException extends Exception {

    public ResourceNotSavedException() {
        super();
    }

    public ResourceNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotSavedException(String message) {
        super(message);
    }

    public ResourceNotSavedException(Throwable cause) {
        super(cause);
    }
}
