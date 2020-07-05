import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic06_WebBrowser {
    WebDriver driver;
    @BeforeClass
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://live.demoguru99.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TC01_VerifyURL(){
        driver.findElement(By.xpath("//div[@class= 'footer']//a[@title = 'My Account']")).click();
        Assert.assertEquals("Customer Login", driver.getTitle());
        Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/login/", driver.getCurrentUrl());
        driver.findElement(By.xpath("//div[@class='buttons-set']//span[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/create/", driver.getCurrentUrl());
    }
    @Test
    public void TC02_VerifyTitle(){
        driver.findElement(By.xpath("//div[@class= 'footer']//a[@title = 'My Account']")).click();
        Assert.assertEquals("Customer Login", driver.getTitle());
        driver.findElement(By.xpath("//div[@class='buttons-set']//span[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals("Create New Customer Account",driver.getTitle());
    }
    @Test
    public void TC03_NavigationFunction(){
        driver.findElement(By.xpath("//div[@class= 'footer']//a[@title = 'My Account']")).click();
        driver.findElement(By.xpath("//div[@class='buttons-set']//span[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/create/", driver.getCurrentUrl());
        driver.navigate().back();
        Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/login/", driver.getCurrentUrl());
        driver.navigate().forward();
        Assert.assertEquals("Create New Customer Account",driver.getTitle());
    }
    @Test
    public void TC04_GetPageSource(){
        driver.findElement(By.xpath("//div[@class= 'footer']//a[@title = 'My Account']")).click();
        String sourceLogin = driver.getPageSource();
        Assert.assertTrue(sourceLogin.contains("Login or Create an Account"));
        driver.findElement(By.xpath("//div[@class='buttons-set']//span[contains(text(),'Create an Account')]")).click();
        String sourceRegister = driver.getPageSource();
        Assert.assertTrue(sourceRegister.contains("Create an Account"));
    }
    @Test
    public void TC03_GetPageSource(){
        driver.findElement(By.xpath("//div[@class= 'footer']//a[@title = 'My Account']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//h1[contains(text(),'Login or Create an Account')]")).isDisplayed());
        driver.findElement(By.xpath("//div[@class='buttons-set']//span[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals("CREATE AN ACCOUNT" ,driver.findElement(By.xpath("//div[@class='page-title']")).getText());
    }
    @AfterTest
    public  void Close(){
        driver.close();
    }
}
