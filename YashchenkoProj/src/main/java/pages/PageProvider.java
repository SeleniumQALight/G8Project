package pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;
    private String originalWindow;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.originalWindow = webDriver.getWindowHandle();
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    public HomePage homePage() {
        return new HomePage(webDriver);
    }

    public PostPage getPostPage() {
        return new PostPage(webDriver);
    }


    // Method for opening the new tab
    public void openNewTab() {
        ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("window.open()");
    }

    public void switchToNextTab() {
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
    }
    public void switchToOriginalTab() {
        webDriver.switchTo().window(originalWindow);
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
    }

    public void closeTab() {
        webDriver.close();
    }
}
