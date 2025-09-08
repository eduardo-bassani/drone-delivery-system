# Drone Delivery System

## Project Overview

The Drone Delivery System is a Java-based desktop application that manages deliveries using drones. It allows administrators to register locations, drones, customers, and deliveries (both perishable and non-perishable). Customers can log in to view their deliveries and monthly charges. The system supports data simulation via importing files, making it easy to set up and test with bulk data.

## Features

- **Admin Panel**
  - Register new locations, drones, customers, and deliveries
  - View all deliveries
  - Simulate data load from files
- **Customer Panel**
  - View personal deliveries
  - View monthly charges
- **Delivery Types**
  - Perishable deliveries (with expiration date)
  - Non-perishable deliveries (with material description)
- **Data Persistence**
  - All data is saved to CSV files for locations, drones, customers, and deliveries

## Setup Instructions

### Prerequisites

- Java 8 or higher
- [Git](https://git-scm.com/) (optional, for cloning the repository)

### Steps

1. **Clone or Download the Project**
   - Clone the repository or download the source code to your local machine.

2. **Compile the Project**
   - Open a terminal in the project directory.
   - Compile all `.java` files:
     ```
     javac *.java
     ```

3. **Run the Application**
   - Start the application with:
     ```
     java Main
     ```

4. **Using the Application**
   - The GUI will open. You can log in as an administrator (email: `admin@mail.com`, password: `admin123`) or as a registered customer.
   - Use the admin panel to register entities or simulate data load.

## Simulating Data Load

To quickly populate the system with data, you can use the "Simulate data load" feature in the admin panel. This allows you to import entities from files in the following order:

1. **Locations**
   - File name: `TEST-locations.dat`
   - Format:  
     ```
     code;street;latitude;longitude
     ```
   - Example:
     ```
     1;Main Street;40.7128;-74.0060
     2;Second Ave;34.0522;-118.2437
     ```

2. **Customers**
   - File name: `TEST-customers.dat`
   - Format:  
     ```
     name;email;password;locationCode
     ```
   - Example:
     ```
     John Doe;john@example.com;pass123;1
     Jane Smith;jane@example.com;pass456;2
     ```

3. **Drones**
   - File name: `TEST-drones.dat`
   - Format:  
     ```
     identifier;maxLoad;autonomyKm;baseLocationCode
     ```
   - Example:
     ```
     101;5.0;20.0;1
     102;10.0;30.0;2
     ```

4. **Deliveries**
   - File name: `TEST-deliveries.dat`
   - Format:  
     ```
     type;number;description;date;weight;customerEmail;originCode;destinationCode;expirationOrMaterial
     ```
     - `type`: 1 for perishable, 2 for non-perishable
     - `expirationOrMaterial`: expiration date for perishable, material description for non-perishable
   - Example:
     ```
     1;201;Milk Delivery;01/09/2025;2.5;john@example.com;1;2;10/09/2025
     2;202;Book Delivery;02/09/2025;1.0;jane@example.com;2;1;Books
     ```

**Important:**  
- Load files in the order: locations → customers → drones → deliveries.
- Each entity depends on the previous ones (e.g., customers and drones need locations, deliveries need customers, drones, and locations).

## File Structure

- `Main.java` - Entry point of the application
- `ACMEDrones.java` - Core logic and data management
- `GraphicInterface.java` - GUI implementation
- `Customer.java`, `Drone.java`, `Location.java`, `Delivery.java`, `PerishableDelivery.java`, `NonPerishableDelivery.java` - Entity classes