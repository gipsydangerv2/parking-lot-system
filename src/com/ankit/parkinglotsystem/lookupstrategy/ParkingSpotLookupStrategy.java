package com.ankit.parkinglotsystem.lookupstrategy;

import com.ankit.parkinglotsystem.model.ParkingSpot;

import java.util.List;

public interface ParkingSpotLookupStrategy {
    ParkingSpot selectSpot(List<ParkingSpot> spots);
}
