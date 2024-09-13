package co.com.screenplay.project.tasks;

import co.com.screenplay.project.models.CustomerData;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.thucydides.core.annotations.Step;

public class UpdateCustomer implements Task {
    private final String customerId;
    private final CustomerData customerData;

    public UpdateCustomer(String customerId, CustomerData customerData) {
        this.customerId = customerId;
        this.customerData = customerData;
    }

    public static Performable withData(String customerId, CustomerData customerData) {
        return Tasks.instrumented(UpdateCustomer.class, customerId, customerData);
    }

    @Override
    @Step("{0} updates the customer with ID #customerId")
    public <T extends Actor> void performAs(T actor) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(customerData.getData());
            actor.attemptsTo(
                    Put.to("/customers/" + customerId)
                            .with(request -> request
                                    .header("Content-Type", "application/json")
                                    .body(requestBody)
                            )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
