import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Customer class
class Customer {
    private String name;
    private int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + "," + age;
    }
}

// Bakery class
class Bakery {
    private List<Customer> customerList;

    public Bakery() {
        customerList = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customerList.remove(customer);
    }

    public void saveState() {
        try {
            FileWriter writer = new FileWriter("state.csv");
            for (Customer customer : customerList) {
                writer.write(customer.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreState() {
        try {
            FileReader reader = new FileReader("state.csv");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            customerList.clear();
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                Customer customer = new Customer(name, age);
                customerList.add(customer);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}

// BakeryTester class
class BakeryTester {
    public static void main(String[] args) {
        Bakery bakery = new Bakery();

        // Add some customers
        bakery.addCustomer(new Customer("John", 25));
        bakery.addCustomer(new Customer("Jane", 30));
        bakery.addCustomer(new Customer("Bob", 40));

        // Save the state
        bakery.saveState();

        // Remove a customer
        bakery.removeCustomer(new Customer("Jane", 30));

        // Restore the state
        bakery.restoreState();

        // Print the customer list
        List<Customer> customerList = bakery.getCustomerList();
        for (Customer customer : customerList) {
            System.out.println(customer.getName() + ", " + customer.getAge());
        }
    }
}