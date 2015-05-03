package ModernCatalogue;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 01/05/2015.
 */
public class BaseTest extends BaseClass{

    @Before
    public void startBrowser() {
        driver = new FirefoxDriver();
        driver.get("http://www.modern.co.uk/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void stopBrowser() {
        //  driver.quit();
          }

}
