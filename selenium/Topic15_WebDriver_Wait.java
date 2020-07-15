import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic15_WebDriver_Wait extends Common{

    @Test
    public void TC01_ElementStatus_Visable(){
        driver.get("https://www.facebook.com/");
        // Check lasname visible
        boolean status = driver.findElement(By.name("lastname")).isDisplayed();
        System.out.println(status);
        driver.findElement(By.xpath("//input[@name = 'reg_email__']")).sendKeys("huyho1210@gmail.com");
        // Check confirm_email visible
        status = driver.findElement(By.xpath("//input[@name = 'reg_email_confirmation__']")).isDisplayed();
        System.out.println(status);
        // Xóa email
        driver.findElement(By.xpath("//input[@name = 'reg_email__']")).clear();
        this.setDelay(3);
        // Check confirm_email invisiable
        status = driver.findElement(By.xpath("//input[@name = 'reg_email_confirmation__']")).isDisplayed();
        System.out.println(status);
        expcilitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name = 'reg_email_confirmation__']")));
    }
    @Test
    public void TC01_ElementStatus_InVisable(){
        driver.get("https://www.facebook.com/");
        expcilitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name = 'reg_email_confirmation__']")));
    }
    @Test
    public void TC01_ElementStatus_Presence(){
        driver.get("https://www.facebook.com/");
        //Not on UI
        expcilitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name = 'reg_email_confirmation__']")));
        // Not in DOm
                expcilitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id = 'identify_email']")));
    }
    @Test
    public void TC_02_StaticWait() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'finish']//h4[text() ='Hello World!']")).isDisplayed());
    }
    @Test
    public void TC_03_ImplicitWait(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")).isDisplayed());
    }
    @Test
    public void TC_04_Explicit(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        expcilitWait =  new WebDriverWait(driver,3);
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        expcilitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id ='loading']")));
    }
    @Test
    public void TC_04_Explicit_02(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        expcilitWait =  new WebDriverWait(driver,5);
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        expcilitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id ='loading']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")).isDisplayed());
    }
    @Test
    public void TC_05_Explicit_01(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        expcilitWait =  new WebDriverWait(driver,3);
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        expcilitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")).isDisplayed());
    }
    @Test
    public void TC_05_Explicit_02(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        expcilitWait =  new WebDriverWait(driver,5);
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        expcilitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")).isDisplayed());
    }
    @Test
    public void TC_06_Explicit(){
        driver.get("https://bit.ly/explicit-wait");
        expcilitWait =  new WebDriverWait(driver,15);
        expcilitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='rcTitlebar']")));
        String text = driver.findElement(By.xpath("//span[@class = 'label'][text()= 'No Selected Dates to display.']")).getText();
        System.out.println(text);
        driver.findElement(By.xpath("//tr[@class ='rcRow']//td//a[text() = '5']")).click();
        expcilitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='RadAjax RadAjax_Silk']//div[@class='raDiv']")));
        expcilitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='5']/parent::td[@class ='rcSelected']")));
        String text2 = driver.findElement(By.xpath("//span[@class = 'label']")).getText();
        System.out.println(text2);
        Assert.assertEquals(text2, "Sunday, July 5, 2020");
    }
}
