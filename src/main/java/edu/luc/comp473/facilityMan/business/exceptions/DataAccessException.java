package edu.luc.comp473.facilityMan.business.exceptions;

/**
 * Exception occurs when trying to connect database.
 */
public class DataAccessException extends ApplicationException {

    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }

}
