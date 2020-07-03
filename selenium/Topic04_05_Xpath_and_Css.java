import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.PortUnreachableException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic04_05_Xpath_and_Css {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://live.demoguru99.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void TC_01_LoginWithEmptyEmailAndPass() {
        driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("");
        driver.findElement(By.id("pass")).sendKeys("");
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
    }

    @Test
    public void TC_02_LoginWithInvalidEmail() {
        driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_LoginWithPasswordLessThan6Chars() {
        driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123");
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_LoginWithInvalidPass() {
        driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123123123");
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.className("error-msg")).getText(), "Invalid login or password.");

    }

    @Test
    public void TC_05_LoginWithValidAccountInfo() {

        driver.findElement(By.cssSelector("header#header div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("div#header-account li.last > a")).click();
        driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
        driver.findElement(By.name("login[password]")).sendKeys("123123");
        driver.findElement(By.name("send")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'My Dashboard')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'My Dashboard')]/parent::div/following-sibling::div[@class='welcome-msg']/child::p/strong[contains(text(),'Hello, Automation Testing!')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Automation Testing')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'automation@gmail.com')]")).isDisplayed());

        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[@class='label']")).click();
        driver.findElement(By.xpath("//div[@id= 'header-account']//li[last()]")).click();

    }

    @Test
    public void TC_06() {
        String email = "huyho" + Random() + "@mailinator.com";
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.id("firstname")).sendKeys("Huy");
        driver.findElement(By.id("middlename")).sendKeys("Doãn Quốc");
        driver.findElement(By.id("lastname")).sendKeys("Hồ");
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("confirmation")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@title ='Register']/span")).click();


        String msgSuccess = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
        Assert.assertEquals(msgSuccess, "Thank you for registering with Main Website Store.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Huy Doãn Quốc Hồ')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,email)]")).isDisplayed());
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[@class='label']")).click();
        driver.findElement(By.xpath("//div[@id= 'header-account']//li[last()]")).click();
        String msgLogoutS = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();
        Assert.assertEquals(msgLogoutS, "YOU ARE NOW LOGGED OUT");
    }
    //    @AfterTest
//    public void CloseCase(){
//        driver.close();
//    }
    public int Random() {
        Random r = new Random();
        return r.nextInt(999999);
    }
}