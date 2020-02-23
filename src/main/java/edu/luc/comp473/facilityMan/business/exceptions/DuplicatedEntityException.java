package edu.luc.comp473.facilityMan.business.exceptions;

/**
 * If client try to add same entity already existing in the system, this
 * exception will be throwed.
 */
public class DuplicatedEntityException extends ApplicationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DuplicatedEntityException() {
    }

    public DuplicatedEntityException(String message) {
        super(message);
    }

}
