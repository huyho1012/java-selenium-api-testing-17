import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic16 extends Common{

    @Test
    public void TC01(){
        driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login active']")).click();
        WebElement buttonLogin = driver.findElement(By.className("fhs-btn-login"));
        Assert.assertFalse(buttonLogin.isEnabled());

        driver.findElement(By.id("login_username")).sendKeys("hahalolo@gmail.com");
        driver.findElement(By.id("login_password")).sendKeys("123456");
        Assert.assertTrue(buttonLogin.isEnabled());
        driver.r

    }
}
