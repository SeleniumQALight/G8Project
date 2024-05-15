package pages;

import data.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.elements.ParentPagePb;


public class PrivatBankCurrencyPage extends ParentPagePb {

        String BASE_URL_EXAM_PB = "https://privatbank.ua";
        Logger logger = Logger.getLogger(getClass());

        public PrivatBankCurrencyPage(WebDriver webDriver) {
            super(webDriver);
        }

        private String rateBuyLocator = "//td[@id='%s_buy']";
        private String rateSellLocator = "//td[@id='%s_sell']";

        @Override
        protected String getRelativeUrl() {
            return "/";
        }

        public void openPBHomePage() {
            openPage(BASE_URL_EXAM_PB);
            checkUrlWithPattern();
            webDriverWait15.until(
                    webDriver -> webDriver.findElement(By.xpath(".//div[@class='wr_inner course_type_container']"))
            );
        }

        public WebElement getRateBuy(String currency) {
            return webDriver.findElement(By.xpath(
                    String.format(rateBuyLocator, currency)
            ));
        }

        public WebElement getRateSell(String currency) {
            return webDriver.findElement(By.xpath(
                    String.format(rateSellLocator, currency)
            ));
        }

        public void getBuyAndSaleRateFromUI(String currencyName) {
            TestData.rateBuyUI = getRateBuy(currencyName).getText();
            TestData.rateSaleUI = getRateSell(currencyName).getText();
            logger.info("Buy rate from UI = " + TestData.rateBuyUI);
            logger.info("Sale rate from UI = " + TestData.rateSaleUI);
        }

        public void compareApiAndUiRates() {
            Assert.assertEquals("Buy rate from API is not equal to Buy rate from UI",
                    TestData.rateBuyAPI, TestData.rateBuyUI);
            logger.info("Buy rate from API is equal to Buy rate from UI");
            Assert.assertEquals("Sale rate from API is not equal to Sale rate from UI",
                    TestData.rateSaleAPI, TestData.rateSaleUI);
            logger.info("Sale rate from API is equal to Sale rate from UI");
        }
    }
