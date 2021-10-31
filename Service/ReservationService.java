package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService INSTANCE;
    private ReservationService(){
    }

    public static ReservationService getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ReservationService();
        }

        return INSTANCE;
    }

    public static HashMap<String, IRoom> rooms = new HashMap<String, IRoom>();
    public static HashSet<Reservation> reservations = new HashSet<>();

    public static void addRoom(IRoom room){
        rooms.put(room.getRoomNumber(), room);
    }

    static public IRoom getARoom(String roomID){
        return rooms.get(roomID);
    }

    static public void reserveARoom(Customer customer, IRoom room, Calendar checkInDate, Calendar checkOutDate){
        reservations.add(new Reservation(customer, room, checkInDate, checkOutDate));
    }

    public static Collection<IRoom> findRooms(Calendar checkInDate, Calendar checkOutDate){
        HashSet<IRoom> availables = new HashSet<IRoom>();

        for (IRoom room : rooms.values()){
            boolean available = true;
            for (Reservation reservation: reservations){
                if (room.equals(reservation.getRoom())){
                    available = false;
                    if (checkOutDate.before(reservation.getCheckInDate()) || checkInDate.after(reservation.getCheckOutDate())){
                        availables.add(room);
                    }
                }
            }
            if (available){
            availables.add(room);
            }
        }
        return availables;
    }

    static public Collection<Reservation> getCustomerReservation(Customer customer){
        ArrayList<Reservation> reser = new ArrayList<Reservation>();
        for (Reservation reservation : reservations){
            if (reservation.getCustomer() == customer){
                reser.add(reservation);
            }
        }
        return reser;
    }

    static public void printAllReservation(){
        System.out.println(reservations);
        }
    public static Collection<IRoom> getAllRooms(){
        return rooms.values();
    }




}