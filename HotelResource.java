package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    static public Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    static public void createACustomer(String email, String firstName, String lastName){
        CustomerService.addCustomer(firstName, lastName, email);
    }
    static public IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }
    static public void bookARoom(String customerEmail, IRoom room, Calendar checkInDate, Calendar checkOutDate){
        ReservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }
    public static Collection<Reservation> getCustomersReservations(String customerEmail){
        return ReservationService.getCustomerReservation(getCustomer(customerEmail));
    }
    public static Collection<IRoom> findARoom(Calendar checkInDate, Calendar checkOutDate){
        return ReservationService.findRooms(checkInDate, checkOutDate);
    }



}
