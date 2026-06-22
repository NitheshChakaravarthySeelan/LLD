package strategy.parking;

import model.ParkingFloor;
import factory.vehicle.Vehicle;
import java.util.Optional;
import java.util.List;
import model.ParkingSpot;

public interface ParkingStrategy {
   Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle); 
}