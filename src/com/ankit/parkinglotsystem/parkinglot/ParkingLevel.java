package com.ankit.parkinglotsystem.parkinglot;

import com.ankit.parkinglotsystem.enums.VehicleType;
import com.ankit.parkinglotsystem.model.ParkingSpot;
import com.ankit.parkinglotsystem.spotmanagers.ParkingSpotManager;

import java.util.Map;

public class ParkingLevel {
    private final int levelNumber;
    private final Map<VehicleType, ParkingSpotManager> managers;

    public ParkingLevel(int levelNumber, Map<VehicleType, ParkingSpotManager> managerMap) {
        this.levelNumber = levelNumber;
        this.managers = managerMap;
    }

    public boolean hasAvailability(VehicleType vehicleType) {
        ParkingSpotManager spotManager = managers.get(vehicleType);
        return spotManager != null && spotManager.hasFreeSpot();
    }

    public ParkingSpot park(VehicleType vehicleType) {
        ParkingSpotManager manager = managers.get(vehicleType);
        if (manager == null) {
            throw new IllegalArgumentException("No parking manager available for type " + vehicleType);
        }
        return manager.park();
    }

    public void unPark(VehicleType vehicleType, ParkingSpot parkingSpot) {
        ParkingSpotManager manager = managers.get(vehicleType);
        if (manager != null) {
            manager.unPark(parkingSpot);
        }
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    @Override
    public String toString() {
        return "ParkingLevel{" +
                "levelNumber=" + levelNumber +
                '}';
    }
}
