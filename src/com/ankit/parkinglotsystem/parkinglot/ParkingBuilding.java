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

    public Ticket allocate(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.hasAvailability(vehicle.getVehicleType())) {
                ParkingSpot spot = level.park(vehicle.getVehicleType());
                if (spot != null) {
                    Ticket ticket = new Ticket(vehicle, level, spot);
                    System.out.println("Parking allocated at level : "+ level + ", spot: " + spot);
                    return ticket;
                }
            }
        }
        throw new RuntimeException("Parking is full !!!");
    }

    public void release(Ticket ticket) {
        ticket.getLevel()
                .unPark(ticket.getVehicle().getVehicleType(),
                ticket.getSpot());
    }
}
