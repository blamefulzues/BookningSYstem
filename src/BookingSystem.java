import java.util.Scanner;

public class BookingSystem {
    private final ReservationList[] reservationLists = new ReservationList[5]; // Array med bokningslistor för varje destination
    private final Scanner scanner = new Scanner(System.in); // Scanner för användarens input

    // Konstruktor som initialiserar bokningslistorna
    public BookingSystem() {
        for (int i = 0; i < reservationLists.length; i++) {
            reservationLists[i] = new ReservationList();
        }
    }

    // Huvudmetod som kör bokningssystemet
    public void run() {
        System.out.println("Välkommen till bokningssystemet.");
        while (!allSeatsBooked()) {
            System.out.println("Välj ett alternativ:");
            System.out.println("1. Kontrollera bokning");
            System.out.println("2. Avboka plats");
            System.out.println("3. Visa lediga platser");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> checkBooking(); // Kontrollera en bokning
                case 2 -> cancelBooking(); // Avboka en plats
                case 3 -> showAvailableSeats(); // Visa tillgängliga platser
                default -> System.out.println("Ogiltigt val. Försök igen.");
            }
        }
        scanner.close();
        System.out.println("Alla platser är bokade. Tack!");
    }

    // Kontrollera bokning baserat på användarens input
    private void checkBooking() {
        System.out.println("Välj destination (1. America, 2. Canada, 3. India, 4. China, 5. Japan):");
        int destination = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.println("Förnamn:");
        String firstName = scanner.nextLine();
        System.out.println("Efternamn:");
        String lastName = scanner.nextLine();
        System.out.println("Födelsedatum (ddmmyyyy):");
        String dob = scanner.nextLine();

        boolean found = reservationLists[destination].getPassengers().stream()
                .anyMatch(p -> p.getFirstName().equals(firstName) &&
                        p.getLastName().equals(lastName) &&
                        p.getDob().equals(dob));

        if (found) {
            System.out.println("Bokning hittades.");
        } else {
            System.out.println("Ingen bokning hittades.");
        }
    }

    // Avboka en plats baserat på användarens input
    private void cancelBooking() {
        System.out.println("Välj destination (1. America, 2. Canada, 3. India, 4. China, 5. Japan):");
        int destination = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.println("Förnamn:");
        String firstName = scanner.nextLine();
        System.out.println("Efternamn:");
        String lastName = scanner.nextLine();
        System.out.println("Födelsedatum (ddmmyyyy):");
        String dob = scanner.nextLine();

        if (reservationLists[destination].removePassenger(firstName, lastName, dob)) {
            System.out.println("Bokning avbokad.");
        } else {
            System.out.println("Ingen bokning hittades.");
        }
    }

    // Visa antalet tillgängliga platser för varje destination
    private void showAvailableSeats() {
        for (int i = 0; i < reservationLists.length; i++) {
            Destination location = Destination.values()[i];
            int economySeats = reservationLists[i].availableSeats(false);
            int firstClassSeats = reservationLists[i].availableSeats(true);
            System.out.println(location + " - Ekonomi: " + economySeats + ", Första Klass: " + firstClassSeats);
        }
    }

    // Kontrollera om alla platser är bokade för alla destinationer
    private boolean allSeatsBooked() {
        for (ReservationList rl : reservationLists) {
            if (rl.isFull(true) || rl.isFull(false)) {
                return false;
            }
        }
        return true;
    }

    // Main-metoden som startar bokningssystemet
    public static void main(String[] args) {
        new BookingSystem().run();
    }
}
