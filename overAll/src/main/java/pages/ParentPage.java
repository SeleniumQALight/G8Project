package pages;

import org.openqa.selenium.WebDriver;

// все загальне для сторінок
public class ParentPage extends CommonActionsWithElements {
    // конструктор
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
