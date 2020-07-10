import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic16_Accept_Alert extends Common{
    Alert alert;
    @Test
    public void TC01_AcceptAlert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//div[@class = 'example']/button[contains(text(),'Click for JS Alert')]")).click();
        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id = 'result']")).getText(),"You clicked an alert successfully");
    }
    @Test
    public void TC_02_ConfirmAlert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//div[@class = 'example']/button[contains(text(),'Click for JS Confirm')]")).click();
        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id = 'result']")).getText(),"You clicked: Cancel");
    }
    @Test
    public void TC_03_PromptAlert(){
        String value = "Hahalolo";
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//div[@class = 'example']/button[contains(text(),'Click for JS Prompt')]")).click();
        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        alert.sendKeys(value);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id = 'result']")).getText(),"You entered: "+ value);
    }
}
