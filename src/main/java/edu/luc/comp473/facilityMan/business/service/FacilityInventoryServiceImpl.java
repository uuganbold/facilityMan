package edu.luc.comp473.facilityMan.business.service;

import edu.luc.comp473.facilityMan.business.facility.Facility;

import java.util.ArrayList;
import java.util.List;

public class FacilityInventoryServiceImpl implements FacilityInventoryService {
    private List<Facility> allFacilities = new ArrayList<>();

    //Singleton instance of this class
    private static FacilityInventoryServiceImpl single_instance = null;

    private FacilityInventoryServiceImpl() {}

    public static FacilityInventoryServiceImpl getInstance()
    {
        if (single_instance == null)
            single_instance = new FacilityInventoryServiceImpl();

        return single_instance;
    }

    @Override
    public List<Facility> listFacilities() {
        return allFacilities;
    }

    public Facility getFacility(Long id){
        try{
            for (Facility f: allFacilities){
                if (f.getId() == id){
                    return f;
                }
            }
            return null;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void addNewFacility(Facility facility) {
        allFacilities.add(facility);
    }

    @Override
    public boolean removeFacility(Long id) {
        try{
            for (Facility f: allFacilities){
                if (f.getId() == id){
                    allFacilities.remove((f));
                    return true;
                }
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }
}
