package service;
import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {
    public static List<Customer>  customersList = new ArrayList<>();

    public static void addCustomer(String email, String firstName, String lastName) throws Exception {
        Customer customer = new Customer(firstName, lastName, email);
        customersList.add(customer);
    }

    public static Customer findCustomer(String customerEmail) {
        for (Customer temp : customersList) {
            if (temp.email.equals(customerEmail)) return temp;
        }
        System.out.println("Customer not found.");
        return null;
    }

    public static List<Customer> getAllCustomers() {
        return customersList;
    }
}
