import java.util.Scanner;
h1"sai";
class Room {

    int roomNumber;
    String roomType;
    double price;
    boolean available;

    Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.available = true;
    }

    void displayRoom() {
        System.out.println(
            roomNumber + " | " +
            roomType + " | Rs." +
            price + "/night | " +
            (available ? "Available" : "Booked")
        );
    }
}


class Customer {

    String name;
    String phone;
    String email;
    int roomNumber;
    int nights;

    double roomBill;
    double foodBill;
    double serviceBill;

    Customer(String name, String phone, String email,
             int roomNumber, int nights, double roomBill) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.roomNumber = roomNumber;
        this.nights = nights;
        this.roomBill = roomBill;

        this.foodBill = 0;
        this.serviceBill = 0;
    }

    double getTotalBill() {
        return roomBill + foodBill + serviceBill;
    }

    void displayCustomer() {

        System.out.println("\n----------------------------------");
        System.out.println("        CUSTOMER DETAILS");
        System.out.println("----------------------------------");

        System.out.println("Name       : " + name);
        System.out.println("Phone      : " + phone);
        System.out.println("Email      : " + email);
        System.out.println("Room       : " + roomNumber);
        System.out.println("Nights     : " + nights);
        System.out.println("Room Bill  : Rs." + roomBill);
        System.out.println("Food Bill  : Rs." + foodBill);
        System.out.println("Services   : Rs." + serviceBill);
        System.out.println("Total Bill : Rs." + getTotalBill());
    }
}


public class HotelManagement {

    static Scanner sc = new Scanner(System.in);

    static Room[] rooms = {

        new Room(101, "Single", 1500),
        new Room(102, "Single", 1500),
        new Room(103, "Single", 1500),

        new Room(201, "Double", 2500),
        new Room(202, "Double", 2500),

        new Room(301, "Deluxe", 4000),
        new Room(302, "Deluxe", 4000),

        new Room(401, "Suite", 6000)
    };

    static Customer[] customers = new Customer[50];

    static int customerCount = 0;


    // ------------------------------------------
    // VIEW ALL ROOMS
    // ------------------------------------------

    static void viewRooms() {

        System.out.println("\n==============================================");
        System.out.println("                 ROOM LIST");
        System.out.println("==============================================");

        System.out.println(
            "Room No | Type   | Price        | Status"
        );

        System.out.println("----------------------------------------------");

        for (Room r : rooms) {
            r.displayRoom();
        }
    }


    // ------------------------------------------
    // FIND ROOM
    // ------------------------------------------

    static Room findRoom(int roomNumber) {

        for (Room r : rooms) {

            if (r.roomNumber == roomNumber) {
                return r;
            }
        }

        return null;
    }


    // ------------------------------------------
    // BOOK ROOM
    // ------------------------------------------

    static void bookRoom() {

        System.out.println("\n==============================================");
        System.out.println("                 ROOM BOOKING");
        System.out.println("==============================================");

        sc.nextLine();

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        viewRooms();

        System.out.print("\nEnter room number: ");
        int roomNumber = sc.nextInt();

        Room room = findRoom(roomNumber);

        if (room == null) {

            System.out.println("Invalid room number.");
            return;
        }

        if (!room.available) {

            System.out.println("Sorry! This room is already booked.");
            return;
        }

        System.out.print("Enter number of nights: ");
        int nights = sc.nextInt();

        if (nights <= 0) {

            System.out.println("Invalid number of nights.");
            return;
        }

        double roomBill = room.price * nights;

        Customer customer =
            new Customer(
                name,
                phone,
                email,
                roomNumber,
                nights,
                roomBill
            );

        customers[customerCount] = customer;

        customerCount++;

        room.available = false;

        System.out.println("\nRoom booked successfully!");

        System.out.println("----------------------------------");
        System.out.println("Customer : " + name);
        System.out.println("Room     : " + roomNumber);
        System.out.println("Type     : " + room.roomType);
        System.out.println("Nights   : " + nights);
        System.out.println("Room Bill: Rs." + roomBill);
        System.out.println("----------------------------------");
    }


    // ------------------------------------------
    // VIEW ALL BOOKINGS
    // ------------------------------------------

    static void viewBookings() {

        System.out.println("\n==============================================");
        System.out.println("                ALL BOOKINGS");
        System.out.println("==============================================");

        if (customerCount == 0) {

            System.out.println("No bookings found.");
            return;
        }

        for (int i = 0; i < customerCount; i++) {

            System.out.println("\nBooking #" + (i + 1));

            customers[i].displayCustomer();
        }
    }


    // ------------------------------------------
    // SEARCH CUSTOMER
    // ------------------------------------------

    static void searchCustomer() {

        System.out.println("\n==============================================");
        System.out.println("              SEARCH CUSTOMER");
        System.out.println("==============================================");

        sc.nextLine();

        System.out.print("Enter customer name: ");

        String searchName = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < customerCount; i++) {

            if (customers[i].name.equalsIgnoreCase(searchName)) {

                customers[i].displayCustomer();

                found = true;
            }
        }

