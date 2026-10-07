package com.ankit.parkinglotsystem.pricing;

import com.ankit.parkinglotsystem.model.Ticket;

public class FixedPricingStrategy implements PricingStrategy {
    @Override
    public double calculate(Ticket ticket) {
        return 100.0;
    }
}
