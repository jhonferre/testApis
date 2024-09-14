package co.com.screenplay.project.questions;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

public class CustomerByIdResponse implements Question<ValidatableResponse> {

    private final int expectedId;

    public CustomerByIdResponse(int expectedId) {
        this.expectedId = expectedId;
    }

    @Override
    public ValidatableResponse answeredBy(Actor actor) {
        ValidatableResponse response = SerenityRest.lastResponse().then();
        response.body("id", equalTo(expectedId))
                .body("identityDocument", anything())
                .body("documentType", anything())
                .body("fullName", anything())
                .body("dateOfBirth", anything());
        return response;
    }

    public static CustomerByIdResponse isValid(int expectedId) {
        return new CustomerByIdResponse(expectedId);
    }
}