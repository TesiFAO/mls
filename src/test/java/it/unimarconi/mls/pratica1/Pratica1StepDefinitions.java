package it.unimarconi.mls.pratica1;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;

public class Pratica1StepDefinitions {

    @Given("The power of the modulus is (\\d+) and a is given by (\\d+) and (\\d+)")
    public void initiate_parameters(int b, int m, int q) {
        System.out.println("b " + b);
    }

    @Then("^there should be (\\d+) sequences")
    public void there_are_sequences() {

    }

}