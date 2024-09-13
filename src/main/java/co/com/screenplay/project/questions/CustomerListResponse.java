package co.com.screenplay.project.questions;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static org.hamcrest.Matchers.*;

public class CustomerListResponse implements Question<ValidatableResponse> {

    @Override
    public ValidatableResponse answeredBy(Actor actor) {
        ValidatableResponse response = SerenityRest.lastResponse().then();
        response.body("$", everyItem(allOf(
                hasKey("identityDocument"),
                hasKey("documentType"),
                hasKey("fullName"),
                hasKey("dateOfBirth"),
                hasKey("id")
        )));
        return response;
    }

    public static CustomerListResponse isValid() {
        return new CustomerListResponse();
    }
}