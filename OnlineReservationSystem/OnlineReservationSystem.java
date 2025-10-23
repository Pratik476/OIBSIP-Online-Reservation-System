import java.util.*;

// Reservation class to store booking details
class Reservation {
    String name;
    int trainNumber;
    String trainName;
    String classType;
    String from;
    String to;
    String dateOfJourney;
    long pnrNumber;

    Reservation(String name, int trainNumber, String trainName, String classType,
                String from, String to, String dateOfJourney, long pnrNumber) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.from = from;
        this.to = to;
        this.dateOfJourney = dateOfJourney;
        this.pnrNumber = pnrNumber;
    }

    public void display() {
        System.out.println("PNR Number     : " + pnrNumber);
        System.out.println("Passenger Name : " + name);
        System.out.println("Train Number   : " + trainNumber);
        System.out.println("Train Name     : " + trainName);
        System.out.println("Class Type     : " + classType);
        System.out.println("From           : " + from);
        System.out.println("To             : " + to);
        System.out.println("Date of Journey: " + dateOfJourney);
        System.out.println("--------------------------------------------");
    }
}

// Main Class
public class OnlineReservationSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("===== WELCOME TO ONLINE RESERVATION SYSTEM =====");

        // Step 1: Login
        if (login()) {
            int choice;
            do {
                System.out.println("\n==============================");
                System.out.println("1. Make Reservation");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. View All Reservations");
                System.out.println("4. Exit");
                System.out.println("==============================");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        makeReservation();
                        break;
                    case 2:
                        cancelReservation();
                        break;
                    case 3:
                        viewAllReservations();
                        break;
                    case 4:
                        System.out.println("Thank you for using Online Reservation System!");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } while (choice != 4);
        } else {
            System.out.println("Login Failed! Program Terminated.");
        }
    }

    // Login system
    public static boolean login() {
        System.out.print("Enter Login ID: ");
        String id = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();

        if (id.equals("admin") && password.equals("1234")) {
            System.out.println("Login Successful!");
            return true;
        } else {
            System.out.println("Invalid ID or Password!");
            return false;
        }
    }

    // Make Reservation
    public static void makeReservation() {
        System.out.println("\n---- Make Reservation ----");
        sc.nextLine(); // consume newline
        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Train Number: ");
        int trainNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Train Name: ");
        String trainName = sc.nextLine();
        System.out.print("Enter Class Type (Sleeper/AC/General): ");
        String classType = sc.nextLine();
        System.out.print("Enter From Station: ");
        String from = sc.nextLine();
        System.out.print("Enter To Station: ");
        String to = sc.nextLine();
        System.out.print("Enter Date of Journey (DD-MM-YYYY): ");
        String date = sc.nextLine();

        long pnr = generatePNR();
        Reservation r = new Reservation(name, trainNumber, trainName, classType, from, to, date, pnr);
        reservations.add(r);

        System.out.println("Reservation Successful! Your PNR Number is: " + pnr);
    }

    // Cancel Reservation
    public static void cancelReservation() {
        System.out.println("\n---- Cancel Reservation ----");
        System.out.print("Enter your PNR Number: ");
        long pnr = sc.nextLong();

        Reservation found = null;
        for (Reservation r : reservations) {
            if (r.pnrNumber == pnr) {
                found = r;
                break;
            }
        }

        if (found != null) {
            System.out.println("Reservation Found:");
            found.display();
            System.out.print("Do you really want to cancel this ticket? (yes/no): ");
            String confirm = sc.next();
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(found);
                System.out.println("Your reservation has been cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No reservation found with this PNR.");
        }
    }

    // View all reservations
    public static void viewAllReservations() {
        System.out.println("\n---- All Reservations ----");
        if (reservations.isEmpty()) {
            System.out.println("No reservations found!");
        } else {
            for (Reservation r : reservations) {
                r.display();
            }
        }
    }

    // Generate random PNR
    public static long generatePNR() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);
    }
}
