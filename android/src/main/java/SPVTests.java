import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import quamotion.webdriver.AppCapabilities;
import quamotion.webdriver.AppDriver;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by darko on 12/09/16.
 */
public class SPVTests implements MenuTester {

    private AppDriver driver;

    @Before
    public void setUp2() throws Exception {
        AppCapabilities capabilities = new AppCapabilities("05157df5941e913d", "be.vrt.mobile.android.sporza.voetbal");
        //AppCapabilities capabilities = new AppCapabilities("1ae6703e", "be.vrt.mobile.android.sporza.voetbal");
        capabilities.setCapability("reuseExistingSession", true);
        //capabilities.reuseSession(true);
        driver = new AppDriver(capabilities);
        driver.WaitUntilReady();


    }

    @Test
    public void FirstTest() throws IOException, InterruptedException {
        // zoek element kiesFavorietePloegenText met text 'Kies favoriete ploegen om meldingen te ontvangen.'
        WebElement kiesFavorietePloegenText = driver.findElement(By.id("pushnotifications_select_favorites_too"));
        System.out.println(kiesFavorietePloegenText.getText());
        System.out.println(kiesFavorietePloegenText.isDisplayed());
        // bevestig dat tekst van element kiesFavorietePloegenText gelijk is
        // aan tekst 'Kies favoriete ploegen om meldingen te ontvangen.'
        Assert.assertEquals(kiesFavorietePloegenText.getText(), "Kies favoriete ploegen om meldingen te ontvangen.");
        Assert.assertEquals(kiesFavorietePloegenText.isDisplayed(), true);
        WebElement chkbx = driver.findElement(By.id("pushnotifications_switch"));
        chkbx.click();
        System.out.println(kiesFavorietePloegenText.isDisplayed());
        Assert.assertEquals(kiesFavorietePloegenText.isDisplayed(), false);
        chkbx.click();
        Assert.assertEquals(kiesFavorietePloegenText.isDisplayed(), true);
        WebElement overslaanKnop = driver.findElement(By.id("skip"));
        overslaanKnop.click();

        // er MOET een beetje gewacht worden zodat alle items geladen zijn voordat we verder gaan
        Thread.sleep(3000);

        String[] alleMenuItems = {"Home", "Competities", "Teams", "Jupiler Pro League", "Proximus League",
                "Croky Cup", "Premier League", "WK Kwalificatie", "Instellingen"};

        for (String menuItem : alleMenuItems) {
            zoekMenuItemEnKlik(driver, menuItem);
            klikOpMenuKnop(driver);
        }

        System.out.println("end test");

    }


    public static void zoekMenuItemEnKlik(AppDriver thisDriver, String menuItem) {
        List<WebElement> elements = thisDriver.findElements(By.id("material_drawer_name"));
        System.out.println("---");
        //System.out.println(elements.size());
        Iterator<WebElement> itr = elements.iterator();
        while(itr.hasNext()) {
            WebElement elementWeNeed = itr.next();
            System.out.println(elementWeNeed.getText());
            if (elementWeNeed.getText().equals(menuItem)) {
                elementWeNeed.click();
                break;
            }
        }

    }

    public static void klikOpMenuKnop(AppDriver thisDriver) {
        WebElement menuKnop = thisDriver.findElement(By.xpath("AppCompatImageButton"));
        menuKnop.click();
    }




    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void clickAllTheMenuItems() {

    }
}