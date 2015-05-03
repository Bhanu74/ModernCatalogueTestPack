package ModernCatalogue;

//import org.junit.Assert;
import org.openqa.selenium.By;

/**
 * Created by User on 01/05/2015.
 */
public class BusinessLibrary extends BaseClass {
    //Method for email Subscription
    public void emailSubscription(String email) {
        //verifying Email Signup Option is available on the page
        driver.findElement(By.xpath("//div[@class='section email-signup']")).isDisplayed();
        //providing email details for subscription
        driver.findElement(By.id("newsletter-email")).sendKeys(email);
        driver.findElement(By.xpath("//form[@id='newsletter-signup']/button")).click();
    }

    //Method to verify Sort functionality
    public void sortOrder(String order) {
        driver.findElement(By.xpath("//ul[@id='modern-main-menu']/li[1]/a")).click();
        driver.findElement(By.xpath("//div[@ class='col_one_quarter']/ul/li[2]/a")).click();


        //verifying sort order by LowToHigh
        if (order.equalsIgnoreCase("LowToHigh")) {
        //select sort filter ""Price: Low to High"
        Utils.selectFromDropdownMenu(By.xpath("//select[@class = 'sorts']"), "Price: Low to High");
 //     Capturing all the price labels and store them in a list
//      List<WebElement> prices1 = driver.findElements(By.xpath("//div[@class='item-price-container clearfix']//div[@class='item-price']"));

       }
        //verifying sort order by HighToLow
        else if (order.equalsIgnoreCase("HighToLow")){
            //Select sort filter ""Price: High to Low"
            Utils.selectFromDropdownMenu(By.xpath("//select[@class = 'sorts']"),"Price: High to Low");

        } else{
                 System.out.println("Invalid Sort order");
              }
    }

 }