package co.com.screenplay.project.models;

public class SharedData {
    private static String customerId;

    public static String getCustomerId() {
        return customerId;
    }

    public static void setCustomerId(String id) {
        customerId = id;
    }
}
