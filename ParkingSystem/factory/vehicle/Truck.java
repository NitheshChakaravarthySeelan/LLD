package factory.vehicle;

import enums.VehicleSize;

public class Truck extends Vehicle {
  public Truck(String vehicleNumber) {
    super(vehicleNumber, VehicleSize.LARGE);
  }
}
