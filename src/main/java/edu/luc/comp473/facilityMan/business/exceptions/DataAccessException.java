package edu.luc.comp473.facilityMan.business.exceptions;

/**
 * Exception occurs when trying to connect database.
 */
public class DataAccessException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }

}
