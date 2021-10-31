package model;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Calendar checkInDate;
    private final Calendar checkOutDate;

    public Reservation(Customer customer, IRoom room, Calendar checkInDate, Calendar checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public IRoom getRoom(){
        return room;
    }
    public Calendar getCheckInDate() {
        return checkInDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Calendar getCheckOutDate() {
        return checkOutDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(customer, that.customer) && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, checkInDate, checkOutDate);
    }

    @Override
    public String toString(){
        return "The room is reserved for " + customer.getFirstName() + customer.getLastName() + "\n"
                + room.toString() + "\n"
                + "Check-in date: " + checkInDate + "\n"
                + "Check-out date: " + checkOutDate + "\n";
    }
}
