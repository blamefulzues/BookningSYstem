public class Passenger {
    private final String firstName; // Förnamn på passageraren
    private final String lastName; // Efternamn på passageraren
    private final String dob; // Födelsedatum på passageraren (ddmmyyyy format)
    private final boolean isFirstClass; // Indikerar om passageraren är i första klass

    public Passenger(String firstName, String lastName, String dob, boolean isFirstClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.isFirstClass = isFirstClass;
    }

    // Getters och Setters
    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getDob() { return dob; }

    public boolean isFirstClass() { return isFirstClass; }

}
