public class BakeryTester {
    public static void main(String[] args) {
        Bakery bakery = new Bakery();

        // Add customers
        bakery.addCustomer(new Customer("Alice", 1));
        bakery.addCustomer(new Customer("Bob", 2));

        // Save state
        bakery.saveState();

        // Create a new bakery instance and restore state
        Bakery newBakery = new Bakery();
        newBakery.restoreState();

        // Display customers in the new bakery instance
        for (Customer customer : newBakery.getCustomers()) {
            System.out.println("Customer ID: " + customer.getId() + ", Name: " + customer.getName());
        }
    }
}
