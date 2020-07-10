import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Common {
    WebDriver driver;
    WebDriverWait expcilitWait;
    JavascriptExecutor jsExcector;
    @BeforeTest
    public void Precondition() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        expcilitWait = new WebDriverWait(driver,30);
        jsExcector = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    public int Random() {
        Random r = new Random();
        return r.nextInt(999999);
    }
    public void setDelay(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
}