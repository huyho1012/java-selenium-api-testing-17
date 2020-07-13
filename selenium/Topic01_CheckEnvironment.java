import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic01_CheckEnvironment extends Common {

        @Test
        public void TC_01_ValidateCurrentUrl() {
            String loginPageUrl = driver.getCurrentUrl();
            Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
        }
        @Test
        public void TC_02_ValidatePageTitle() {
            String loginPageTitle = driver.getTitle();
            Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
        }
        @Test
        public void TC_03_LoginFormDisplayed() {
            Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
        }

}
