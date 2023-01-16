package model;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;


public class Customer {
    public String firstName;
    public String lastName;
    public String email;

    public Customer(String firstName, String lastName, String email) throws Exception{
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        //String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        String regex = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$";
        boolean result = email.matches(regex);
        if (!result) throw new IllegalArgumentException("Please enter correct Email");
    }

    @Override
    public String toString() {
        return "model.Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
