package strategy.parking;

import java.util.Optional;
import model.ParkingSpot;
import model.ParkingFloor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import factory.vehicle.Vehicle;

public class FarthestFirstStrategy implements ParkingStrategy {
    
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floor, Vehicle vehicle) {
         // We need to reverser to search from the top floor
         List<ParkingFloor> reversedFloor = new ArrayList<>(floor);
         Collections.reverse(reversedFloor);
         
         for (ParkingFloor f: reversedFloor) {
            Optional<ParkingSpot> spot = f.getAvailableSpot(vehicle.getSize());
            if (spot.isPresent()) {
                return spot;
            }
         }
         return Optional.empty();
    } 
}
