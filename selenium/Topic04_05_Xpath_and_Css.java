import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic04_05_Xpath_and_Css {
    WebDriver driver;
    @Test
    public  void TC_01_LoginWithEmptyEmailAndPass(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.cssSelector("header#header div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("div#header-account li.last > a")).click();
        driver.findElement(By.name("login[username]")).sendKeys("");
        driver.findElement(By.name("login[password]")).sendKeys("");
        driver.findElement(By.name("send")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
    }
    @Test
    public void TC_02_LoginWithInvalidEmail(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.cssSelector("header#header div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("div#header-account li.last > a")).click();
        driver.findElement(By.name("login[username]")).sendKeys("123434234@12312.123123");
        driver.findElement(By.name("login[password]")).sendKeys("123456");
        driver.findElement(By.name("send")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_LoginWithPasswordLessThan6Chars(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.cssSelector("header#header div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("div#header-account li.last > a")).click();
        driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
        driver.findElement(By.name("login[password]")).sendKeys("123");
        driver.findElement(By.name("send")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_LoginWithInvalidPass(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.cssSelector("header#header div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("div#header-account li.last > a")).click();
        driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
        driver.findElement(By.name("login[password]")).sendKeys("123123123");
        driver.findElement(By.name("send")).click();
        Assert.assertEquals(driver.findElement(By.id("html#top li > span")).getText(), "Invalid login or password.");
    }

    @Test
    public void TC_04_LoginWithValidAccountInfo(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.cssSelector("header#header div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("div#header-account li.last > a")).click();
        driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
        driver.findElement(By.name("login[password]")).sendKeys("123123");
        driver.findElement(By.name("send")).click();
        Assert.assertEquals(driver.findElement(By.id("html#top li > span")).getText(), "Invalid login or password.");
    }
    @AfterTest
    public void CloseTestcase(){
        driver.close();
    }
}
