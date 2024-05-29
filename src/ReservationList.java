import java.util.ArrayList;
import java.util.List;

public class ReservationList {
    private final List<Passenger> passengers = new ArrayList<>(); // Lista över passagerare
    private static final int ECONOMY_CAPACITY = 40; // Max kapacitet för ekonomi
    private static final int FIRST_CLASS_CAPACITY = 10; // Max kapacitet för första klass

    // Ta bort en passagerare från listan baserat på namn och födelsedatum
    public boolean removePassenger(String firstName, String lastName, String dob) {
        return passengers.removeIf(p -> p.getFirstName().equals(firstName) &&
                p.getLastName().equals(lastName) &&
                p.getDob().equals(dob));
    }

    // Kontrollera om klassen är fullbokad
    public boolean isFull(boolean isFirstClass) {
        long count = passengers.stream().filter(p -> p.isFirstClass() == isFirstClass).count();
        return isFirstClass ? count < FIRST_CLASS_CAPACITY : count < ECONOMY_CAPACITY;
    }

    // Returnerar antal tillgängliga platser baserat på klass
    public int availableSeats(boolean isFirstClass) {
        long count = passengers.stream().filter(p -> p.isFirstClass() == isFirstClass).count();
        return isFirstClass ? FIRST_CLASS_CAPACITY - (int)count : ECONOMY_CAPACITY - (int)count;
    }

    // Hämtar listan av passagerare
    public List<Passenger> getPassengers() {
        return passengers;
    }
}
