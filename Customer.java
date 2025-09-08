import java.util.ArrayList;

public class Customer {
    private String name;
    private Location address;
    private String email;
    private String password;
    private ArrayList<Delivery> deliveries;

    public Customer(String name, Location address, String email, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.deliveries = new ArrayList<Delivery>();
    }

    public String getName() {
        return name;
    }

    public Location getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean addDelivery(Delivery delivery) {
        return deliveries.add(delivery);
    }

    public String viewDeliveries() {
        if (deliveries.isEmpty()) {
            return null;
        } else {
            String list = "";
            for (Delivery d : deliveries) {
                list += d + "\n\n";
            }
            return list;
        }
    }

    public String viewMonthlyCharge(int year, int month) {
        int count = 0;
        String list = "";
        double monthlyCharge = 0.0;
        for (Delivery d : deliveries) {
            if (d.getDate().getYear() == year && d.getDate().getMonthValue() == month) {
                list += d + "\n\n";
                monthlyCharge += d.calculateValue();
                count++;
            }
        }
        if (count == 0) {
            return null;
        } else {
            list += "Monthly charge: $" + monthlyCharge;
            return list;
        }
    }

    public String toCsv() {
        return name + ";" + address.getCode() + ";" + email + ";" + password;
    }

    public String toString() {
        return "[Email: " + email + ", Address Location: " + address + ", Name: " + name + ", Password: " + password + "]";
    }
}
