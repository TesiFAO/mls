package it.unimarconi.mls;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SommaStepDefinition {

    double a;

    double b;

    @Given("I have (\\d+) and (\\d+)")
    public void I_have_a_and_b(double user_a, double user_b) {
        a = user_a;
        b = user_b;
    }

    @Then("^the sum is (\\d+)$")
    public void the_sum_is(double arg1) {
        assertTrue(arg1 == Somma.sum(a, b));
    }

}