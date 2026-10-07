package com.ankit.parkinglotsystem.spotmanagers;

import com.ankit.parkinglotsystem.model.ParkingSpot;
import com.ankit.parkinglotsystem.lookupstrategy.ParkingSpotLookupStrategy;

import java.util.List;

public class TwoWheelerParkingSpotManager extends ParkingSpotManager {

    public TwoWheelerParkingSpotManager(List<ParkingSpot> spots, ParkingSpotLookupStrategy strategy) {
        super(spots, strategy);
    }
}
