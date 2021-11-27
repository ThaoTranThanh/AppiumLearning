package lesson17;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class SwipeToOpenNotification {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
// Get Mobile window size

            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();
            //Calculator touch point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 0;
            int yEndPoint = 50 * screenHeight / 100;
            //Convert to PointOption -Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(yStartPoint, yEndPoint);
            //Perform Touch action
            TouchAction touchAction = new TouchAction(androidDriver);
            //Swipe down to open notification
            touchAction.press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();//without this, will be no action at all
            Thread.sleep(2000);
            //get the info inside the notification by getting a list
            List<MobileElement> notificationElems = androidDriver.findElements(By.id("android:id/notification_main_column"));
            if (notificationElems.isEmpty()) {
                throw new RuntimeException("Notification List is empty");
            }
            Map<String, String> notificationList = new HashMap<>();

            notificationElems.forEach(notification -> {
                String notificationTitle;
                String notificationContent;
                try {
                    notificationTitle = notification.findElement(By.id("android:id/title")).getText();
                    notificationContent = notification.findElement(By.id("android:id/big_text")).getText();
                } catch (Exception exception) {
                    notificationTitle = "Not found Title";
                    notificationContent = "Not found Content";
                }
                notificationList.put(notificationTitle, notificationContent);
            });
            touchAction.press(endPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(startPoint)
                    .release()
                    .perform();//without this, will be no action at all
            Thread.sleep(2000);
            notificationList.keySet().forEach(key -> System.out.println(key + "" + notificationList.get(key)));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }

    }
}
