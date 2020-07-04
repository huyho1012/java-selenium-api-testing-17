import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic04_05_Xpath_and_Css {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://live.demoguru99.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    @Test
    public void TC_01_LoginWithEmptyEmailAndPass(){
        driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
        driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("");
        driver.findElement(By.xpath("//button[@name ='send']")).click();
        Assert.assertEquals("This is a required field.", driver.findElement(By.xpath("//div[@id ='advice-required-entry-email']")).getText());
        Assert.assertEquals("This is a required field.", driver.findElement(By.xpath("//div[@id ='advice-required-entry-pass']")).getText());
    }
    @Test
    public void TC_02_LoginWithInvalidEmail(){
        driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
        driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("123434234@12312.123123");
        driver.findElement(By.xpath("//input[@id = 'pass']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id = 'send2']")).click();
        Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", driver.findElement(By.xpath("//div[@id = 'advice-validate-email-email']")).getText());
    }

    @Test
    public void TC_03_LoginWithPasswordLessThan6Chars(){
        driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
        driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id = 'pass']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@id = 'send2']")).click();
        Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", driver.findElement(By.xpath("//div[@id = 'advice-validate-password-pass']")).getText());
    }

    @Test
    public void TC_04_LoginWithInvalidPass(){
        driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
        driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id ='pass']")).sendKeys("123123123");
        driver.findElement(By.xpath("//button[@id = 'send2']")).click();
        System.out.println(driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]")).getText());

    }

    @Test
    public void TC_04_LoginWithValidAccountInfo(){
        driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
        driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id ='pass']")).sendKeys("123123");
        driver.findElement(By.xpath("//button[@id = 'send2']")).click();
        driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[contains(text(),'Account')]")).click();
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//div[@class ='skip-content skip-active]/ul/li[last()]")).click();


    }
//
//    @AfterTest
//    public void CloseCase(){
//        driver.close();
//    }
}