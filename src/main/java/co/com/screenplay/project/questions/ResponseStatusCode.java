package co.com.screenplay.project.questions;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseStatusCode implements Question<Integer> {

    public static ResponseStatusCode was() {
        return new ResponseStatusCode();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        ValidatableResponse response = SerenityRest.lastResponse().then();
        return response.extract().statusCode();
    }
}
