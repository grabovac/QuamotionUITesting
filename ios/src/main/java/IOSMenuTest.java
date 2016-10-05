import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import quamotion.webdriver.AppCapabilities;
import quamotion.webdriver.AppDriver;

import java.io.IOException;

/**
 * Created by darko on 23/09/16.
 * Modified by wardvb on 26/09/16.
 */

public class IOSMenuTest{


    private AppDriver driver;

    private String[] AllTabs = {"Home", "Wedstrijden", "Video's", "Nieuws", "Teams"};

    @Before
    public void setUp2() throws Exception {
        AppCapabilities capabilities = new AppCapabilities("a948902e38a5129313e3e33a56151bcc509375d2", "be.vrt.mobile.ios.themasites.sporza.live.football.Debug");
        capabilities.setCapability("reuseExistingSession", true);
        driver = new AppDriver(capabilities);
        driver.WaitUntilReady();
    }

    @Test
    public void clickWedstrijden( ) throws IOException, InterruptedException {
        WebElement kiesWedstrijden = driver.findElement(By.xpath("UITabBarButton[@marked='"+ AllTabs[5] + "']"));
        //WebElement kiesWedstrijden = driver.findElement(By.id("Teams"));
        kiesWedstrijden.click();
        Thread.sleep(3000);
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}