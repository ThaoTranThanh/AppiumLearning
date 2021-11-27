package lesson17;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

public class HandelMultipleApps {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
            List<MobileElement> credsFormlElem = androidDriver.findElementsByXPath("//android.widget.EditText");
            MobileElement loginButton = androidDriver.findElementByAccessibilityId("button-LOGIN");
            final int INPUT_EMAIL_INDEX = 0;
            final int INPUT_PASSWORD_INDEX = 1;
            credsFormlElem.get(INPUT_EMAIL_INDEX).sendKeys("send@mailinator.com");
            credsFormlElem.get(INPUT_PASSWORD_INDEX).sendKeys("98765432");
            loginButton.click();
            //Put webdriverio demo app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));
            //Open setting application
            androidDriver.activateApp("com.android.settings");
            //Do sth here
            androidDriver.findElementByXPath("//*[@text='Connections']").click();
            androidDriver.findElementByAccessibilityId("Wi-Fi").click();
            //IF ON  ->OFF and then ON| ELSE ->ON
            MobileElement wifiSwichBtnElem = androidDriver.findElement(By.id("android:id/switch_widget"));
            boolean isWifiOn = wifiSwichBtnElem.getText().equals("ON");
            if (isWifiOn) {
                //Change to OFF
                wifiSwichBtnElem.click();
                //Chang to ON again
                wifiSwichBtnElem.click();
            }else {
                wifiSwichBtnElem.click();
            }
            // Re-launchWebDriverIO app
            androidDriver.activateApp("com.wdiodemoapp");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }

    }

}
