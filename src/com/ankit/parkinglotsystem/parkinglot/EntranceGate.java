package com.ankit.parkinglotsystem.parkinglot;

import com.ankit.parkinglotsystem.model.Ticket;
import com.ankit.parkinglotsystem.model.Vehicle;

public class EntranceGate {

    public Ticket enter(ParkingBuilding building, Vehicle vehicle) {
        return building.allocate(vehicle);
    }
}
