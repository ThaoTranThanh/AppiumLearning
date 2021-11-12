package lesson15;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import driver.DriverFactory;

public class FormScreenTest {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try{
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement tabForm = androidDriver.findElementByAccessibilityId("Forms");
            tabForm.click();
            MobileElement activeBtnElem = androidDriver.findElementByAccessibilityId("button-Active");
            activeBtnElem.click();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }

    }
}
