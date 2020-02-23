package edu.luc.comp473.facilityMan.business.entities.maintenance;

import java.math.BigDecimal;

/**
 * Entity MaintenanceOrder.
 */
public class MaintenanceOrder {
    private BigDecimal totalCost;
    private String description;

    public MaintenanceOrder(BigDecimal totalCost, String description) {
        this.totalCost = totalCost;
        this.description = description;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
