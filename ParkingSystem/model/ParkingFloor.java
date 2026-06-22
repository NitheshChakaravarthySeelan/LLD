package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import enums.VehicleSize;

public class ParkingFloor {
    private String floorId;
    private List<ParkingSpot> parkingSpot;
    private int floorNumber;

    public ParkingFloor(String floorId, int floorNumber) {
        this.floorId = floorId;
        this.floorNumber = floorNumber;
        this.parkingSpot = new ArrayList<>();

    }

    public void addParkingSpot(ParkingSpot spot) {
        this.parkingSpot.add(spot);
    }

    public Optional<ParkingSpot> getAvailableSpot(VehicleSize size) {
        for (ParkingSpot spot: this.parkingSpot) {
            if (!spot.checkIsOccupied() && spot.getSize() == size) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }
}
