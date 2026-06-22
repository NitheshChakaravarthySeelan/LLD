import model.*;
import enums.*;
import factory.vehicle.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize Parking System
        ParkingSystem parkingSystem = new ParkingSystem();

        // 2. Setup Floors and Spots
        ParkingFloor floor1 = new ParkingFloor("FLOOR-1", 1);
        floor1.addParkingSpot(new ParkingSpot("SPOT-1", null, false, VehicleSize.MEDIUM));
        floor1.addParkingSpot(new ParkingSpot("SPOT-2", null, false, VehicleSize.MEDIUM));
        
        List<ParkingFloor> floors = new ArrayList<>();
        floors.add(floor1);
        parkingSystem.setFloors(floors);

        // 3. Setup Gates
        EntryGate entryGate1 = new EntryGate("GATE-E1", parkingSystem);
        ExitGate exitGate1 = new ExitGate("GATE-X1", parkingSystem);

        // 4. Test Entry
        System.out.println("--- Testing Entry ---");
        Vehicle car1 = new Car("KA-01-1234");
        ParkingTicket ticket1 = entryGate1.processEntry(car1);
        if (ticket1 != null) {
            System.out.println("Car 1 parked. Ticket ID: " + ticket1.getTicketId());
        }

        Vehicle car2 = new Car("KA-02-5678");
        ParkingTicket ticket2 = entryGate1.processEntry(car2);
        if (ticket2 != null) {
            System.out.println("Car 2 parked. Ticket ID: " + ticket2.getTicketId());
        }

        // Test Full Parking
        Vehicle car3 = new Car("KA-03-9999");
        ParkingTicket ticket3 = entryGate1.processEntry(car3);
        if (ticket3 == null) {
            System.out.println("Correctly identified: No spots for Car 3");
        }

        // 5. Test Exit
        System.out.println("\n--- Testing Exit ---");
        try {
            // Wait a bit to simulate parking duration
            Thread.sleep(1000); 
        } catch (InterruptedException e) {}

        exitGate1.processExit(ticket1.getTicketId(), PriceCalculationType.TIME_BASED);
        
        // After exit, spot should be free. Try parking car 3 again.
        System.out.println("\n--- Testing Re-parking after Exit ---");
        ticket3 = entryGate1.processEntry(car3);
        if (ticket3 != null) {
            System.out.println("Car 3 parked successfully in the freed spot. Ticket ID: " + ticket3.getTicketId());
        }
    }
}
