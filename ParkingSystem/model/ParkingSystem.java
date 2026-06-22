package model;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import enums.PriceCalculationType;

import factory.parking.ParkingFactory;
import factory.vehicle.Vehicle;
import strategy.parking.ParkingStrategy;

public class ParkingSystem {
   private List<ParkingFloor> floors;
   private Map<String, ParkingTicket> activeTicket = new ConcurrentHashMap<>();
   
    public synchronized ParkingTicket parkVehicle(Vehicle vehicle) {
        ParkingStrategy strategy = ParkingFactory.getParkingStrategy("Near");
        Optional<ParkingSpot> spotOpt = strategy.findSpot(floors, vehicle);

        if (spotOpt.isPresent()) {
            ParkingSpot spot = spotOpt.get();
            spot.assignVehicle(vehicle);

            String ticketId = "TKT-" + java.util.UUID.randomUUID().toString();
            ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, System.currentTimeMillis(), -1, spot, vehicle.getVehicleNumber());
            activeTicket.put(ticketId, ticket);
            return ticket;
        }
        return null;
    }

    public synchronized void processExit(String ticketId, PriceCalculationType pricingType) {
        ParkingTicket ticket = activeTicket.get(ticketId);
        if (ticket != null) {
            ticket.setExitTime(System.currentTimeMillis());
            double fee = factory.pricing.PriceFactory.calculatePrice(ticket, pricingType);
            ticket.getParkingSpot().removeVehicle();
            activeTicket.remove(ticketId);
            System.out.println("Vehicle " + ticket.getVehicleNumber() + " exited. Fee: " + fee);
        } else {
            System.out.println("Invalid Ticket ID: " + ticketId);
        }
    }

    public void setFloors(List<ParkingFloor> floors) {
        this.floors = floors;
    }
}
