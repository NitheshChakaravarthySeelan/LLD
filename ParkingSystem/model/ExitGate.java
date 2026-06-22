package model;

import enums.PriceCalculationType;
import factory.pricing.PriceFactory;

public class ExitGate {
    private String gateId;
    private ParkingSystem parkingSystem;

    public ExitGate(String gateId, ParkingSystem parkingSystem) {
        this.gateId = gateId;
        this.parkingSystem = parkingSystem;
    }

    public void processExit(String ticketId, PriceCalculationType pricingType) {
        parkingSystem.processExit(ticketId, pricingType);
    }
}
