package pages;

import libs.ConfigProvider;
import org.openqa.selenium.WebDriver;

//all general methods for all pages
public class ParentPage extends CommonActionsWithElements {
    final String baseUrl = ConfigProvider.configProperties.base_url();
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
