package utils;

/**
 * A ParaBank customer. Each run registers its own customer with a unique
 * username, so scenarios never depend on accounts someone else changed on
 * the shared public demo.
 */
public class Customer {

    public String firstName = "Ranajit";
    public String lastName = "Tester";
    public String street = "100 Main Street";
    public String city = "Norristown";
    public String state = "PA";
    public String zipCode = "19401";
    public String phone = "2673425500";
    public String ssn;
    public String username;
    public String password = "Demo#2026";

    public static Customer unique() {
        Customer c = new Customer();
        String stamp = Long.toString(System.currentTimeMillis(), 36)
                + Integer.toString((int) (Math.random() * 1296), 36);
        c.username = "rb" + stamp;
        String digits = Long.toString(Math.abs(System.nanoTime()));
        c.ssn = digits.substring(digits.length() - 9);
        return c;
    }
}
