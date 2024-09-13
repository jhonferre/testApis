package co.com.screenplay.project.tasks;

import co.com.screenplay.project.models.CustomerData;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;

public class CreateCustomer implements Task {

    private final CustomerData customerData;

    public CreateCustomer(CustomerData customerData) {
        this.customerData = customerData;
    }

    public static Performable withData(CustomerData customerData) {
        return Tasks.instrumented(CreateCustomer.class,customerData);
    }

    @Override
    @Step("{0} creates a new customer with the provided data")
    public <T extends Actor> void performAs(T actor) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(customerData.getData());
            actor.attemptsTo(
                    Post.to("/customers")
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
