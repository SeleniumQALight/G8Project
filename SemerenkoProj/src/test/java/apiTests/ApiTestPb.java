package apiTests;

import api.ApiHelperPb;
import api.EndPointsPb;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ApiTestPb {
    private String rateDate = "22.03.2022";
    Logger logger = Logger.getLogger(getClass());
    ApiHelperPb apiHelperPb = new ApiHelperPb();

    @Test
    public void getRateByDate(){
        apiHelperPb.getRateByDate(rateDate);
    }
}
