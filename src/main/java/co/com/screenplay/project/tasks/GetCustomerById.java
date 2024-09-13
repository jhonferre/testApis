package co.com.screenplay.project.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

public class GetCustomerById implements Task {

    private final String customerId;

    public GetCustomerById(String customerId) {
        this.customerId = customerId;
    }

    public static Performable withId(String customerId) {
        return Tasks.instrumented(GetCustomerById.class, customerId);
    }

    @Override
    @Step("{0} retrieves the customer with ID #customerId from the API")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/customers/" + customerId)
        );
    }
}
