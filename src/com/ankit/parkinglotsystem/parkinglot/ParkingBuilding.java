package com.ankit.parkinglotsystem.parkinglot;

import com.ankit.parkinglotsystem.model.ParkingSpot;
import com.ankit.parkinglotsystem.model.Ticket;
import com.ankit.parkinglotsystem.model.Vehicle;

import java.util.List;

public class ParkingBuilding {

    private final List<ParkingLevel> levels;

    public ParkingBuilding(List<ParkingLevel> levels) {
        this.levels = levels;
    }

    public Ticket allocateParkingSpot(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
                ParkingSpot spot = level.park(vehicle.getVehicleType());
                if (spot != null) {
                    Ticket ticket = new Ticket(vehicle, level, spot);
                    System.out.println("Parking allocated at level : "+ level + ", spot: " + spot);
                    return ticket;
                }
        }
        throw new RuntimeException("Parking is full !!!");
    }

    public void releaseParkingSpot(Ticket ticket) {
        ticket.getLevel()
                .unPark(ticket.getVehicle().getVehicleType(),
                ticket.getSpot());
    }
}
