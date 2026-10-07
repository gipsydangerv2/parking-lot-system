package com.ankit.parkinglotsystem.pricing;

import com.ankit.parkinglotsystem.model.Ticket;

public interface PricingStrategy {
    double calculate(Ticket ticket);
}
