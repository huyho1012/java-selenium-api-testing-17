import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic11_12_Popup_Iframe_windows  extends  Common{

    @Test
    public void TC01_Fixed_Popup(){
        driver.get("https://www.zingpoll.com/");
        this.setDelay(4);
        driver.findElement(By.id("Loginform")).click();
        this.setDelay(4);
        if(driver.findElement(By.id("Login")).isDisplayed()){
            driver.findElement(By.xpath("//div[@id='Login']//button[@class = 'close']")).click();
        }
        this.setDelay(4);
        driver.findElement(By.id("Loginform")).click();
        this.setDelay(4);
        driver.findElement(By.id("loginEmail")).sendKeys("huyho1210@gmail.com");
        driver.findElement(By.id("loginPassword")).sendKeys("123456");
        driver.findElement(By.id("button-login")).click();
        this.setDelay(4);
        driver.navigate().refresh();
        Assert.assertTrue(driver.findElement(By.className("username")).isDisplayed());
    }
    @Test
    public void TC02(){
        driver.get("https://blog.testproject.io/");
        this.setDelay(6);
        if(driver.findElement(By.xpath("//div[@id='mailch-bg']")).isDisplayed()){
            driver.findElement(By.xpath("//div[@class='close-mailch']")).click();
        }
    }
}
