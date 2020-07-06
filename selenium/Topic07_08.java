import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic07_08 {
    WebDriver driver;
    @BeforeTest
    public void Connect(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void Tcs_01_TextArea (){
        driver.get("http://demo.guru99.com/v4/");

        WebElement username = driver.findElement(By.name("uid"));
        username.clear();
        username.sendKeys("mngr233468");
        WebElement pass = driver.findElement(By.name("password"));
        pass.clear();
        pass.sendKeys("tYqAhaq");
        driver.findElement(By.name("btnLogin")).click();
    }

}
