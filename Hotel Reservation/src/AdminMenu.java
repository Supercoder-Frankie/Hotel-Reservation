import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public static Scanner scanner;
    public static AdminResource adminResource = AdminResource.getInstance();

    public static void startAdmin() {
        scanner = new Scanner(System.in);
        try {
            boolean exit = false;
            while (!exit) {
                int choiceAdmin = displayAdminMenu();
                switch (choiceAdmin) {
                    case 1 -> System.out.println(adminResource.getAllCustomers());
                    case 2 -> System.out.println(adminResource.getAllrooms());
                    case 3 -> adminResource.displayAllReservations();
                    case 4 -> addRoom();
                    case 5 -> exit = true;
                    default -> displayAdminMenu();
                }
            }
            // !!! 保证程序返回到MainMenu后能够继续执行,使程序回到MainMenu中的main方法中
            String[] arguments = new String[] {""};
            MainMenu.main(arguments);
        } catch (Exception ex) {
            ex.getLocalizedMessage();
        } finally {
            scanner.close();
        }
    }

    // !!!
    public static void setAdmni(AdminResource adminResource) {
        AdminMenu.adminResource = adminResource;
    }

    private static void addRoom() {
        Room room = new Room();
        System.out.println("Please enter the room number:");
        room.roomNumber = scanner.next();
        for (IRoom tempRoom : adminResource.getAllrooms()) {
            if (tempRoom.getRoomNumber().equals(room.roomNumber)) {
                System.out.println("This room number already exists, please try another one.");
                return;
            }
        }
        System.out.println("Please enter the price per night:");
        room.price = scanner.nextDouble();
        System.out.println("Please enter the type of the room: 1 for SINGLE and 2 for DOUBLE bed:");
        int type = scanner.nextInt();

        room.roomType = type == 1? RoomType.SINGLE : RoomType.DOUBLE;
        adminResource.addRoom(room);
        System.out.println("Room added successfully.");
    }

    public static int displayAdminMenu() {
        System.out.println("Admin Menu");
        System.out.println("--------------------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("--------------------------------------------------");
        System.out.println("Please select a number for the menu option");
        System.out.println("Enter menu:");
        return scanner.nextInt();
    }

}
