package edu.luc.comp473.facilityMan.business.exceptions;

public class InvalidUsageException extends ApplicationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidUsageException() {
    }

    public InvalidUsageException(String message) {
        super(message);
    }

}