import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PerishableDelivery extends Delivery {
    private LocalDate expirationDate;

    public PerishableDelivery(int number, String description, LocalDate date, double weight, Location origin, Location destination, Customer customer, LocalDate expirationDate) {
        super(number, description, date, weight, origin, destination, customer);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public double calculateValue() {
        return (20.0 * weight + 30.0 * distance()) * 1.1;
    }

    public String toCsv() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "1;" + number + ";" + description + ";" + date.format(formatter) + ";" + weight + ";" + origin.getCode() + ";" + destination.getCode() + ";" + status + ";" + drone.getIdentifier() + ";" + customer.getEmail() + ";" + expirationDate.format(formatter);
    }

    public String toString() {
        return "Identifier: " + number + "\nDescription: " + description + "\nDate: " + date + "\nWeight: "
            + weight + "\nOrigin Location: " + origin + "\nDestination Location: " + destination
            + "\nStatus: " + status + "\nDrone: " + drone + "\nCustomer: " + customer + "\nExpiration Date: " + expirationDate + "\nCharge: " + calculateValue();
    }
}
