package factory.vehicle;

import enums.VehicleSize;

public abstract class Vehicle {
  private final String vehicleNumber;
  private final VehicleSize size;

  public Vehicle(String vehicleNumber, VehicleSize size) {
    this.vehicleNumber = vehicleNumber;
    this.size = size;
  }

  public String getVehicleNumber() {
    return this.vehicleNumber;
  }

  public VehicleSize getSize() {
    return this.size;
  }
}
