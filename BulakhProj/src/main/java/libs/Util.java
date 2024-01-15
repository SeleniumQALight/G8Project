package libs;


import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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



    public static void switchTab(WebDriver webDriver) {
        try {
            String currentHandle = webDriver.getWindowHandle();
            webDriver.switchTo().window(currentHandle);
        } catch (Exception e) {
            System.out.println("Failed to switch to the main tab: " + e.getMessage());
        }
    }

    public static void closeTab(WebDriver webDriver) {
        try {
            String currentHandle = webDriver.getWindowHandle();
            webDriver.close();
            webDriver.switchTo().window(currentHandle);
        } catch (Exception e) {
            System.out.println("Failed to close the tab: " + e.getMessage());
        }
    }
}
