package pages;

import org.openqa.selenium.WebDriver;

//Parent class for all pages
public class ParentPage extends CommonActionsWithElements {

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