        if (!found) {

            System.out.println("Customer not found.");
        }
    }


    // ------------------------------------------
    // FOOD MENU
    // ------------------------------------------

    static void orderFood() {

        System.out.println("\n==============================================");
        System.out.println("                  FOOD MENU");
        System.out.println("==============================================");

        System.out.println("1. Breakfast       - Rs.200");
        System.out.println("2. Lunch           - Rs.350");
        System.out.println("3. Dinner          - Rs.400");
        System.out.println("4. Pizza           - Rs.300");
        System.out.println("5. Burger          - Rs.200");
        System.out.println("6. Coffee          - Rs.100");
        System.out.println("7. Tea             - Rs.50");
        System.out.println("8. Juice            - Rs.120");

        System.out.print("\nEnter room number: ");

        int roomNumber = sc.nextInt();

        Customer customer = findCustomerByRoom(roomNumber);

        if (customer == null) {

            System.out.println("No customer found in this room.");
            return;
        }

        System.out.print("Select food item: ");

        int choice = sc.nextInt();

        System.out.print("Enter quantity: ");

        int quantity = sc.nextInt();

        double price = 0;

        switch (choice) {

            case 1:
                price = 200;
                break;

            case 2:
                price = 350;
                break;

            case 3:
                price = 400;
                break;

            case 4:
                price = 300;
                break;

            case 5:
                price = 200;
                break;

            case 6:
                price = 100;
                break;

            case 7:
                price = 50;
                break;

            case 8:
                price = 120;
                break;

            default:
                System.out.println("Invalid food choice.");
                return;
        }

        double total = price * quantity;

        customer.foodBill += total;

        System.out.println("\nFood order placed successfully!");

        System.out.println("Food Cost : Rs." + total);
        System.out.println("Updated Food Bill : Rs."
                           + customer.foodBill);
    }


    // ------------------------------------------
    // EXTRA SERVICES
    // ------------------------------------------

    static void extraServices() {

        System.out.println("\n==============================================");
        System.out.println("               HOTEL SERVICES");
        System.out.println("==============================================");

        System.out.println("1. Laundry          - Rs.300");
        System.out.println("2. Room Cleaning    - Rs.200");
        System.out.println("3. Spa              - Rs.1000");
        System.out.println("4. Airport Pickup   - Rs.800");
        System.out.println("5. Extra Bed        - Rs.500");

        System.out.print("\nEnter room number: ");

        int roomNumber = sc.nextInt();

        Customer customer = findCustomerByRoom(roomNumber);

        if (customer == null) {

            System.out.println("No customer found in this room.");
            return;
        }

        System.out.print("Select service: ");

        int choice = sc.nextInt();

        double price = 0;

        switch (choice) {

            case 1:
                price = 300;
                break;

            case 2:
                price = 200;
                break;

            case 3:
                price = 1000;
                break;

            case 4:
                price = 800;
                break;

            case 5:
                price = 500;
                break;

            default:
                System.out.println("Invalid service.");
                return;
        }

        customer.serviceBill += price;

        System.out.println("\nService added successfully.");

        System.out.println("Service Cost : Rs." + price);

        System.out.println(
            "Total Service Bill : Rs."
            + customer.serviceBill
        );
    }


    // ------------------------------------------
    // FIND CUSTOMER BY ROOM
    // ------------------------------------------

    static Customer findCustomerByRoom(int roomNumber) {

        for (int i = 0; i < customerCount; i++) {

            if (customers[i].roomNumber == roomNumber) {

                return customers[i];
            }
        }

        return null;
    }


    // ------------------------------------------
    // CHECK CUSTOMER BILL
    // ------------------------------------------

    static void viewBill() {

        System.out.println("\n==============================================");
        System.out.println("                  BILL");
        System.out.println("==============================================");

        System.out.print("Enter room number: ");

        int roomNumber = sc.nextInt();

        Customer customer =
            findCustomerByRoom(roomNumber);

        if (customer == null) {

            System.out.println("No booking found.");
            return;
        }

        System.out.println("\n------------- HOTEL BILL ----------------");

        System.out.println("Customer Name : "
                           + customer.name);

        System.out.println("Room Number   : "
                           + customer.roomNumber);

        System.out.println("Nights        : "
                           + customer.nights);

        System.out.println("------------------------------------------");

        System.out.println("Room Charges  : Rs."
                           + customer.roomBill);

        System.out.println("Food Charges  : Rs."
                           + customer.foodBill);

        System.out.println("Services      : Rs."
                           + customer.serviceBill);

        System.out.println("------------------------------------------");

        System.out.println("TOTAL BILL    : Rs."
                           + customer.getTotalBill());

        System.out.println("------------------------------------------");
    }


    // ------------------------------------------
    // CHECK OUT
    // ------------------------------------------

    static void checkOut() {

        System.out.println("\n==============================================");
        System.out.println("                 CHECK-OUT");
        System.out.println("==============================================");

        System.out.print("Enter room number: ");

        int roomNumber = sc.nextInt();

        Customer customer =
            findCustomerByRoom(roomNumber);

        if (customer == null) {

            System.out.println("No booking found for this room.");
            return;
        }

        customer.displayCustomer();

        System.out.println(
            "\nFinal Amount: Rs."
            + customer.getTotalBill()
        );

        System.out.print(
            "Confirm check-out? (Y/N): "
        );

        char confirm = sc.next().charAt(0);

        if (confirm == 'Y' || confirm == 'y') {

            Room room = findRoom(roomNumber);

            if (room != null) {

                room.available = true;
            }

            removeCustomer(customer);

            System.out.println(
                "\nCheck-out successful!"
            );

            System.out.println(
                "Thank you for staying with us."
            );
        }
        else {

            System.out.println("Check-out cancelled.");
        }
    }


    // ------------------------------------------
    // REMOVE CUSTOMER
    // ------------------------------------------

    static void removeCustomer(Customer customer) {

        int index = -1;

        for (int i = 0; i < customerCount; i++) {

            if (customers[i] == customer) {

                index = i;
                break;
            }
        }

        if (index != -1) {

            for (int i = index;
                 i < customerCount - 1;
                 i++) {

                customers[i] = customers[i + 1];
            }

            customers[customerCount - 1] = null;

            customerCount--;
        }
    }


    // ------------------------------------------
    // CANCEL BOOKING
    // ------------------------------------------

    static void cancelBooking() {

        System.out.println("\n==============================================");
        System.out.println("              CANCEL BOOKING");
        System.out.println("==============================================");

        System.out.print("Enter room number: ");

        int roomNumber = sc.nextInt();

        Customer customer =
            findCustomerByRoom(roomNumber);

        if (customer == null) {

            System.out.println("No booking found.");
            return;
        }

        System.out.println(
            "Booking found for: "
            + customer.name
        );

        System.out.print(
            "Are you sure you want to cancel? (Y/N): "
        );

        char confirm = sc.next().charAt(0);

        if (confirm == 'Y' || confirm == 'y') {

            Room room = findRoom(roomNumber);

            if (room != null) {

                room.available = true;
            }

            removeCustomer(customer);

            System.out.println(
                "Booking cancelled successfully."
            );
        }
        else {

            System.out.println(
                "Cancellation stopped."
            );
        }
    }


    // ------------------------------------------
    // HOTEL INFORMATION
    // ------------------------------------------

    static void hotelInformation() {

        System.out.println("\n==============================================");
        System.out.println("              HOTEL INFORMATION");
        System.out.println("==============================================");

        System.out.println("Hotel Name : Grand Palace Hotel");

        System.out.println("Location   : Mumbai, Maharashtra");

        System.out.println("Contact    : 9876543210");

        System.out.println("Email      : grandpalace@gmail.com");

        System.out.println("\nFacilities:");

        System.out.println("- Free Wi-Fi");

        System.out.println("- Restaurant");

        System.out.println("- Room Service");

        System.out.println("- Laundry");

        System.out.println("- Parking");

        System.out.println("- 24/7 Reception");

        System.out.println("- Airport Pickup");

        System.out.println("- Spa");
    }


    // ------------------------------------------
    // MAIN METHOD
    // ------------------------------------------

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n\n");
            System.out.println(
                "=============================================="
            );

            System.out.println(
                "          GRAND PALACE HOTEL"
            );

            System.out.println(
                "          MANAGEMENT SYSTEM"
            );

            System.out.println(
                "=============================================="
            );

            System.out.println("1.  View Rooms");

            System.out.println("2.  Book Room");

            System.out.println("3.  View All Bookings");

            System.out.println("4.  Search Customer");

            System.out.println("5.  Order Food");

            System.out.println("6.  Hotel Services");

            System.out.println("7.  View Bill");

            System.out.println("8.  Check-Out");

            System.out.println("9.  Cancel Booking");

            System.out.println("10. Hotel Information");

            System.out.println("11. Exit");

            System.out.println(
                "=============================================="
            );

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();


            switch (choice) {

                case 1:

                    viewRooms();

                    break;


                case 2:

                    bookRoom();

                    break;


                case 3:

                    viewBookings();

                    break;


                case 4:

                    searchCustomer();

                    break;


                case 5:

                    orderFood();

                    break;


                case 6:

                    extraServices();

                    break;


                case 7:

                    viewBill();

                    break;


                case 8:

                    checkOut();

                    break;


                case 9:

                    cancelBooking();

                    break;


                case 10:

                    hotelInformation();

                    break;


                case 11:

                    System.out.println(
                        "\nThank you for using "
                        + "Grand Palace Hotel Management System!"
                    );

                    break;


                default:

                    System.out.println(
                        "\nInvalid choice. Please try again."
                    );
            }

        } while (choice != 11);


        sc.close();
    }
}
