import java.time.LocalDate;

public abstract class Delivery {
    protected int number;
    protected String description;
    protected LocalDate date;
    protected double weight;
    protected Location origin;
    protected Location destination;
    protected String status;
    protected Drone drone;
    protected Customer customer;

    public Delivery(int number, String description, LocalDate date, double weight, Location origin, Location destination, Customer customer) {
        this.number = number;
        this.description = description;
        this.date = date;
        this.weight = weight;
        this.origin = origin;
        this.destination = destination;
        this.status = "registered";
        this.drone = null;
        this.customer = customer;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }

    public Drone getDrone() {
        return drone;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public abstract double calculateValue();

    public abstract String toCsv();

    public double distance() {
        double lat1 = origin.getLatitude(); 
        double lon1 = origin.getLongitude();
        double lat2 = destination.getLatitude();
        double lon2 = destination.getLongitude();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                   Math.pow(Math.sin(dLon / 2), 2) *
                   Math.cos(lat1) *
                   Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}
