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
    @Test
    public void TC_01_WebElement(){
        OpenPage();
        if(driver.findElement(By.id("mail")).isDisplayed()) {
            driver.findElement(By.id("mail")).sendKeys("Automation Testing");
        }
        if(driver.findElement(By.id("under_18")).isDisplayed()){
            driver.findElement(By.id("under_18")).click();
        }
        if(driver.findElement(By.id("edu")).isDisplayed()){
            driver.findElement(By.id("edu")).sendKeys("Automation Testing");
        }
    }
    @Test
    public void TC_02_WebElement() {
        OpenPage();
        // Email textbox
        if (driver.findElement(By.id("mail")).isEnabled()) {
            System.out.println("Email textbox is Enable");
        } else {
            System.out.println("Email textbox is Disable");
        }
        // Under 18 radio
        if (driver.findElement(By.id("under_18")).isEnabled()) {
            System.out.println("Under 18 radio is Enable");
        } else {
            System.out.println("Under 18 radio is Disable");
        }
        // Education text area
        if (driver.findElement(By.id("edu")).isEnabled()) {
            System.out.println("Education text area is Enable");
        } else {
            System.out.println("Education text area is Disable");
        }
        // Job Role 01
        if (driver.findElement(By.id("job1")).isEnabled()) {
            System.out.println("Job Role 01 is Enable");
        } else {
            System.out.println("Job Role 01 is Disable");
        }
        // Job Role 02
        if (driver.findElement(By.id("job2")).isEnabled()) {
            System.out.println("Job Role 02 dropdown is Enable");
        } else {
            System.out.println("Job Role 02 dropdown is Disable");
        }
        // Development checkbox
        if (driver.findElement(By.id("development")).isEnabled()) {
            System.out.println("Development checkbox is enable");
        } else {
            System.out.println("Development checkbox is disable");
        }
        // Slider01
        if (driver.findElement(By.id("slider-1")).isEnabled()) {
            System.out.println("Slider 01 is enable");
        } else {
            System.out.println("Slider 01 is disable");
        }
        // Password
        if (driver.findElement(By.id("password")).isEnabled()) {
            System.out.println("Password field is Enable");
        } else {
            System.out.println("Password field is Disable");
        }
        //  Radio button is disabled
        if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
            System.out.println(" Radio button is Enable");
        } else {
            System.out.println(" Radio button is Disable");
        }
        // Biography text area
        if (driver.findElement(By.id("bio")).isEnabled()) {
            System.out.println("Biography text area is Enable");
        } else {
            System.out.println("Biography text area is Disable");
        }
        // Interests (checjbox is disable)
        if (driver.findElement(By.id("check-disbaled")).isEnabled()) {
            System.out.println("Chèckbox is Enable");
        } else {
            System.out.println("Chèckbox is Disable");
        }
        // Job Role 03
        if (driver.findElement(By.id("job3")).isEnabled()) {
            System.out.println("Job Role 03 dropdown is Enable");
        } else {
            System.out.println("Job Role 03 dropdown is Disable");
        }
        // Development checkbox
        if (driver.findElement(By.id("development")).isEnabled()) {
            System.out.println("Development checkbox is enable");
        } else {
            System.out.println("Development checkbox is disable");
        }
        // Slider02
        if (driver.findElement(By.id("slider-2")).isEnabled()) {
            System.out.println("Slider 02 is enable");
        } else {
            System.out.println("Slider 02 is disable");
        }
    }
    @Test
    public void TC_03_WebElement() {
        OpenPage();
        driver.findElement(By.id("under_18")).click();
        driver.findElement(By.id("development")).click();
        Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.id("development")).isSelected());
        driver.findElement(By.id("development")).click();
        Assert.assertTrue(!driver.findElement(By.id("development")).isSelected());
    }

}
