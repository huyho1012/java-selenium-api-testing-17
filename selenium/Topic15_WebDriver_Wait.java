import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic15_WebDriver_Wait extends Common{

    @Test
    public void TC01_ElementStatus_Visable(){
        driver.get("https://www.facebook.com/");
        // Check lasname visible
        boolean status = driver.findElement(By.name("lastname")).isDisplayed();
        System.out.println(status);
        driver.findElement(By.xpath("//input[@name = 'reg_email__']")).sendKeys("huyho1210@gmail.com");
        // Check confirm_email visible
        status = driver.findElement(By.xpath("//input[@name = 'reg_email_confirmation__']")).isDisplayed();
        System.out.println(status);
        // Xóa email
        driver.findElement(By.xpath("//input[@name = 'reg_email__']")).clear();
        this.setDelay(3);
        // Check confirm_email invisiable
        status = driver.findElement(By.xpath("//input[@name = 'reg_email_confirmation__']")).isDisplayed();
        System.out.println(status);
        expcilitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name = 'reg_email_confirmation__']")));
    }
    @Test
    public void TC01_ElementStatus_InVisable(){
        driver.get("https://www.facebook.com/");
        expcilitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name = 'reg_email_confirmation__']")));
    }
    @Test
    public void TC01_ElementStatus_Presence(){
        driver.get("https://www.facebook.com/");
        //Not on UI
        expcilitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name = 'reg_email_confirmation__']")));
        // Not in DOm
                expcilitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id = 'identify_email']")));
    }
}
