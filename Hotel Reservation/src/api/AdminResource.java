package api;

import model.Customer;
import model.IRoom;
import java.util.Collection;
import java.util.List;
import static service.CustomerService.*;
import static service.ReservationService.*;

public class AdminResource {
    private static AdminResource adminResource = null;

    private AdminResource() {
    }
    public static AdminResource getInstance() {
        if (adminResource == null) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public static Customer getCustomer (String email) {
        return findCustomer(email);
    }

    // TODO 可视情况改为for循环判断room是否重复
    public void addRoom(IRoom room) {
        roomList.add(room);
    }

    public List<IRoom> getAllrooms() {
        return roomList;
    }

    public List<Customer> getAllCustomers() {
        return customersList;
    }

    public void displayAllReservations() {
        System.out.println(reservationList);
    }


}
