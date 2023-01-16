import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;

import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    public static Scanner scanner;
    // 创建相应的实例
    public static HotelResource hotelResource = HotelResource.getInstance();
    public static AdminResource adminResource = AdminResource.getInstance();


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Welcome to the Hotel Resversation Application");
        try {
            boolean exit = false;
            while (!exit) {
                int choice = displayMainMenu();
                switch (choice) {
                    case 1 -> findAndReserveARoom();
                    case 2 -> seeReservations();
                    case 3 -> createAccount();
                    case 4 -> {
                        // 此步骤实现；两个java文件之间操作的跳转操作
                        AdminMenu.setAdmni(adminResource);
                        AdminMenu.startAdmin();
                    }
                    case 5 -> exit = true;
                    default -> {
                        System.out.println("Please enter the right number.");
                        displayMainMenu();
                    }
                }
            }
            System.exit(0);
        } catch (Exception e) {
            e.getLocalizedMessage();
        } finally {
            scanner.close();
        }

    }

    private static Customer createAccount() throws Exception {
        // 在此处创建一个新的customer
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email:");
        String custEmail = scanner.next();
        System.out.println("Please enter your first name:");
        String fistName = scanner.next();
        System.out.println("Please enter your last name:");
        String lastName = scanner.next();
        // try catch 语句需要添加
        try {
            hotelResource.createACustomer(custEmail, fistName, lastName);
            System.out.println("Welcome! " + fistName + " " + lastName + ", your account has been successfully created.");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return hotelResource.getCustomer(custEmail);
    }

    private static void seeReservations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the customer email you want to check:");
        String custEmail = scanner.next();
        System.out.println(hotelResource.getCustomerReservations(custEmail));
    }

    private static void findAndReserveARoom() throws Exception {
        scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter CheckIn Date dd/MM/yyyy example (14/01/2023)");
        Date checkInDate = dateFormat.parse(scanner.nextLine());
        System.out.println("Enter CheckOut Date dd/MM/yyyy example (15/01/2023)");
        Date checkOutDate = dateFormat.parse(scanner.nextLine());
        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);

        if (!availableRooms.isEmpty()) {
            Customer customer;
            System.out.println("Woud you like to book a room? y/n");
            char optionBookARoom = scanner.next().trim().charAt(0);
            if (optionBookARoom == 'y') {
                System.out.println("Are you a current member of us? y/n");
                char optionMembership = scanner.next().trim().charAt(0);
                if (optionMembership == 'y') {
                    System.out.println("Enter Email format: name@domain.com");
                    String custEmail = scanner.next().trim();
                    customer = hotelResource.getCustomer(custEmail);

                    if (customer == null) {
                        System.out.println("Cumstomer was not found.");
                        return;
                    }
                } else {
                    customer = createAccount();
                }

                boolean isRoomAvailable = false;
                while (!isRoomAvailable) {
                    System.out.println("Available Rooms:");
                    System.out.println(availableRooms);
                    System.out.println("Please enter the room number from available rooms:");
                    String selectedRoomNum = scanner.next().trim();
                    IRoom selectedRoom = hotelResource.getRoom(selectedRoomNum);

                    if (!availableRooms.contains(selectedRoom)) {
                        System.out.println("Sorry, room number " + selectedRoomNum + " is not available.");
                    } else {
                        hotelResource.bookARoom(customer.email, selectedRoom, checkInDate, checkOutDate);
                        System.out.println("Your room has been successfully booked!");
                        isRoomAvailable = true;
                    }
                }
            }
        } else {
            Collection<IRoom> availableRooms2 = hotelResource.findARoom(addDay(checkInDate,7), addDay(checkOutDate,7));
            if (!availableRooms2.isEmpty()) {
                Customer customer;
                System.out.println("I'm sorry, there is no room available at this time, but we have found a room for you next week. Woud you like to book a room? y/n");
                char optionBookARoom = scanner.next().trim().charAt(0);
                if (optionBookARoom == 'y') {
                    System.out.println("Are you a current member of us? y/n");
                    char optionMembership = scanner.next().trim().charAt(0);
                    if (optionMembership == 'y') {
                        System.out.println("Enter Email format: name@domain.com");
                        String custEmail = scanner.next().trim();
                        customer = hotelResource.getCustomer(custEmail);

                        if (customer == null) {
                            System.out.println("Cumstomer was not found.");
                            return;
                        }
                    } else {
                        customer = createAccount();
                    }

                    boolean isRoomAvailable = false;
                    while (!isRoomAvailable) {
                        System.out.println("Available Rooms:");
                        System.out.println(availableRooms2);
                        System.out.println("Please enter the room number from available rooms:");
                        String selectedRoomNum = scanner.next().trim();
                        IRoom selectedRoom = hotelResource.getRoom(selectedRoomNum);

                        if (!availableRooms2.contains(selectedRoom)) {
                            System.out.println("Sorry, room number " + selectedRoomNum + " is not available.");
                        } else {
                            hotelResource.bookARoom(customer.email, selectedRoom, checkInDate, checkOutDate);
                            System.out.println("Your room has been successfully booked!");
                            isRoomAvailable = true;
                        }
                    }
                }
            } else {
                System.out.println("Sorry there are no available rooms");
            }
        }
    }


    private static int displayMainMenu(){
        System.out.println("--------------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("--------------------------------------------------");
        System.out.println("Please select a number for the menu option");
        System.out.println("Enter menu:");
        return scanner.nextInt();
    }

    public static Date addDay(Date date,int i){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, i);
        Date newDate = c.getTime();
        return newDate;
    }

}
