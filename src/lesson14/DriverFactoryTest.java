package lesson14;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.List;

public class DriverFactoryTest  {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {

            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

//Take about relative Xpath
            List<MobileElement> credsFormlElem = androidDriver.findElementsByXPath("//android.widget.EditText");
            final int INPUT_EMAIL_INDEX =0;
            final int INPUT_PASSWORD_INDEX =1;
            credsFormlElem.get(INPUT_EMAIL_INDEX).sendKeys("send@mailinator.com");
            credsFormlElem.get(INPUT_PASSWORD_INDEX).sendKeys("987654");
//            MobileElement emailInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
//            MobileElement passwordInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginButton = androidDriver.findElementByAccessibilityId("button-LOGIN");
            //Input Email/Password
//            emailInputElem.sendKeys("Test@mailinator.com");
//            passwordInputElem.sendKeys("123456");
            loginButton.click();
            MobileElement loginFeatureDes = androidDriver.findElementByXPath("//*[contains(@text,'When the device')]");
            MobileElement loginFeatureDesUiSelector =
                    androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"When the device\").className(\"android.widget.TextView\")");
            System.out.println(loginFeatureDes.getText());
            System.out.println(loginFeatureDesUiSelector.getText());
        }catch(Exception ignore){}
        finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
