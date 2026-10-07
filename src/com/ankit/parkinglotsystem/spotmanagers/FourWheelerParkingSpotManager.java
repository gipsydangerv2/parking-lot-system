package com.ankit.parkinglotsystem.spotmanagers;

import com.ankit.parkinglotsystem.model.ParkingSpot;
import com.ankit.parkinglotsystem.lookupstrategy.ParkingSpotLookupStrategy;

import java.util.List;

public class FourWheelerParkingSpotManager extends ParkingSpotManager {

    public FourWheelerParkingSpotManager(List<ParkingSpot> spots, ParkingSpotLookupStrategy strategy) {
        super(spots, strategy);
    }
}
