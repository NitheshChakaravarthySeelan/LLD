package model;

import factory.vehicle.Vehicle;

public class ParkingTicket {
  private String ticketId;
  private Vehicle vehicleType;
  private long entryTime;
  private long exitTime;
  private ParkingSpot parkingSpot;
  private String vehicleNumber;

  public ParkingTicket(String ticketId, Vehicle vehicleType, long entryTime, long exitTime, ParkingSpot parkingSpot, String vehicleNumber) {
    this.ticketId = ticketId;
    this.vehicleType = vehicleType;
    this.vehicleNumber = vehicleNumber;
    this.entryTime = entryTime;
    this.exitTime = exitTime;
    this.parkingSpot = parkingSpot;
  }

  public Vehicle getVehicleType() {
    return this.vehicleType;
  }

  public ParkingSpot getParkingSpot() {
    return this.parkingSpot;
  }

  public long getEntryTime() {
    return this.entryTime;
  }

  public long getExitTime() {
    return this.exitTime;
  }

  public void setExitTime(long exitTime) {
    this.exitTime = exitTime;
  }

  public String getTicketId() {
    return this.ticketId;
  }

  public String getVehicleNumber() {
    return this.vehicleNumber;
  }
}
