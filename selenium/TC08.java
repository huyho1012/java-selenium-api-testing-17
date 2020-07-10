import net.bytebuddy.build.ToStringPlugin;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TC08 {
    WebDriver driver;
    WebDriverWait impicilityWait;

    @BeforeTest
    public void preconditionStep(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        impicilityWait = new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterTest
    public void EndStep(){
        driver.close();
    }
    @Test
    public void Tcs01(){
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

    }
    //span[@id='number-button']
    public void selectItemDropdown(String dropdownSelector,String itemSelector){
        driver.findElement(By.xpath(dropdownSelector)).click();
    }
}
