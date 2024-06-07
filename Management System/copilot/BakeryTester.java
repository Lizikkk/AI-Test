import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private String address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Getters and setters for name and address
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

class Bakery {
    private List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customerList.remove(customer);
    }

    public void saveState() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("state.csv"))) {
            for (Customer customer : customerList) {
                writer.println(customer.getName() + "," + customer.getAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreState() {
        try (BufferedReader reader = new BufferedReader(new FileReader("state.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    String address = parts[1];
                    customerList.add(new Customer(name, address));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}

public class BakeryTester {
    public static void main(String[] args) {
        Bakery bakery = new Bakery();
        bakery.addCustomer(new Customer("Alice", "123 Main St"));
        bakery.addCustomer(new Customer("Bob", "456 Elm St"));

        // Test saveState() and restoreState() methods
        bakery.saveState();
        bakery.restoreState();

        System.out.println("Customers in the bakery:");
        for (Customer customer : bakery.getCustomerList()) {
            System.out.println(customer);
        }
    }
}
