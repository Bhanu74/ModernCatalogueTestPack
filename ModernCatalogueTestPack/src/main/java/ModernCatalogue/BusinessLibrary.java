package ModernCatalogue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 01/05/2015.
 */
public class BusinessLibrary extends BaseClass {
    //Method for email Subscription
    public void emailSubscription(String email) {
        waitForElement(By.xpath("//div[@class='section email-signup']"),10);
        //verifying Email Signup Option is available on the page
        driver.findElement(By.xpath("//div[@class='section email-signup']")).isDisplayed();
        //providing email details for subscription
        driver.findElement(By.id("newsletter-email")).sendKeys(email);
        driver.findElement(By.xpath("//form[@id='newsletter-signup']/button")).click();
    }

    //Method to verify Sort functionality
    public boolean sortOrder(String order) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //verifying sort order by LowToHigh
        if (order.equalsIgnoreCase("LowToHigh")) {
        //select sort filter ""Price: Low to High"
        sortBy("Price: Low to High");
        waitForElement(By.xpath("//div[@class='item-price-container clearfix']//div[@class='item-price']"),20);
        //     Capturing the price labels of products in the page
        List<WebElement> prices1 = driver.findElements(By.xpath("//div[@class='item-price-container clearfix']//div[@class='item-price']"));
        double firstProdutPrice=getProductPrice(prices1,0);
            //Compare the prices whether they are in the right order
            for (int i = 1; i<prices1.size()-1 ; i++) {
              //  System.out.println("check 1_" + i);
               double nextProductPrice=getProductPrice(prices1,i);
               // System.out.println("firstProdutPrice is:" + firstProdutPrice);
               // System.out.println("nextProductPrice is:" + nextProductPrice);
                if (firstProdutPrice<=nextProductPrice)
                {
                    //Assign next price(value of next item) to first price(current item price).
                    firstProdutPrice = nextProductPrice;
                }else {
                    System.out.println("Incorrect Sorting order");
                    return false;
                }
            }
            return true;
       }
        //verifying sort order by HighToLow
        else if (order.equalsIgnoreCase("HighToLow")){
            //Select sort filter ""Price: High to Low"
            sortBy("Price: High to Low");
            waitForElement(By.xpath("//div[@class='item-price-container clearfix']//div[@class='item-price']"), 20);
            //     Capturing the price labels of products in the page
            List<WebElement> prices1 = driver.findElements(By.xpath("//div[@class='item-price-container clearfix']//div[@class='item-price']"));
            double firstProdutPrice=getProductPrice(prices1, 0);
            for (int i = 1; i<prices1.size()-1 ; i++) {
               // System.out.println("check 1_" + i);
                double nextProductPrice = getProductPrice(prices1,i);
              //  System.out.println("firstProdutPrice is:" + firstProdutPrice);
             //   System.out.println("nextProductPrice is:" + nextProductPrice);
                if (firstProdutPrice>=nextProductPrice)
                {
                    // Assign next price to first price.
                    firstProdutPrice = nextProductPrice;

                }else {
                    System.out.println("Incorrect Sorting order");
                    return false;
                }
            }
            return true;

        } else{
                 System.out.println("Invalid Sort order");
                 return false;
              }
    }

    public double getProductPrice(List<WebElement> prices,int number)
    {
        String no=prices.get(number).getText().substring(1);
        String result = no.replaceAll("[,]", "");
        return Double.valueOf(result);
    }

  public void waitForElement(By by,int waitTime)
  {
      WebElement myDynamicElement = (new WebDriverWait(driver, waitTime))
              .until(ExpectedConditions.presenceOfElementLocated(by));
  }

    public void goToSofasProductcatalougue() {
        //navigate To sofas Product catalouge
        driver.findElement(By.xpath("//ul[@id='modern-main-menu']/li[1]/a")).isDisplayed();
        driver.findElement(By.xpath("//ul[@id='modern-main-menu']/li[1]/a")).click();
        waitForElement(By.xpath("//div[@ class='col_one_quarter']/ul/li[2]/a"), 30);
        driver.findElement(By.xpath("//div[@ class='col_one_quarter']/ul/li[2]/a")).click();
    }

    public void sortBy(String category)
    {
        Utils.selectFromDropdownMenu(By.xpath("//select[@class = 'sorts']"),category);
    }
}