package libs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Util {
    private Util() {
    }

    public static void waitABit(Integer second){
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method returned SystemDateAndTime In Format yyyy-MM-dd_HH-mm-ss
     */
    public static String getDateAndTimeFormatted(){
        return getDateAndTime("yyyy-MM-dd_HH-mm-ss");
    }

    /**
     * Method returned SystemDateAndTime In Format yyyyMMddHHmmss
     */
    public static String getDateAndTimeFormattedOnlyNumbers(){
        return getDateAndTime("yyyyMMddHHmmss");
    }

    /**
     * Method returned SystemDateAndTime
     */
    public static String getDateAndTime(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }



    public static void openNewTab(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        if (jsExecutor != null) {
            jsExecutor.executeScript("window.open();");
            waitABit(5);
            switchToNextTab(driver);
        } else {
            System.out.println("JavascriptExecutor is not initialized.");
        }
    }


    public static void switchToNextTab(WebDriver webDriver) {
        try {
            String currentHandle = webDriver.getWindowHandle();
            Set<String> handles = webDriver.getWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(currentHandle)) {
                    webDriver.switchTo().window(handle);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to switch to the next tab: " + e.getMessage());
        }
    }

    public static void switchToMainTab(WebDriver webDriver) {
        try {
            String currentHandle = webDriver.getWindowHandle();
            webDriver.switchTo().window(currentHandle);
        } catch (Exception e) {
            System.out.println("Failed to switch to the main tab: " + e.getMessage());
        }
    }

    public static void closeNewTab(WebDriver webDriver) {
        try {
            String currentHandle = webDriver.getWindowHandle();
            Set<String> handles = webDriver.getWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(currentHandle)) {
                    webDriver.switchTo().window(handle);
                    webDriver.close();
                    webDriver.switchTo().window(currentHandle);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to close the new tab: " + e.getMessage());
        }

    }
}
