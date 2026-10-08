package com.ankit.parkinglotsystem.parkinglot;

import com.ankit.parkinglotsystem.model.Ticket;
import com.ankit.parkinglotsystem.payment.Payment;
import com.ankit.parkinglotsystem.pricing.CostComputation;

public class ExitGate {
    private final CostComputation costComputation;

    public ExitGate(CostComputation costComputation) {
        this.costComputation = costComputation;
    }

    public void completeExit(ParkingBuilding building, Ticket ticket, Payment payment) {
        double amount = calculatePrice(ticket);
        boolean paymentStatus = payment.pay(amount);

        if (!paymentStatus) {
            throw new RuntimeException("Payment Failed. Exit denied.... Please retry");
        }
        building.releaseParkingSpot(ticket);
        System.out.println("Exit successful. Gate Opened !!!");
    }

    private double calculatePrice(Ticket ticket) {
        return costComputation.compute(ticket);
    }


}
