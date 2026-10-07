package com.ankit.parkinglotsystem.spotmanagers;

import com.ankit.parkinglotsystem.enums.VehicleType;
import com.ankit.parkinglotsystem.model.Vehicle;
import com.ankit.parkinglotsystem.model.ParkingSpot;
import com.ankit.parkinglotsystem.lookupstrategy.ParkingSpotLookupStrategy;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpotManager {

    protected final List<ParkingSpot> spots;
    protected final ParkingSpotLookupStrategy strategy;
    private final ReentrantLock lock = new ReentrantLock(true);

    public ParkingSpotManager(List<ParkingSpot> spots, ParkingSpotLookupStrategy strategy) {
        this.spots = spots;
        this.strategy = strategy;
    }

    public ParkingSpot park(){
        lock.lock();
        try {
            ParkingSpot spot = strategy.selectSpot(spots);
            if (spot == null) {
                return null;
            }
            spot.occupySpot();
            return spot;
        } finally {
            lock.unlock();
        }
    }

    public void unPark(ParkingSpot parkingSpot) {
        lock.lock();
        try {
            parkingSpot.releaseSpot();
        } finally {
            lock.unlock();
        }
    }
    public boolean hasFreeSpot() {
        lock.lock();
        try {
            return spots.stream().anyMatch(ParkingSpot::isSpotFree);
        } finally {
            lock.unlock();
        }
    }
}
