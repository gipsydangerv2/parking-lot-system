package com.ankit.parkinglotsystem.parkinglot;

import com.ankit.parkinglotsystem.model.Ticket;
import com.ankit.parkinglotsystem.model.Vehicle;
import com.ankit.parkinglotsystem.payment.Payment;

public class ParkingLot {
    private final ParkingBuilding building;
    private final EntranceGate entranceGate;
    private final ExitGate exitGate;

    public ParkingLot(ParkingBuilding building, EntranceGate entranceGate, ExitGate exitGate) {
        this.building = building;
        this.entranceGate = entranceGate;
        this.exitGate = exitGate;
    }

    public Ticket vehicleArrives(Vehicle vehicle) {
        return entranceGate.enter(building, vehicle);
    }

    public void vehicleExists(Ticket ticket, Payment payment) {
        exitGate.completeExit(building, ticket, payment);
    }
}
