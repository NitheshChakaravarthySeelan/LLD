package model;

import java.util.Optional;
import factory.vehicle.Vehicle;

public class EntryGate {
    private String gateId;
    private ParkingSystem parkingSystem;

    public EntryGate(String gateId, ParkingSystem parkingSystem) {
        this.gateId = gateId;
        this.parkingSystem = parkingSystem;
    }
    
    public ParkingTicket processEntry(Vehicle vehicle) {
        return parkingSystem.parkVehicle(vehicle);
    }
}