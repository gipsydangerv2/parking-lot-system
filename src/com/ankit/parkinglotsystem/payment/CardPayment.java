package com.ankit.parkinglotsystem.payment;

public class CardPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        System.out.println("Payment of Rs :" + amount + " done using Card ...");
        return true;
    }
}
