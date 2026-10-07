package com.ankit.parkinglotsystem.payment;

public class UPIPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        System.out.println("Payment of Rs :" + amount + " done using UPI ...");
        return true;
    }
}
