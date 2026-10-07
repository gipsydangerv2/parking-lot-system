package com.ankit.parkinglotsystem;

import com.ankit.parkinglotsystem.enums.VehicleType;
import com.ankit.parkinglotsystem.lookupstrategy.ParkingSpotLookupStrategy;
import com.ankit.parkinglotsystem.lookupstrategy.RandomSpotLookupStrategy;
import com.ankit.parkinglotsystem.model.ParkingSpot;
import com.ankit.parkinglotsystem.model.Ticket;
import com.ankit.parkinglotsystem.model.Vehicle;
import com.ankit.parkinglotsystem.parkinglot.*;
import com.ankit.parkinglotsystem.payment.CardPayment;
import com.ankit.parkinglotsystem.payment.Payment;
import com.ankit.parkinglotsystem.payment.UPIPayment;
import com.ankit.parkinglotsystem.pricing.CostComputation;
import com.ankit.parkinglotsystem.pricing.FixedPricingStrategy;
import com.ankit.parkinglotsystem.pricing.HourlyPricingStrategy;
import com.ankit.parkinglotsystem.pricing.PricingStrategy;
import com.ankit.parkinglotsystem.spotmanagers.ParkingSpotManager;

import java.util.*;

public class ParkingLotClient {

    public static void main(String[] args) {
        System.out.println("Hello, welcome to Parking Lot Client Class....");

        ParkingSpotLookupStrategy randomStrategy = new RandomSpotLookupStrategy();

        // ParkingLot => has ParkingBuilding
        // ParkingBuilding => has ParkingLevel
        // ParkingLevel => has multiple parking spots of different VehicleType
        // ParkingLevel => for different VehicleType parking spot, there are different ParkingSpotManagers.

        // For Level L1

        String[] spotNames = {"L1-TWO-1", "L1-TWO-2", "L1-TWO-3", "L1-TWO-4", "L1-TWO-5"};
        List<ParkingSpot> twoWheelerSpots = prepareParkingSpotList(spotNames);
        ParkingSpotManager twoWheelerManager = new ParkingSpotManager(twoWheelerSpots, randomStrategy);

        Map<VehicleType, ParkingSpotManager> spotManagerMap = new HashMap<>();
        spotManagerMap.put(VehicleType.TWO_WHEELER, twoWheelerManager);

        String[] fourWheelerSpotNames = {"L1-FOUR-1", "L1-FOUR-2", "L1-FOUR-3", "L1-FOUR-4", "L1-FOUR-5"};
        List<ParkingSpot> fourWheelerSpots = prepareParkingSpotList(fourWheelerSpotNames);

        ParkingSpotManager fourWheelerManager = new ParkingSpotManager(fourWheelerSpots, randomStrategy);
        spotManagerMap.put(VehicleType.FOUR_WHEELER, fourWheelerManager);

        ParkingLevel parkingLevel = new ParkingLevel(1, spotManagerMap);

        // For Level L2

        String[] spotNames2 = {"L2-TWO-1", "L2-TWO-2", "L2-TWO-3", "L2-TWO-4", "L2-TWO-5"};
        List<ParkingSpot> twoWheelerSpotsV2 = prepareParkingSpotList(spotNames2);
        ParkingSpotManager twoWheelerManagerV2 = new ParkingSpotManager(twoWheelerSpotsV2, randomStrategy);

        Map<VehicleType, ParkingSpotManager> spotManagerMapV2 = new HashMap<>();
        spotManagerMapV2.put(VehicleType.TWO_WHEELER, twoWheelerManagerV2);

        String[] fourWheelerSpotNamesV2 = {"L1-FOUR-1", "L1-FOUR-2", "L1-FOUR-3", "L1-FOUR-4", "L1-FOUR-5"};
        List<ParkingSpot> fourWheelerSpotsV2 = prepareParkingSpotList(fourWheelerSpotNamesV2);

        ParkingSpotManager fourWheelerManagerV2 = new ParkingSpotManager(fourWheelerSpotsV2, randomStrategy);
        spotManagerMapV2.put(VehicleType.FOUR_WHEELER, fourWheelerManagerV2);

        ParkingLevel parkingLevelV2 = new ParkingLevel(2, spotManagerMapV2);


        ParkingBuilding parkingBuilding = new ParkingBuilding(List.of(parkingLevel, parkingLevelV2));

        PricingStrategy fixedPricingStrategy = new FixedPricingStrategy();
        PricingStrategy houlyPricingStrategy = new HourlyPricingStrategy();

        CostComputation costComputation = new CostComputation(fixedPricingStrategy);
        Payment upiPayment = new UPIPayment();

        EntranceGate entranceGate = new EntranceGate();
        ExitGate exitGate = new ExitGate(costComputation);

        ParkingLot parkingLot = new ParkingLot(parkingBuilding, entranceGate, exitGate);

        Vehicle vehicle1 = new Vehicle("ABC-120-CDF", VehicleType.TWO_WHEELER);
        Ticket ticket1 = parkingLot.vehicleArrives(vehicle1);

        Vehicle vehicle2 = new Vehicle("ACB-200-CDF", VehicleType.FOUR_WHEELER);
        Ticket ticket2 = parkingLot.vehicleArrives(vehicle2);

        Vehicle vehicle3 = new Vehicle("ABC-121-CDF", VehicleType.TWO_WHEELER);
        parkingLot.vehicleArrives(vehicle3);

        Vehicle vehicle4 = new Vehicle("ACB-201-CDF", VehicleType.FOUR_WHEELER);
        parkingLot.vehicleArrives(vehicle4);

        Vehicle vehicle5 = new Vehicle("ABC-122-CDF", VehicleType.TWO_WHEELER);
        parkingLot.vehicleArrives(vehicle5);

        Vehicle vehicle6 = new Vehicle("ACB-202-CDF", VehicleType.FOUR_WHEELER);
        parkingLot.vehicleArrives(vehicle6);

        Vehicle vehicle7 = new Vehicle("ABC-123-CDF", VehicleType.TWO_WHEELER);
        parkingLot.vehicleArrives(vehicle7);

        Vehicle vehicle8 = new Vehicle("ACB-204-CDF", VehicleType.FOUR_WHEELER);
        parkingLot.vehicleArrives(vehicle8);

        Vehicle vehicle9 = new Vehicle("ABC-125-CDF", VehicleType.TWO_WHEELER);
        parkingLot.vehicleArrives(vehicle9);

        Vehicle vehicle10 = new Vehicle("ACB-206-CDF", VehicleType.FOUR_WHEELER);
        parkingLot.vehicleArrives(vehicle10);

        Vehicle vehicle11 = new Vehicle("ACB-209-CDF", VehicleType.FOUR_WHEELER);
        parkingLot.vehicleArrives(vehicle11);

        parkingLot.vehicleExists(ticket1, upiPayment);
        parkingLot.vehicleExists(ticket2, new CardPayment());

    }

    private static List<ParkingSpot> prepareParkingSpotList(String[] spotNames) {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        for (String spotName : spotNames) {
            parkingSpots.add(new ParkingSpot(spotName));
        }
        return parkingSpots;
    }
}
