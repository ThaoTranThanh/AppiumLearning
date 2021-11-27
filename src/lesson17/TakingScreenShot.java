package lesson17;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class TakingScreenShot
{
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try{
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginlabel = androidDriver.findElementByAccessibilityId("Login");
            loginlabel.click();
            //Wait until we are on Login Screen
            WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");
            wait.until(ExpectedConditions.visibilityOf(loginBtnElem));


            //Taking whole screen
            File base64ScreenShotData =  androidDriver.getScreenshotAs(OutputType.FILE);
            String  fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginForm.png");
            FileUtils.copyFile(base64ScreenShotData, new File(fileLocation));
            //Taking element screenshot FAB - Floating Action Button
            File base64ScreenLoginBtnData =  loginBtnElem.getScreenshotAs(OutputType.FILE);
            String  loginBtnImgFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginBtnElem.png");
            FileUtils.copyFile(base64ScreenLoginBtnData, new File(loginBtnImgFileLocation));
            //Taking area screenshot
            List<MobileElement> viewGroupElms = androidDriver.findElementsByXPath("//android.view.ViewGroup/android.view.ViewGroup[2]");
            if (!viewGroupElms.isEmpty()) {
                File base64NavData = viewGroupElms.get(viewGroupElms.size() -1).getScreenshotAs(OutputType.FILE);
                String navImgLocation = System.getProperty("user.dir").concat("/screenshots/").concat("nav.png");
                FileUtils.copyFile(base64NavData,new File(navImgLocation));
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
