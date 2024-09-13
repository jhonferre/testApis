package co.com.screenplay.project.models;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class CustomerData {

    private Map<String, Object> data;

    public CustomerData() {
        Faker faker = new Faker();
        data = new HashMap<>();
        data.put("identityDocument", faker.number().numberBetween(1, 999999999));
        data.put("documentType", faker.options().option("NATIONAL_ID", "PASSPORT"));

        // Generar y validar el nombre completo
        String fullName;
        do {
            fullName = faker.name().fullName();
        } while (!fullName.matches("[a-zA-Z ]+"));

        data.put("fullName", fullName);
        data.put("dateOfBirth", faker.date().birthday(0, 124).toInstant().toString().substring(0, 10)); // Genera una fecha de nacimiento entre 0 y 124 años
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
