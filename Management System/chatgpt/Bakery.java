import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bakery {
    private List<Customer> customers;

    public Bakery() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public void saveState() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("state.csv"))) {
            for (Customer customer : customers) {
                writer.println(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreState() {
        customers.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("state.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[1];
                    int id = Integer.parseInt(parts[0]);
                    customers.add(new Customer(name, id));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
