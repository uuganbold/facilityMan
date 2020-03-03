package edu.luc.comp473.facilityMan.business.exceptions;

/**
 * This exception is occurred when client try to access to an entity which does
 * not exist.
 */
public class EntityNotFoundException extends ApplicationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

}
