package com.ankit.parkinglotsystem.model;

import com.ankit.parkinglotsystem.parkinglot.ParkingLevel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Ticket {
    private final Vehicle vehicle;
    private final ParkingLevel level;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;

    public Ticket(Vehicle vehicle, ParkingLevel level, ParkingSpot spot) {
        this.vehicle = vehicle;
        this.level = level;
        this.spot = spot;

        Instant instant = Instant.now();
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.entryTime = localDateTime;

    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingLevel getLevel() {
        return level;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "vehicle=" + vehicle +
                ", level=" + level +
                ", spot=" + spot +
                ", entryTime=" + entryTime +
                '}';
    }
}
