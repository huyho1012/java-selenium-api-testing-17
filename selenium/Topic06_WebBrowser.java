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
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://live.demoguru99.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TC01_VerifyURL(){
        OpenBrowser();
        driver.findElement(By.xpath("//div[@class= 'footer']//a[@title = 'My Account']")).click();
        Assert.assertEquals("Customer Login", driver.getTitle());
        Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/login/", driver.getCurrentUrl());
        driver.findElement(By.xpath("//div[@class='buttons-set']//span[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/create/", driver.getCurrentUrl());
    }
    @Test
    public void TC02_VerifyTitle(){
        OpenBrowser();
        driver.findElement(By.xpath("//div[@class= 'footer']//a[@title = 'My Account']")).click();
        Assert.assertEquals("Customer Login", driver.getTitle());
        driver.findElement(By.xpath("//div[@class='buttons-set']//span[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals("Create New Customer Account",driver.getTitle());
    }
    @Test
    public void TC03_NavigationFunction(){
        OpenBrowser();
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
        OpenBrowser();
        driver.findElement(By.xpath("//div[@class= 'footer']//a[@title = 'My Account']")).click();
        String sourceLogin = driver.getPageSource();
        Assert.assertTrue(sourceLogin.contains("Login or Create an Account"));
        driver.findElement(By.xpath("//div[@class='buttons-set']//span[contains(text(),'Create an Account')]")).click();
        String sourceRegister = driver.getPageSource();
        Assert.assertTrue(sourceRegister.contains("Create an Account"));
    }
//    @AfterTest
//    public  void Close(){
//        driver.close();
//    }

    public void OpenPage(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    public boolean isDisplay(String xpathValue){
        if(driver.findElement(By.xpath(xpathValue)).isDisplayed()) return true;
        return false;
    }
    @Test
    public void TC_01_WebElement(){
        OpenPage();
        if(isDisplay("//input[@id = 'email']")){
            driver.findElement(By.id("mail")).sendKeys("Automation Testing");
        }
        if(isDisplay("//input[@id = 'under_18']")){
            driver.findElement(By.id("under_18")).click();
        }
        if(isDisplay("//textarea[@id = 'edu']")){
            driver.findElement(By.id("edu")).sendKeys("Automation Testing");
        }
    }
    public boolean isEnable(String xpathValue){
        if(driver.findElement(By.xpath(xpathValue)).isEnabled()){
            System.out.println("Element is enable");
            return true;
        } else {
            System.out.println("Element is disable");
            return false;
        }
    }
    @Test
    public void TC_02_WebElement() {
        OpenPage();

        Assert.assertTrue(isEnable("//input[@id = 'email']"));
        Assert.assertTrue(isEnable("//input[@id = 'under_18']"));
        Assert.assertTrue(isEnable("//textarea[@id = 'edu']"));
        Assert.assertTrue(isEnable("//select[@id = 'job1']"));
        Assert.assertTrue(isEnable("//select[@id = 'job2']"));
        Assert.assertTrue(isEnable("//input[@id = 'development']"));
        Assert.assertTrue(isEnable("//input[@id = 'slider-1']"));

        Assert.assertFalse(isEnable("//input[@id = 'password']"));
        Assert.assertFalse(isEnable("//input[@id = 'radio-disabled']"));
        Assert.assertFalse(isEnable("//textarea[@id = 'bio']"));
        Assert.assertFalse(isEnable("//input[@id = 'check-disbaled']"));
        Assert.assertFalse(isEnable("//select[@id = 'job3']"));
        Assert.assertFalse(isEnable("//input[@id = 'slider-2']"));
    }
    public boolean isSelected(String xpathValue){
        if(driver.findElement(By.xpath(xpathValue)).isSelected())
            return true;
            return false;
    }
    @Test
    public void TC_03_WebElement() {
        OpenPage();
        driver.findElement(By.id("under_18")).click();
        driver.findElement(By.id("development")).click();
        Assert.assertTrue(isSelected("//input[@id = 'under_18']"));
        Assert.assertTrue(isSelected("//input[@id = 'development']"));
        driver.findElement(By.id("development")).click();
        Assert.assertFalse(isSelected("//input[@id = 'development']"));
    }

}
