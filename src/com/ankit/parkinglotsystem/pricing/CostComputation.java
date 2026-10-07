package com.ankit.parkinglotsystem.pricing;

import com.ankit.parkinglotsystem.model.Ticket;

public class CostComputation {
    private PricingStrategy pricingStrategy;

    public CostComputation(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double compute(Ticket ticket) {
        return pricingStrategy.calculate(ticket);
    }
}
