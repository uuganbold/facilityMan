package edu.luc.comp473.facilityMan.business.entities.util;

/**
 * Status for Maintenance.
 */
public enum Status {
    /**
     * State on which Maintenance is scheduled.
     */
    SCHEDULED,

    /**
     * State on which Maintenance is happening and in progress.
     */
    IN_PROGRESS,

    /**
     * State on which Maintenance has completed.
     */
    COMPLETED
}
