package strategy;

import model.ParkingTicket;

public interface FeeRates {
    double calculateFee(ParkingTicket ticket);
}
