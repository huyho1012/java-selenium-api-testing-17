import com.google.common.collect.RangeMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic04_05_Xpath_and_Css {
    WebDriver driver;
    String email = "huyho" + Random() + "@mailinator.com";
    @BeforeMethod
    public void getLoginPage(){
        System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://live.demoguru99.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class =  'footer']//a[@title= 'My Account']")).click();
        setTimeDelay(1);
    }
    @Test
    public void TC_01_LOGIN_WITH_EMPTY_EMAIL_AND_PASSWORD(){
        driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("");
        driver.findElement(By.xpath("//input[@id ='pass']")).sendKeys("");
        driver.findElement(By.xpath("//button[@id = 'send2']")).click();
        String errMessageValidateOfUserName = driver.findElement(By.xpath("//div[@id ='advice-required-entry-email']")).getText();
        String errMessageValidateOfPassword = driver.findElement(By.xpath("//div[@id ='advice-required-entry-pass']")).getText();
        Assert.assertEquals(errMessageValidateOfUserName,"This is a required field.");
        Assert.assertEquals(errMessageValidateOfPassword,"This is a required field.");
    }
    @Test
    public void TC_02_LOGIN_WITH_INVALID_EMAIL(){
        driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("12343432434534@12313.1213");
        driver.findElement(By.xpath("//input[@id ='pass']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id = 'send2']")).click();
        String errMessageValidateOfUserName = driver.findElement(By.xpath("//div[@id ='advice-validate-email-email']")).getText();
        Assert.assertEquals(errMessageValidateOfUserName,"Please enter a valid email address. For example johndoe@domain.com.");
    }
    @Test
    public void TC_03_LOGIN_WITH_PASSWORD_LESS_THAN_6_CHARS(){
        driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id ='pass']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@id = 'send2']")).click();
        String errMessageValidateOfPassword = driver.findElement(By.xpath("//div[@id ='advice-validate-password-pass']")).getText();
        Assert.assertEquals(errMessageValidateOfPassword,"Please enter 6 or more characters without leading or trailing spaces.");
    }
    @Test
    public void TC_04_LOGIN_WITH_INCORRECT_PASSWORD(){
        driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id ='pass']")).sendKeys("123123123");
        driver.findElement(By.xpath("//button[@id = 'send2']")).click();
        String errMessageInvalidInfo = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
        Assert.assertEquals(errMessageInvalidInfo,"Invalid login or password.");
    }
    @Test
    public void TC_05_LOGIN_WITH_VALID_USER_NAME_AND_PASS(){
        driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id ='pass']")).sendKeys("123123");
        driver.findElement(By.xpath("//button[@id = 'send2']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//h1")).isDisplayed());
    }
    @Test
    public void TC_06_CREATE_A_NEW_ACCOUNT(){
        driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
        driver.findElement(By.xpath("//input[@id ='firstname']")).sendKeys("Huy");
        driver.findElement(By.xpath("//input[@id ='lastname']")).sendKeys("Huy");
        driver.findElement(By.xpath("//input[@id ='email_address']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id ='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id ='confirmation']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@title ='Register']")).click();
        setTimeDelay(3);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//div[@id='header-account']//li[last()]")).click();
        setTimeDelay(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h2[contains(text(),'This is demo site for')]")).isDisplayed());
    }

    @AfterTest
    public void closePage(){
        driver.close();
    }
    public int Random(){
        Random random = new Random();
        return random.nextInt(1000);
    }
    public void setTimeDelay(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}