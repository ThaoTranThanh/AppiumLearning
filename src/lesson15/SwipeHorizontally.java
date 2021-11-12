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

public class SwipeHorizontally {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try{
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            //Click on forms label
            MobileElement tabForm = androidDriver.findElementByAccessibilityId("Swipe");
            tabForm.click();
            // Check to see whether we are on focus screen
            WebDriverWait wait = new WebDriverWait(androidDriver,30L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[@text= 'Swipe horizontal']")));
            // get Mobile window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();
            //Calculate touch point
            int xStartPoint = 50* screenWidth/100;
            int xEndPoint = 10 * screenWidth/100;
            int yStartPoint = 50* screenHeight/100;
            int yEndPoint = yStartPoint;

            //Convert to PointOptions -Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint,yEndPoint);

            // Perform touch actions
            TouchAction touchAction = new TouchAction(androidDriver);
            for (int i = 0; i < 5; i++) {
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                        .moveTo(endPoint)
                        .release()
                        .perform();
            }




        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
