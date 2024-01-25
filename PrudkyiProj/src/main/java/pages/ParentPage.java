package pages;

import libs.ConfigProvider;
import org.openqa.selenium.WebDriver;

//all general methods for all pages
public class ParentPage extends CommonActionsWithElements {
   String env = System.getProperty("env", "qa");
    final String baseUrl = ConfigProvider.configProperties.base_url().replace("env", env);
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
