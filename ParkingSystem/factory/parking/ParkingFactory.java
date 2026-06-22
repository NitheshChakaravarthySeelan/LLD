package factory.parking;

import strategy.parking.FarthestFirstStrategy;
import strategy.parking.NearestFirstStrategy;
import strategy.parking.ParkingStrategy;

public class ParkingFactory {
    public static ParkingStrategy getParkingStrategy(String type) {
        if (type.equals("Far")) {
            return new FarthestFirstStrategy();
        } else if (type.equals("Near")) {
            return new NearestFirstStrategy();
        } else {
            throw new Error("Illegal parking strategy");
        }
    }
}
