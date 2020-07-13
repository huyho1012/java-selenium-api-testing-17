import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Topic08_DefaultDropDownList extends Common{
    String email = "huyho" + this.Random() + "@mailinator.com";
    @Test
    public void TC_01() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Select job1 = new Select(driver.findElement(By.id("job1")));
        Assert.assertFalse(job1.isMultiple());
        job1.selectByVisibleText("Mobile Testing");
        Assert.assertTrue(job1.getFirstSelectedOption().isSelected());
        job1.selectByValue("manual");
        Assert.assertTrue(job1.getFirstSelectedOption().isSelected());
        job1.selectByIndex(9);
        Assert.assertTrue(job1.getFirstSelectedOption().isSelected());
        Assert.assertEquals(job1.getOptions().size(), 10);
        Select job2 = new Select(driver.findElement(By.id("job2")));
        Assert.assertTrue(job2.isMultiple());
        job2.selectByVisibleText("Automation");
        job2.selectByVisibleText("Mobile");
        job2.selectByVisibleText("Desktop");
        int numOpt = job2.getAllSelectedOptions().size();
        Assert.assertEquals(numOpt, 3);

        List<WebElement> options = job2.getAllSelectedOptions();

        for (WebElement option : options) {
            System.out.println(option.getText());
        }
        job2.deselectAll();
        int numOpt2 = job2.getAllSelectedOptions().size();
        Assert.assertEquals(numOpt2, 0);
    }

    @Test
    public void TC_02() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.xpath("//a[@class= 'ico-register']")).click();
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Huy");
        driver.findElement(By.id("LastName")).sendKeys("Hồ");
        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByValue("1");
        int numDay = day.getOptions().size();
        Assert.assertEquals(numDay, 32);
        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByValue("5");
        int numMonth = month.getOptions().size();
        Assert.assertEquals(numMonth, 13);
        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByValue("1980");
        int numYear = year.getOptions().size();
        Assert.assertEquals(numYear, 112);

        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Company")).sendKeys("Hahalolo Company");
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[@class= 'ico-account']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class= 'ico-logout']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class ='result']")).getText(), "Your registration completed");
    }
}