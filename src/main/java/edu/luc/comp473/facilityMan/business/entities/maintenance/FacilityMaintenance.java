package edu.luc.comp473.facilityMan.business.entities.maintenance;

import edu.luc.comp473.facilityMan.business.entities.facility.Facility;
import edu.luc.comp473.facilityMan.business.entities.util.Schedule;
import edu.luc.comp473.facilityMan.business.entities.util.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * FacilityMaintenance entity.
 */
public class FacilityMaintenance {
    private List<Problem> problems = new ArrayList<>();
    private Schedule schedule;
    private Status status;
    private Facility facility;
    private List<FacilityMaintenanceRequest> requests = new ArrayList<>();
    private List<FacilityMaintenanceOrder> orders = new ArrayList<>();

    /**
     * When we use ORM framework, we are not likely to have constructor like this.
     * But it may be fixed later.
     */
    public FacilityMaintenance(Schedule schedule, Facility facility) {
        this.schedule = schedule;
        this.facility = facility;
        status = Status.SCHEDULED;
    }

    public void addProblem(Problem problem) {
        this.problems.add(problem);
    }

    public void addRequest(FacilityMaintenanceRequest request) {
        this.requests.add(request);
    }

    public void addOrder(FacilityMaintenanceOrder order) {
        this.orders.add(order);
    }

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

    public List<FacilityMaintenanceRequest> getRequests() {
        return requests;
    }

    public List<FacilityMaintenanceOrder> getOrders() {
        return orders;
    }
}
