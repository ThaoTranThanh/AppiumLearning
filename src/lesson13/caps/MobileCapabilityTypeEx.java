package lesson13.caps;

import io.appium.java_client.remote.MobileCapabilityType;

public interface MobileCapabilityTypeEx extends MobileCapabilityType {
    String APP_PACKAGE = "appPackage";
    String APP_ACTIVITY = "appActivity";
//    String CHROMEDRIVER_EXECUTABLE = "chromedriverExecutable"; //sai desiredCapbility
//    String CHROMEDRIVER_EXECUTABLE = "chromedriverExecutableDir";
}
