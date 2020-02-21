package edu.luc.comp473.facilityMan.business.entities.maintenance;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.business.entities.util.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintenance entity.
 */
public class Maintenance {
    private Long id;
    private List<Problem> problems = new ArrayList<>();
    private Schedule schedule;
    private Status status;
    private Facility facility;
    private List<MaintenanceRequest> requests = new ArrayList<>();
    private List<MaintenanceOrder> orders = new ArrayList<>();

    /**
     * When we use ORM framework, we are not likely to have constructor like this.
     * But it may be fixed later.
     */
    public Maintenance(Long id, Schedule schedule, Facility facility) {
        this.id = id;
        this.schedule = schedule;
        this.facility = facility;
        status = Status.SCHEDULED;
    }

    public void addProblem(Problem problem) {
        this.problems.add(problem);
    }

    public void addRequest(MaintenanceRequest request) {
        this.requests.add(request);
    }

    public void addOrder(MaintenanceOrder order) {
        this.orders.add(order);
    }

    public Long getId(){ return id; }

    public List<Problem> getProblems() {
        return problems;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Status getStatus() {
        return status;
    }

    public Facility getFacility() {
        return facility;
    }

    public List<MaintenanceRequest> getRequests() {
        return requests;
    }

    public List<MaintenanceOrder> getOrders() {
        return orders;
    }
}
