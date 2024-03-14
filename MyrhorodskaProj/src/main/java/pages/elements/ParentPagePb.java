package pages.elements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CommonActionsWithElements;

public abstract class ParentPagePb extends CommonActionsWithElements {
        Logger logger = Logger.getLogger(getClass());
        String BASE_URL_EXAM_PB = "https://privatbank.ua";

   public ParentPagePb(WebDriver webDriver) {
            super(webDriver);
        }

        public void openPage(String url) {
            try {
                webDriver.get(url);
                logger.info("The site was opened " + url);
            } catch (Exception e) {
                logger.error("Can't open " + url);
                Assert.fail("Can't open " + url);
            }
        }

        protected abstract String getRelativeUrl();

        protected void checkUrlWithPattern(String relativeUrl) {
            Assert.assertTrue("Url is not expected \n"
                            + "Expected result: " + BASE_URL_EXAM_PB + relativeUrl + "\n"
                            + "Actual result: " + webDriver.getCurrentUrl()
                    , webDriver.getCurrentUrl().matches(BASE_URL_EXAM_PB + relativeUrl));
        }

        protected void checkUrlWithPattern() {
            checkUrlWithPattern(getRelativeUrl());
        }
}
