package co.com.screenplay.project.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.thucydides.core.annotations.Step;

public class DeleteCustomer implements Task {
    private final String customerId;

    public DeleteCustomer(String customerId) {
        this.customerId = customerId;
    }

    public static Performable byId(String customerId) {
        return new DeleteCustomer(customerId);
    }

    @Override
    @Step("{0} deletes the customer with ID #customerId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/customers/" + customerId)
        );
    }
}
