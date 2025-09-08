import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NonPerishableDelivery extends Delivery {
    private String materialsDescription;

    public NonPerishableDelivery(int number, String description, LocalDate date, double weight, Location origin, Location destination, Customer customer, String materialsDescription) {
        super(number, description, date, weight, origin, destination, customer);
        this.materialsDescription = materialsDescription;
    }

    public String getMaterialsDescription() {
        return materialsDescription;
    }

    public void setMaterialsDescription(String materialsDescription) {
        this.materialsDescription = materialsDescription;
    }

    @Override
    public double calculateValue() {
        return 20.0 * weight + 30.0 * distance();
    }

    public String toCsv() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "2;" + number + ";" + description + ";" + date.format(formatter) + ";" + weight + ";" + origin.getCode() + ";" + destination.getCode() + ";" + status + ";" + drone.getIdentifier() + ";" + customer.getEmail() + ";" + materialsDescription;
    }

    public String toString() {
        return "Identifier: " + number + "\nDescription: " + description + "\nDate: " + date + "\nWeight: "
            + weight + "\nOrigin Location: " + origin + "\nDestination Location: " + destination
            + "\nStatus: " + status + "\nDrone: " + drone + "\nCustomer: " + customer + "\nMaterials Description: " + materialsDescription + "\nCharge: " + calculateValue();
    }
}
