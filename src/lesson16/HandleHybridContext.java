package lesson16;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
public class HandleHybridContext {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try{
            AppiumDriver<MobileElement> appiumDriver = DriverFactory.getAndroidDriver();
            MobileElement webViewLabel = appiumDriver.findElementByAccessibilityId("Webview");
            webViewLabel.click();
            appiumDriver.getContextHandles().forEach(System.out::println);
            appiumDriver.context("WEBVIEW_com.wdiodemoapp");
            WebElement navToggleBtnElem= appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();
            List<MobileElement> menuItems = appiumDriver.findElementsByCssSelector(".menu__list-item a");
            List<MenuItem> menuItemList = new ArrayList<>();
            for (MobileElement menuItem : menuItems) {
                String menuText = menuItem.getText();
                String menuHyberlink = menuItem.getAttribute("href");
                if(StringUtils.isEmpty(menuText)) {
                    menuItemList.add(new MenuItem("GitHub", menuHyberlink));
                }else {
                    menuItemList.add(new MenuItem(menuText, menuHyberlink));
                }

            }
            menuItemList.forEach(System.out::println);
            //Switch to Native context
            appiumDriver.context("NATIVE_APP");
            //Interact with Native context
            MobileElement loginLabelElem = appiumDriver.findElementByAccessibilityId("Login");
            loginLabelElem.click();
            //App keep current state
            appiumDriver.runAppInBackground(Duration.ofSeconds(3));
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactory.stopAppiumServer();
        }
    }
    public static  class MenuItem{
        private String text;
        private String hyperlink;
        public MenuItem(String text, String hyperlink) {
            this.text = text;
            this.hyperlink = hyperlink;
        }

        public String getText() {
            return text;
        }

        public String getHyperlink() {
            return hyperlink;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "text='" + text + '\'' +
                    ", hyperlink='" + hyperlink + '\'' +
                    '}';
        }
    }

}
