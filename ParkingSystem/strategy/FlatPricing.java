package strategy;

import enums.VehicleSize;
import model.ParkingTicket;

public class FlatPricing implements FeeRates {

    @Override
    public double calculateFee(ParkingTicket ticket) {
        VehicleSize size = ticket.getVehicleType().getSize();

        switch(size) {
            case SMALL: return 10.0;
            case MEDIUM: return 20.0;
            case LARGE: return 30.0;
            default: throw new IllegalArgumentException("Invalid vehicle size: " + size);
        }
    }
}
