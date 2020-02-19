package edu.luc.comp473.facilityMan.business.exceptions;

/**
 * Abstract Exception can be occurred with the system.
 */
public abstract class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }

}
