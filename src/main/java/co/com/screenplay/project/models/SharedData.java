package co.com.screenplay.project.models;

public class SharedData {
    private static Integer customerId;

    public static Integer getCustomerId() {
        return customerId;
    }

    public static void setCustomerId(Integer id) {
        customerId = id;
    }
}
