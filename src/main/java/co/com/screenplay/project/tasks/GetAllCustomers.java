package co.com.screenplay.project.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

public class GetAllCustomers implements Task {

    public static Performable fromApi() {
        return Tasks.instrumented(GetAllCustomers.class);
    }

    @Override
    @Step("{0} retrieves all customers from the API")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/customers")
        );
    }
}
