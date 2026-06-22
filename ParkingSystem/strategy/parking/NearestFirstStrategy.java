package strategy.parking;

import model.ParkingFloor;
import model.ParkingSpot;

import java.util.List;
import java.util.Optional;

import factory.vehicle.Vehicle;

public class NearestFirstStrategy implements ParkingStrategy {
    
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        for (ParkingFloor f: floors) {
            Optional<ParkingSpot> spot = f.getAvailableSpot(vehicle.getSize());
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}
