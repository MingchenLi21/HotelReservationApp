package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CustomerService {
    private static CustomerService INSTANCE;
    private CustomerService(){
    }

    public static CustomerService getInstance(){
        if (INSTANCE == null){
            INSTANCE = new CustomerService();
        }

        return INSTANCE;
    }

    public static Map<String, Customer> customers = new HashMap<String, Customer>();

    static public void addCustomer(String firstName, String lastName, String email){
        customers.put(email, new Customer(firstName, lastName, email));
    }
    static public Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);
    }
    static public Collection<Customer> getAllCustomers(){
        ArrayList<Customer> custs = new ArrayList<>();
        for (Customer i : customers.values()) {
            custs.add(i);
        }
        return custs;
    }

}
