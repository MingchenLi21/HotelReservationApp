package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class MainMenu {
    public MainMenu() throws ParseException {
        boolean i = true;
        while (i){
            Scanner input = new Scanner(System.in);
            System.out.println("Main Menu");
            System.out.println("----------------");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            int inp;
            try {
                inp = input.nextInt();
                if (!(0 < inp && inp < 6)) {
                    throw new ArithmeticException("Invalid input");
                }
            } catch (Exception ex){
                System.out.println("Something went wrong");
                continue;
                }
            switch (inp) {
                case 1:
                    input = new Scanner(System.in);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    System.out.println("Enter the checkin date, dd-MM-yyyy");
                    Calendar checkInDate = Calendar.getInstance();

                    try {
                        checkInDate.setTime(formatter.parse(input.nextLine()));
                    } catch (Exception e){
                        System.out.println("Invalid input!");
                        continue;
                    }

                    input = new Scanner(System.in);
                    formatter = new SimpleDateFormat("dd-MM-yyyy");
                    System.out.println("Enter the checkout date, dd-MM-yyyy");
                    Calendar checkOutDate = Calendar.getInstance();;

                    try {
                        checkOutDate.setTime(formatter.parse(input.nextLine()));
                        if (checkInDate.after(checkOutDate)){
                            throw new ArithmeticException("Invalid input!");
                        }
                    }catch (Exception ex){
                        System.out.println("Invalid input!");
                        continue;
                    }

                    Collection<IRoom> rooms = HotelResource.findARoom(checkInDate, checkOutDate);
                    if (rooms.isEmpty()){
                        System.out.println("No available rooms during that period.");
                        checkInDate.add(Calendar.DATE, 7);
                        checkOutDate.add(Calendar.DATE, 7);

                        rooms = HotelResource.findARoom(checkInDate, checkOutDate);
                        if ((rooms.isEmpty())){
                            break;
                        }else {
                            System.out.println("There are some recommended rooms for other period.");
                            System.out.println("From");
                            System.out.println("Date: "+checkInDate.get(Calendar.DATE));
                            System.out.println("Month: "+checkInDate.get(Calendar.MONTH));
                            System.out.println("Year: "+checkInDate.get(Calendar.YEAR));
                            System.out.println("to ");
                            System.out.println("Date: "+checkOutDate.get(Calendar.DATE));
                            System.out.println("Month: "+checkOutDate.get(Calendar.MONTH));
                            System.out.println("Year: "+checkOutDate.get(Calendar.YEAR));
                            System.out.println(rooms);
                        }

                    }else{
                        System.out.println(rooms);
                    }

                    input = new Scanner(System.in);
                    System.out.println("Please enter the room number you want to reserve");

                    String roomNumber;

                    try {
                        roomNumber = input.nextLine();
                        if (!ReservationService.rooms.containsKey(roomNumber)){
                            throw new ArithmeticException("No such room!");
                        }
                    }catch (Exception ex){
                        System.out.println("Please enter the right room number!");
                        continue;
                    }

                    System.out.println("Please enter your email address");
                    String emailAddress;

                    try {
                        emailAddress = input.nextLine();
                        if (!CustomerService.customers.containsKey(emailAddress)){
                            throw new ArithmeticException("No such customers!");
                        }
                    }catch (Exception e){
                        System.out.println("Please enter the correct email address");
                        continue;
                    }

                    HotelResource.bookARoom(emailAddress, HotelResource.getRoom(roomNumber), checkInDate, checkOutDate);

                    System.out.println("room booked!");
                    continue;
                case 2:
                    input = new Scanner(System.in);
                    System.out.println("Please enter your email:");
                    String customerEmail;

                    try {
                        customerEmail = input.nextLine();
                        if (!CustomerService.customers.containsKey(customerEmail)){
                            throw new ArithmeticException("No such customers!");
                        }
                    }catch (Exception e){
                        System.out.println("Please enter the correct email address");
                        continue;
                    }

                    for (Reservation reservation: HotelResource.getCustomersReservations(customerEmail)){
                        System.out.println(reservation);
                    }
                    continue;

                case 3:
                    input = new Scanner(System.in);
                    System.out.println("Please enter you first name:");
                    String customerFirstName = input.nextLine();
                    System.out.println("Please enter you last name:");
                    String customerLastName = input.nextLine();
                    System.out.println("Please enter you email address:");
                    String customerEmailAddress = input.nextLine();

                    try{
                        String emailRegex = "^(.+)@(.+).(.+)$";
                        Pattern pattern = Pattern.compile(emailRegex);
                        if (!pattern.matcher(customerEmailAddress).matches()){
                            throw new IllegalArgumentException("The email addrres is wrong.");
                        }
                        if(CustomerService.customers.keySet().contains(customerEmailAddress)){
                            throw new IllegalArgumentException("The email addrres already existed.");
                        }
                    }catch (Exception e){
                        System.out.println("Something went wrong.");
                        continue;
                    }
                    HotelResource.createACustomer(customerEmailAddress, customerFirstName, customerLastName);
                    continue;
                case 4:
                    MainMenu.AdminMenu();
                    continue;
                case 5:
                    i = false;
                    break;
            }
        }
    }
    public static void AdminMenu(){
        boolean i = true;
        Scanner input = new Scanner(System.in);
        while (i) {
            System.out.println("Admin Menu");
            System.out.println("----------------");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to main Menu");
            input = new Scanner(System.in);
            int inp;
            try {
                inp = input.nextInt();
                if (!(0<inp && inp < 6)){
                    throw new ArithmeticException("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong.");
                continue;
            }
            switch (inp) {
                case 1:
                    for (Customer customer : AdminResource.getAllCustomer()) {
                        System.out.println(customer);
                    }
                    break;

                case 2:
                    for (IRoom room : AdminResource.getAllRooms()) {
                        System.out.println(room);
                    }
                    break;
                case 3:
                    AdminResource.displayAllReservations();
                    break;
                case 4:
                    input = new Scanner(System.in);
                    String roomnumber;
                    int roomtype;
                    Double roomprice;
                    System.out.println("Please enter room number");
                    roomnumber = input.nextLine();

                    System.out.println("Please enter room type; 1 for SINGLE, 2 for DOUBLE");
                    try {
                        roomtype = input.nextInt();
                    } catch (InputMismatchException ex) {
                        System.out.println("Invalid input!");
                        continue;
                    }

                    try {
                        if (roomtype != 1 && roomtype != 2){
                            throw new ArithmeticException("Please enter the right room type.");
                        }
                    }catch (Exception ex){
                        System.out.println("Please enter the right room type.");
                        continue;
                    }

                    System.out.println("Please enter room price");
                    roomprice = input.nextDouble();


                    if (roomtype == 1) {
                        ReservationService.rooms.put(roomnumber, new Room(roomnumber, RoomType.SINGLE, roomprice));
                    } else {
                        ReservationService.rooms.put(roomnumber, new Room(roomnumber, RoomType.DOUBLE, roomprice));
                    }
                    continue;
                case 5:
                    i = false;
                    break;
            }
            }


        }

    }
