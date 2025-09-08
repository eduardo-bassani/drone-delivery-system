import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ACMEDrones {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Location> locations;
    private ArrayList<Drone> drones;
    private ArrayList<Customer> customers;
    private ArrayList<Delivery> deliveries;
    private Customer customer;
    private boolean administrator;

    public ACMEDrones() {
        locations = new ArrayList<Location>();
        drones = new ArrayList<Drone>();
        customers = new ArrayList<Customer>();
        deliveries = new ArrayList<Delivery>();
        customer = null;
        administrator = false;
        loadLocations();
        loadDrones();
        loadCustomers();
        loadDeliveries();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public int customerCount() {
        return customers.size();
    }

    public boolean loadLocations() {
        try (BufferedReader br = new BufferedReader(new FileReader("locations.csv"))) {
            String line, street;
            int code;
            double latitude, longitude;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 4) {
                    code = Integer.parseInt(fields[0]);
                    street = fields[1];
                    latitude = Double.parseDouble(fields[2]);
                    longitude = Double.parseDouble(fields[3]);
                    locations.add(new Location(code, street, latitude, longitude));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean loadDrones() {
        try (BufferedReader br = new BufferedReader(new FileReader("drones.csv"))) {
            String line;
            int identifier, baseCode;
            double maxLoad, autonomyKm;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 4) {
                    identifier = Integer.parseInt(fields[0]);
                    maxLoad = Double.parseDouble(fields[1]);
                    autonomyKm = Double.parseDouble(fields[2]);
                    baseCode = Integer.parseInt(fields[3]);
                    drones.add(new Drone(identifier, maxLoad, autonomyKm, findLocation(baseCode)));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean loadCustomers() {
        try (BufferedReader br = new BufferedReader(new FileReader("customers.csv"))) {
            String line, name, email, password;
            int locationCode;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 4) {
                    name = fields[0];
                    locationCode = Integer.parseInt(fields[1]);
                    email = fields[2];
                    password = fields[3];
                    customers.add(new Customer(name, findLocation(locationCode), email, password));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean loadDeliveries() {
        try (BufferedReader br = new BufferedReader(new FileReader("deliveries.csv"))) {
            String line, description;
            int type, number;
            Location origin, destination;
            LocalDate date, expiration;
            double weight;
            String materialsDescription, status;
            Customer customer;
            Drone drone;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 11) {
                    type = Integer.parseInt(fields[0]);
                    number = Integer.parseInt(fields[1]);
                    description = fields[2];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    date = LocalDate.parse(fields[3], formatter);
                    weight = Double.parseDouble(fields[4]);
                    origin = findLocation(Integer.parseInt(fields[5]));
                    destination = findLocation(Integer.parseInt(fields[6]));
                    status = fields[7];
                    drone = findDrone(Integer.parseInt(fields[8]));
                    customer = findCustomer(fields[9]);
                    if (type == 1) {
                        expiration = LocalDate.parse(fields[10], formatter);
                        PerishableDelivery perishableDelivery = new PerishableDelivery(number, description, date, weight, origin, destination, customer, expiration);
                        perishableDelivery.setStatus(status);
                        perishableDelivery.setDrone(drone);
                        deliveries.add(perishableDelivery);
                        customer.addDelivery(perishableDelivery);
                        drone.addDelivery(perishableDelivery);
                    } else if (type == 2) {
                        materialsDescription = fields[10];
                        NonPerishableDelivery nonPerishableDelivery = new NonPerishableDelivery(number, description, date, weight, origin, destination, customer, materialsDescription);
                        nonPerishableDelivery.setStatus(status);
                        nonPerishableDelivery.setDrone(drone);
                        deliveries.add(nonPerishableDelivery);
                        customer.addDelivery(nonPerishableDelivery);
                        drone.addDelivery(nonPerishableDelivery);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("locations.csv"))) {
            for (Location l : locations) {
                bw.write(l.toCsv() + "\n");
            }
        } catch (Exception e) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("drones.csv"))) {
            for (Drone d : drones) {
                bw.write(d.toCsv() + "\n");
            }
        } catch (Exception e) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("customers.csv"))) {
            for (Customer c : customers) {
                bw.write(c.toCsv() + "\n");
            }
        } catch (Exception e) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("deliveries.csv"))) {
            for (Delivery e : deliveries) {
                bw.write(e.toCsv() + "\n");
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ArrayList<Drone> qualifiedDrones(Delivery delivery) {
        ArrayList<Drone> qualifiedDrones = new ArrayList<Drone>();
        for (Drone d : drones) {
            if (delivery.getWeight() <= d.getMaxLoad()) {
                if ((d.distance(delivery.getOrigin()) + delivery.distance() + d.distance(delivery.getDestination())) <= d.getAutonomyKm()) {
                    qualifiedDrones.add(d);
                }
            }
        }
        return qualifiedDrones;
    }

    public boolean isAdmin() {
        return administrator;
    }

    public boolean login(String email, String password) {
        if (email.equals("admin@mail.com") && password.equals("admin123")) {
            administrator = true;
            customer = null;
            return true;
        }
        for (Customer c : customers) {
            if (c.getEmail().equals(email) && c.getPassword().equals(password)) {
                administrator = false;
                customer = c;
                return true;
            }
        }
        administrator = false;
        customer = null;
        return false;
    }

    public boolean registerLocation(int code, String street, double latitude, double longitude) {
        if (locationExists(code)) {
            return false;
        }
        locations.add(new Location(code, street, latitude, longitude));
        return true;
    }

    public boolean locationExists(int code) {
        for (Location l : locations) {
            if (l.getCode() == code) {
                return true;
            }
        }
        return false;
    }

    public Location findLocation(int code) {
        for (Location l : locations) {
            if (l.getCode() == code) {
                return l;
            }
        }
        return null;
    }

    public boolean registerDrone(int identifier, double maxLoad, double autonomyKm, Location base) {
        if (droneExists(identifier)) {
            return false;
        }
        drones.add(new Drone(identifier, maxLoad, autonomyKm, base));
        return true;
    }

    public boolean droneExists(int identifier) {
        for (Drone d : drones) {
            if (d.getIdentifier() == identifier) {
                return true;
            }
        }
        return false;
    }

    public Drone findDrone(int identifier) {
        for (Drone d : drones) {
            if (d.getIdentifier() == identifier) {
                return d;
            }
        }
        return null;
    }

    public boolean registerCustomer(String name, Location address, String email, String password) {
        if (customerExists(email)) {
            return false;
        }
        customers.add(new Customer(name, address, email, password));
        return true;
    }

    public boolean customerExists(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Customer findCustomer(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public boolean registerPerishableDelivery(int number, String description, LocalDate date, double weight, Location origin, Location destination, Customer customer, LocalDate expiration) {
        PerishableDelivery delivery = new PerishableDelivery(number, description, date, weight, origin, destination, customer, expiration);
        if (deliveryExists(number) || qualifiedDrones(delivery).isEmpty()) {
            return false;
        }
        deliveries.add(delivery);
        customer.addDelivery(delivery);
        return true;
    }

    public void registerPerishableDelivery(PerishableDelivery delivery) {
        deliveries.add(delivery);
        delivery.getCustomer().addDelivery(delivery);
    }

    public boolean registerNonPerishableDelivery(int number, String description, LocalDate date, double weight, Location origin, Location destination, Customer customer, String materialsDescription) {
        NonPerishableDelivery delivery = new NonPerishableDelivery(number, description, date, weight, origin, destination, customer, materialsDescription);
        if (deliveryExists(number) || qualifiedDrones(delivery).isEmpty()) {
            return false;
        }
        deliveries.add(delivery);
        customer.addDelivery(delivery);
        return true;
    }

    public void registerNonPerishableDelivery(NonPerishableDelivery delivery) {
        deliveries.add(delivery);
        delivery.getCustomer().addDelivery(delivery);
    }

    public boolean deliveryExists(int number) {
        for (Delivery e : deliveries) {
            if (e.getNumber() == number) {
                return true;
            }
        }
        return false;
    }

    public String viewAllDeliveries() {
        if (deliveries.isEmpty()) {
            return "No deliveries found.";
        } else {
            String list = "";
            for (Delivery e : deliveries) {
                list += e + "\n\n";
            }
            return list;
        }
    }

    public String viewDeliveries() {
        if (customer.getDeliveries().isEmpty()) {
            return "No deliveries found.";
        } else {
            return customer.viewDeliveries();
        }
    }

    public String viewMonthlyCharge(int year, int month) {
        if (customer.viewMonthlyCharge(year, month) == null) {
            return "No deliveries found in the period.";
        } else {
            return customer.viewMonthlyCharge(year, month);
        }
    }

    public boolean simulateDataLoad(String fileLocation) {
        if (readTestLocationFile(fileLocation)) {
            return true;
        } else if (readTestDroneFile(fileLocation)) {
            return true;
        } else if (readTestCustomerFile(fileLocation)) {
            return true;
        } else if (readTestDeliveryFile(fileLocation)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean readTestCustomerFile(String a) {
        Path path = Paths.get(a);
        if (path.toString().contains("-customers.dat")) {
            boolean exists = Files.exists(path);
            List<Customer> list = new ArrayList<Customer>();
            try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
                String line = br.readLine();
                line = br.readLine();
                while (line != null) {
                    String[] vect = line.split(";");
                    String name = vect[0];
                    String email = vect[1];
                    String password = vect[2];
                    int loc = Integer.parseInt(vect[3]);
                    Location loc1 = findLocation(loc);
                    Customer testCustomer;
                    for (int i = 0; i < customers.size(); i++) {
                        testCustomer = customers.get(i);
                        if (email.equals(testCustomer.getEmail())) {
                            return false;
                        }
                    }
                    Customer cli = new Customer(name, loc1, email, password);
                    list.add(cli);
                    line = br.readLine();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
            Customer temp;
            for (int i = 0; i < list.size(); i++) {
                temp = list.get(i);
                customers.add(temp);
            }
            return true;
        }
        return false;
    }

    public boolean readTestLocationFile(String b) {
        Path path = Paths.get(b);
        if (path.toString().contains("-locations.dat")) {
            boolean exists = Files.exists(path);
            List<Location> list = new ArrayList<Location>();
            try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
                br.readLine();
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] vect = line.split(";");
                    int code = Integer.parseInt(vect[0]);
                    String street = vect[1];
                    Double latitude = Double.parseDouble(vect[2]);
                    Double longitude = Double.parseDouble(vect[3]);
                    Location locs;
                    for (int i = 0; i < locations.size(); i++) {
                        locs = locations.get(i);
                        if (code == locs.getCode()) {
                            return false;
                        }
                    }
                    Location locali = new Location(code, street, latitude, longitude);
                    list.add(locali);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
            Location loc;
            for (int i = 0; i < list.size(); i++) {
                loc = list.get(i);
                locations.add(loc);
            }
            return true;
        }
        return false;
    }

    public boolean readTestDroneFile(String c) {
        Path path = Paths.get(c);
        if (path.toString().contains("-drones.dat")) {
            List<Drone> list = new ArrayList<Drone>();
            try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
                String line = br.readLine();
                line = br.readLine();
                while (line != null) {
                    String[] vect = line.split(";");
                    int identifier = Integer.parseInt(vect[0]);
                    int loc = Integer.parseInt(vect[3]);
                    Location base = findLocation(loc);
                    Double maxLoad = Double.parseDouble(vect[1]);
                    Double autonomyKm = Double.parseDouble(vect[2]);
                    Drone dro = new Drone(identifier, maxLoad, autonomyKm, base);
                    list.add(dro);
                    line = br.readLine();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
            Drone drone;
            for (int i = 0; i < list.size(); i++) {
                drone = list.get(i);
                drones.add(drone);
            }
            return true;
        }
        return false;
    }

    public boolean readTestDeliveryFile(String d) {
        try (BufferedReader br = new BufferedReader(new FileReader(d))) {
            String line, description;
            int type, number;
            Location origin, destination;
            LocalDate date, expiration;
            double weight;
            String materialsDescription;
            Customer customer;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 9) {
                    type = Integer.parseInt(fields[0]);
                    number = Integer.parseInt(fields[1]);
                    description = fields[2];
                    String[] dateParts = fields[3].split("/");
                    date = LocalDate.of(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]));
                    weight = Double.parseDouble(fields[4]);
                    customer = findCustomer(fields[5]);
                    origin = findLocation(Integer.parseInt(fields[6]));
                    destination = findLocation(Integer.parseInt(fields[7]));
                    if (type == 1) {
                        dateParts = fields[8].split("/");
                        expiration = LocalDate.of(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]));
                        PerishableDelivery perishableDelivery = new PerishableDelivery(number, description, date, weight, origin, destination, customer, expiration);
                        if (!qualifiedDrones(perishableDelivery).isEmpty()) {
                            perishableDelivery.setDrone(qualifiedDrones(perishableDelivery).get(0));
                            deliveries.add(perishableDelivery);
                            customer.addDelivery(perishableDelivery);
                        }
                    } else if (type == 2) {
                        materialsDescription = fields[8];
                        NonPerishableDelivery nonPerishableDelivery = new NonPerishableDelivery(number, description, date, weight, origin, destination, customer, materialsDescription);
                        if (!qualifiedDrones(nonPerishableDelivery).isEmpty()) {
                            nonPerishableDelivery.setDrone(qualifiedDrones(nonPerishableDelivery).get(0));
                            deliveries.add(nonPerishableDelivery);
                            customer.addDelivery(nonPerishableDelivery);
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
