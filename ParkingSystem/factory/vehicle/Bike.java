package factory.vehicle;

import enums.VehicleSize;

public class Bike extends Vehicle {
  public Bike(String vehicleNumber) {
    super(vehicleNumber, VehicleSize.SMALL);
  }
}
