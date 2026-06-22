package factory.vehicle;

import enums.VehicleSize;

public class Car extends Vehicle {
  public Car(String vehicleNumber) {
    super(vehicleNumber, VehicleSize.MEDIUM);
  }
}
