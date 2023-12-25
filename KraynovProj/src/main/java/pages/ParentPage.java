package pages;

import org.openqa.selenium.WebDriver;

// всё общее для всех страниц
public class ParentPage extends CommonActionsWithElements {
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
