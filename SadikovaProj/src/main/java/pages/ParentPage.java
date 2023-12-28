package pages;

import org.openqa.selenium.WebDriver;

// Все общее для всех страниц
public class ParentPage extends CommonActionsWithElements {

    // Конструктор
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
