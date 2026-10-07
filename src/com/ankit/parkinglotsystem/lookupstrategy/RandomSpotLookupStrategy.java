package com.ankit.parkinglotsystem.lookupstrategy;

import com.ankit.parkinglotsystem.model.ParkingSpot;

import java.util.List;

public class RandomSpotLookupStrategy implements ParkingSpotLookupStrategy {
    @Override
    public ParkingSpot selectSpot(List<ParkingSpot> spots) {
        for (ParkingSpot spot : spots) {
            if (spot.isSpotFree()) {
                return spot;
            }
        }
        return null;
    }
}
