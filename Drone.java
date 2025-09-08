import java.util.ArrayList;

public class Drone {
    private int identifier;
    private double maxLoad;
    private double autonomyKm;
    private Location base;
    private ArrayList<Delivery> deliveries;

    public Drone(int identifier, double maxLoad, double autonomyKm, Location base) {
        this.identifier = identifier;
        this.maxLoad = maxLoad;
        this.autonomyKm = autonomyKm;
        this.base = base;
        this.deliveries = new ArrayList<Delivery>();
    }

    public int getIdentifier() {
        return identifier;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public double getAutonomyKm() {
        return autonomyKm;
    }

    public Location getBase() {
        return base;
    }

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }

    public void setAutonomyKm(double autonomyKm) {
        this.autonomyKm = autonomyKm;
    }

    public void setBase(Location base) {
        this.base = base;
    }

    public double distance(Location l) {
        double lat1 = base.getLatitude(); 
        double lon1 = base.getLongitude();
        double lat2 = l.getLatitude();
        double lon2 = l.getLongitude();
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

    public boolean addDelivery(Delivery delivery) {
        return deliveries.add(delivery);
    }

    public String toCsv() {
        return identifier + ";" + maxLoad + ";" + autonomyKm + ";" + base.getCode();
    }

    public String toString() {
        return "[Autonomy: " + autonomyKm + ", Base Location: " + base + ", Max Load: " + maxLoad
                + ", Identifier: " + identifier + "]";
    }
}
