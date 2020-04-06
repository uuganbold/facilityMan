package edu.luc.comp473.facilityMan.business.base;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Person extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
}