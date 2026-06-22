package factory.vehicle;

import enums.VehicleSize;
import factory.vehicle.Bike;
import factory.vehicle.Car;
import factory.vehicle.Truck;
import factory.vehicle.Vehicle;

public class VehicleFactory {
  public static Vehicle createVehicle(VehicleSize size, String number) {
    switch (size) {
      case SMALL:
        return new Bike(number);
      case MEDIUM:
        return new Car(number);
      case LARGE:
        return new Truck(number);
      default:
        throw new Error("Invalid vehicle type");
    }
  }
}
