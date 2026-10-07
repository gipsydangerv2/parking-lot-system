package com.ankit.parkinglotsystem.pricing;

import com.ankit.parkinglotsystem.model.Ticket;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class HourlyPricingStrategy implements PricingStrategy {
    @Override
    public double calculate(Ticket ticket) {
        Instant instant = Instant.now();
        LocalDateTime end = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Calculates total hours between start and end
        long hours = ChronoUnit.HOURS.between(ticket.getEntryTime(), end);
        System.out.println("Total hours: " + hours);
        return calculateCost(hours);
    }

    private double calculateCost(long hours) {
        if (hours < 1) {
            return 50.0d;
        } else if (hours <= 2) {
            return 100.0d;
        } else {
            return 250.0d;
        }
    }
}
