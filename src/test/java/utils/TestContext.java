package utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * State shared between steps.
 * <ul>
 *   <li>{@link #customer} lives for the whole run: the first scenario that needs a
 *       logged-in user registers one, later scenarios just log in with it.</li>
 *   <li>Everything else is per scenario and cleared by the Before hook.</li>
 * </ul>
 */
public final class TestContext {

    public static Customer customer;

    public static final Map<String, String> accounts = new HashMap<>();
    public static final Map<String, BigDecimal> balancesBefore = new HashMap<>();
    public static String lastAmount;

    private TestContext() {
    }

    public static void resetScenario() {
        accounts.clear();
        balancesBefore.clear();
        lastAmount = null;
    }
}
