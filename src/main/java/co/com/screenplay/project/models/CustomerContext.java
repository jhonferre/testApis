package co.com.screenplay.project.models;

public class CustomerContext {
    private static CustomerData customerData;

    public static void setCustomerData(CustomerData data) {
        customerData = data;
    }

    public static CustomerData getCustomerData() {
        return customerData;
    }
}
