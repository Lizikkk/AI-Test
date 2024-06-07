import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private int id;

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

class Bakery {
    private List<Customer> customers;

    public Bakery() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(int customerId) throws CustomerNotFoundException {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customerId) {
                customers.remove(i);
                return;
            }
        }
        throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
    }

    public void saveState(String filename) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            for (Customer customer : customers) {
                writer.write(customer.getName() + "," + customer.getId() + "\n");
            }
        }
    }

    public void restoreState(String filename) throws IOException {
        customers.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                customers.add(new Customer(parts[0], Integer.parseInt(parts[1])));
            }
        }
    }

    // Added getter method for customers list
    public List<Customer> getCustomers() {
        return customers;
    }
}

class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}

class BakeryTester {
    public static void main(String[] args) throws IOException, CustomerNotFoundException {
        Bakery bakery = new Bakery();

        // Add some customers
        bakery.addCustomer(new Customer("John", 1001));
        bakery.addCustomer(new Customer("Jane", 1002));

        // Save state
        bakery.saveState("state.csv");

        // Remove a customer
        bakery.removeCustomer(1002);

        // Restore state
        bakery.restoreState("state.csv");

        // Print the customer list
        System.out.println("Customers:");
        for (Customer customer : bakery.getCustomers()) {
            System.out.println("  - " + customer.getName() + " (ID: " + customer.getId() + ")");
        }
    }
}
