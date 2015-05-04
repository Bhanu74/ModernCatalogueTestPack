package ModernCatalogue;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

/**
 * Created by arunabhamidipati on 01/05/2015.
 */
public class TestSuite extends BaseTest{

    //Test Data
    String ValidEmailID="test" + new Random().nextInt() + "@gmail.com";
    String InVlidEmailID="modern.modern.com";
    int productQuantity=3;
    List<WebElement> prices1;
    BusinessLibrary blib=new BusinessLibrary();
    //Test to Verify that user can subscribe with a valid Email ID
    @Test
    public void validEmailVerification()
    {
        blib.emailSubscription(ValidEmailID);
        Assert.assertEquals("Email address subscribed", driver.findElement(By.cssSelector(".newsletter-signup-successmsg")).getText());
    }

    //Test to Verify that user can not subscribe with invalid Email ID and get proper notification
    @Test
    public void invalidEmailVerification()
    {
        blib.emailSubscription(InVlidEmailID);
        Assert.assertEquals("Invalid email address", driver.findElement(By.cssSelector(".newsletter-signup-errormsg")).getText());
    }

    //Test to Verify that user can not subscribe without Email ID and get proper notification
    @Test
    public void blankEmailVerification()
    {
        blib.emailSubscription("");
        Assert.assertEquals("Enter an email address",driver.findElement(By.cssSelector(".newsletter-signup-errormsg")).getText());

    }



//Verifying the Sort Order Functionality
    @Test
    public void sortByLowToHigh() {
        blib.goToSofasProductcatalougue();
       // System.out.println("Check 1");
        blib.sortOrder("LowToHigh");
      //  System.out.println("Check 2");
         }
    @Test
    public void sortByHighToLow() {
        blib.goToSofasProductcatalougue();
        blib.sortOrder("HighToLow");
    }

 //verifying the  Price Calculation in Shopping Basket

    @Test
    public void productPriceCalculation()
    {
         //Go to Sofas product catalogue
        driver.findElement(By.linkText("Sofas")).click();
        //Go to product page
        driver.findElement(By.linkText("Charlie Corner Sofa...")).click();
        //Add item to Shopping Basket
        driver.findElement(By.id("ws-btnaddcart")).click();

        //Go to Shopping basket page
        driver.findElement(By.cssSelector("a[title=\"checkout\"]")).click();

        //Capture product Price
        double productPrice=Double.valueOf(driver.findElement(By.cssSelector("span.product_price")).getText().substring(1));

        //Capture intial Total Price
        double totalPrice=Double.valueOf(driver.findElement(By.xpath("//td[2]/span[2]")).getText().substring(1));
        Assert.assertTrue(productPrice == totalPrice);

        //change product quantity
        Utils.selectFromDropdownMenu(By.name("quantity"), Integer.toString(productQuantity));
        Assert.assertTrue(Utils.closeAlertAndGetItsText().contains("You are about to alter the quantity of"));

        //Capture Goods Total Price
        String GoodsTotal=driver.findElement(By.xpath("//td[2]/span[2]")).getText().substring(1);
        String result = GoodsTotal.replaceAll("[,]", "");
        double finalTotalPrice=Double.valueOf(result);

        //Verify the final total price is correct
        Assert.assertTrue(finalTotalPrice==productQuantity*productPrice);

    }
}
