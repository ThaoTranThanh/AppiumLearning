package lesson15;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class lab15 {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try{
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            //Click on forms label
            MobileElement tabForm = androidDriver.findElementByAccessibilityId("Swipe");
            tabForm.click();
            // Check to see whether we are on focus screen
            // Check to see whether we are on focus screen
            WebDriverWait wait = new WebDriverWait(androidDriver,30L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[@text= 'Swipe horizontal']")));
            // get Mobile window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();
            //Calculate touch point
            int xStartPoint = 50* screenWidth/100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 90* screenHeight/100;
            int yEndPoint = 10 *screenHeight/100;

            //Convert to PointOptions -Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint,yEndPoint);

            // Perform touch actions
            TouchAction touchAction = new TouchAction(androidDriver);
            int MAX_SWIPE_TIME =2;
            int swipeTime =0;
            while (swipeTime< MAX_SWIPE_TIME){

                List<MobileElement> matchedCards = androidDriver.findElementsByAccessibilityId("WebdriverIO logo");
                if (!matchedCards.isEmpty()){ break; }
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                        .moveTo(endPoint)
                        .release()
                        .perform();
            }
            swipeTime ++;


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
