package bdd.stepDefinitions;


import api.ApiHelperPb;
import io.cucumber.java.en.Given;

public class ApiPbSteps {
    private ApiHelperPb apiHelperPb = new ApiHelperPb();

    @Given("I send request for {string} rates to API")
    public void i_send_request_for_rates_to_api(String currency) {
        apiHelperPb.getCurrencyRatesAndSaveInMap(currency);
    }
}
