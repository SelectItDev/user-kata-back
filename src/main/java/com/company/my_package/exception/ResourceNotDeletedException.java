package com.company.my_package.exception;

public class ResourceNotDeletedException extends Exception {

    public ResourceNotDeletedException() {
        super();
    }

    public ResourceNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotDeletedException(String message) {
        super(message);
    }

    public ResourceNotDeletedException(Throwable cause) {
        super(cause);
    }
}
