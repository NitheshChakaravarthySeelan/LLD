package model;

import enums.VehicleSize;
import factory.vehicle.Vehicle;

public class ParkingSpot {
  private String spotId;
  private String vehicleNumber;
  private boolean isOccupied;
  private final VehicleSize size;

  public ParkingSpot(String spotId, String vehicleNumber, boolean isOccupied, VehicleSize size) {
    this.spotId = spotId;
    this.vehicleNumber = vehicleNumber;
    this.isOccupied = isOccupied;
    this.size = size;
  }

  public boolean checkIsOccupied() {
    return this.isOccupied;
  }

  public VehicleSize getSize() {
    return this.size;
  }

  public void assignVehicle(Vehicle vehicle) {
    if (this.isOccupied) {
      throw new Error("Already occupied");
    }

    this.vehicleNumber = vehicle.getVehicleNumber();
    this.isOccupied = true;
  }

  public void removeVehicle() {
    if (!this.isOccupied) {
      throw new Error("Not occupied");
    }

    this.vehicleNumber = null;
    this.isOccupied = false;
  }

  public boolean canFit(Vehicle vehicle) {
    VehicleSize vehicleSize = vehicle.getSize();
    if (vehicleSize == this.size) {
      return true;
    }
    return false;
  }

}
