package strategy;

import enums.VehicleSize;
import model.ParkingTicket;

public class TimeBased implements FeeRates {

    @Override
    public double calculateFee(ParkingTicket ticket) {
        long entryTime = ticket.getEntryTime();
        long exitTime = ticket.getExitTime();
        long hours = (exitTime - entryTime) / 3600;
        VehicleSize size = ticket.getVehicleType().getSize();

        if(size == VehicleSize.SMALL) {
            return hours * 10.0;
        } else if (size == VehicleSize.MEDIUM) {
            return hours * 20.0;
        } else if (size == VehicleSize.LARGE) {
            return hours * 30.0;
        } else {
            throw new IllegalArgumentException("Invalid vehicle size: " + size);
        }
    }
}
