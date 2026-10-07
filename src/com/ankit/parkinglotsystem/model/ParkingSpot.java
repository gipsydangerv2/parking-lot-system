package com.ankit.parkinglotsystem.model;


public class ParkingSpot {
    private final String spotId;
    private boolean isFree = true;

    public ParkingSpot(String spotId) {
        this.spotId = spotId;
    }

    public String getSpotId() {
        return spotId;
    }

    public boolean isSpotFree() {
        return isFree;
    }

    public void occupySpot() {
        this.isFree = false;
    }

    public void releaseSpot() {
        this.isFree = true;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotId='" + spotId + '\'' +
                ", isFree=" + isFree +
                '}';
    }
}
