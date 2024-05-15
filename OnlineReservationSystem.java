import java.util.*;

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Reservation {
    String pnr;
    String user;
    String trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String to;

    Reservation(String pnr, String user, String trainNumber, String trainName, String classType, String dateOfJourney, String from, String to) {
        this.pnr = pnr;
        this.user = user;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + ", Train: " + trainName + " (" + trainNumber + "), Class: " + classType + ", Date: " + dateOfJourney + ", From: " + from + " To: " + to;
    }
}

public class OnlineReservationSystem {

    private static final Map<String, User> users = new HashMap<>();
    private static final Map<String, Reservation> reservations = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static String loggedInUser = null;

    public static void main(String[] args) {
        initializeUsers();
        while (true) {
            if (loggedInUser == null) {
                showLoginForm();
            } else {
                showMainMenu();
            }
        }
    }

    private static void initializeUsers() {
        users.put("user1", new User("user1", "password1"));
        users.put("user2", new User("user2", "password2"));
    }

    private static void showLoginForm() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            loggedInUser = username;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void showMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Make a Reservation");
        System.out.println("2. Cancel a Reservation");
        System.out.println("3. Logout");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                makeReservation();
                break;
            case 2:
                cancelReservation();
                break;
            case 3:
                loggedInUser = null;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void makeReservation() {
        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter Train Name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter Class Type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter From (Place): ");
        String from = scanner.nextLine();
        System.out.print("Enter To (Destination): ");
        String to = scanner.nextLine();

        String pnr = UUID.randomUUID().toString().substring(0, 8);
        Reservation reservation = new Reservation(pnr, loggedInUser, trainNumber, trainName, classType, dateOfJourney, from, to);
        reservations.put(pnr, reservation);

        System.out.println("Reservation successful! Your PNR is: " + pnr);
    }

    private static void cancelReservation() {
        System.out.print("Enter PNR number: ");
        String pnr = scanner.nextLine();

        Reservation reservation = reservations.get(pnr);
        if (reservation != null && reservation.user.equals(loggedInUser)) {
            System.out.println("Reservation details: " + reservation);
            System.out.print("Are you sure you want to cancel this reservation? (yes/no): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No reservation found for the given PNR or you are not authorized to cancel this reservation.");
        }
    }
}
