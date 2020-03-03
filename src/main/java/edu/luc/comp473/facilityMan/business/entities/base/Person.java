package edu.luc.comp473.facilityMan.business.entities.base;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Person extends BaseEntity {

    private String firstName;
    private String lastName;
}