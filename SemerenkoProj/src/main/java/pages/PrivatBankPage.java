package pages;

import data.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrivatBankPage extends ParentPage {
    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    private String url = "https://privatbank.ua/";
    private String locatorCurrSellValue = ".//td[@id='%s_sell']";
    private String locatorCurrBuyValue = ".//td[@id='%s_buy']";



    @Override
    protected String getRelativeUrl() {
        return "https://privatbank.ua/";
    }

    public void openPrivatBankPage() {
        try {
            webDriver.get(url);
            logger.info("Page was open " + url);
        } catch (Exception e) {
            logger.error("Page " + url + " notopen");
            Assert.fail("Page " + url + " notopen");
        }
    }

    public Double getCurrencySaleValue(String currency) {
        WebElement currencyRate = getElement(locatorCurrSellValue, currency.toUpperCase());
        return Double.parseDouble(currencyRate.getText());
    }

    public Double getCurrencyBuyValue(String currency) {
        WebElement currencyRate = getElement(locatorCurrBuyValue, currency.toUpperCase());
        return Double.parseDouble(currencyRate.getText());
    }

    public Double getCurrencyBuyValue(String currency, String operation) {
        if (operation.equals("buy")) {
            return getCurrencyBuyValue(currency);
        } else if (operation.equals("sale")) {
            return getCurrencySaleValue(currency);
        } else return null;
    }

    public void checkCurrencyRateCompareUiAndApi(String currency, Map<String, Double> currencyValuesFromApiMap) {
        String[] operations = {"buy", "sale"};
        SoftAssertions softAssertions = new SoftAssertions();
        if (!(currencyValuesFromApiMap == null)) {
            for (int i = 0; i < operations.length; i++) {
                softAssertions.assertThat(currencyValuesFromApiMap.get(operations[i]))
                        .as("Error" + i)
                        .isEqualTo(getCurrencyBuyValue(currency, operations[i]));
            }
            softAssertions.assertAll();
        }else {
            logger.info("Comparated with null");
        }
    }


}
