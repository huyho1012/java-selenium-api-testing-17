import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Topic09_Alert extends Common{
    String chromexAutoIT = "libraries\\ÁutoIT\\authen_chrome.exe";
    String firefoxAutoIT = "libraries\\ÁutoIT\\authen_firefox.exe";
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
        this.setDelay(5);
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
    @Test
    public void TC01(){
        driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
        driver.findElement(By.cssSelector("li.popup-login-tab-item.popup-login-tab-login")).click();
        WebElement buttonLogin = driver.findElement(By.className("fhs-btn-login"));
        Assert.assertFalse(buttonLogin.isEnabled());

        driver.findElement(By.id("login_username")).sendKeys("hahalolo@gmail.com");
        driver.findElement(By.id("login_password")).sendKeys("123456");
        Assert.assertTrue(buttonLogin.isEnabled());
        this.setDelay(2);
        driver.findElement(By.id("login_username")).clear();
        driver.findElement(By.id("login_password")).clear();
        removeAttribute(buttonLogin);
        buttonLogin.click();
        this.setDelay(2);
        String errLogin = driver.findElement(By.xpath("//input[@id ='login_username']/parent::div//following-sibling::div")).getText();
        Assert.assertEquals(errLogin,"Thông tin này không thể để trống");
        String errLogin1 = driver.findElement(By.xpath("//input[@id ='login_password']/parent::div//following-sibling::div")).getText();
        Assert.assertEquals(errLogin1,"Thông tin này không thể để trống");
    }

    @Test
    public void TC_02_DefaultCheckbox_RadioButton(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        driver.findElement(By.xpath("//input[@class='k-checkbox']/following-sibling::label[text() ='Dual-zone air conditioning']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//label[text() ='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
        driver.findElement(By.xpath("//input[@class='k-checkbox']/following-sibling::label[text() ='Dual-zone air conditioning']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//label[text() ='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
        this.setDelay(2);
        driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
        driver.findElement(By.xpath("//input/following-sibling::label[contains(text(),'2.0 Petrol, 147kW')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).isSelected());
    }
    @Test
    public void TC_03_CustomCheckbox_RadioButton() {
        driver.get("https://material.angular.io/components/radio/examples");
        driver.findElement(By.xpath("//div[contains(text(),'Autumn')]/preceding-sibling::div")).click();
        Assert.assertTrue( driver.findElement(By.xpath("//div[contains(text(),'Autumn')]/preceding-sibling::div")).isDisplayed());
        driver.get("https://material.angular.io/components/checkbox/examples");

        By CheckBox1 = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div/input");
        ClickByJS(driver.findElement(CheckBox1));
        Assert.assertTrue(driver.findElement(CheckBox1).isSelected());
        By CheckBox2 = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::div/input");
        ClickByJS(driver.findElement(CheckBox2));
        Assert.assertTrue(driver.findElement(CheckBox2).isSelected());
        ClickByJS(driver.findElement(CheckBox1));
        Assert.assertFalse(driver.findElement(CheckBox1).isSelected());
        ClickByJS(driver.findElement(CheckBox2));
        Assert.assertFalse(driver.findElement(CheckBox2).isSelected());
    }
    public void ClickByJS(WebElement element){
        jsExcector.executeScript("arguments[0].click();",element);
    }
    public void removeAttribute(WebElement element){
        this.jsExcector.executeScript("arguments[0].removeAttribute('disable')", element);
    }
    @Test
    public void TC_04_Authentication(){
        String username = "admin";
        String password = "admin";
        driver.get("http://"+ username+ ":"+ password+ "@" + "the-internet.herokuapp.com/basic_auth");

    }
    @Test
    public void TC_5_Authentication(){
        String username = "admin";
        String password = "admin";
        driver.get("http://"+ username+ ":"+ password+ "@" + "the-internet.herokuapp.com/basic_auth");

    }
    @Test
    public void TC_5_AuthenticationAutoIT() throws IOException {
        String username = "admin";
        String password = "admin";
        String authenUrl = "http://the-internet.herokuapp.com/basic_auth";
        if(driver.toString().contains("firefox")) {
            Runtime.getRuntime().exec(new String[]{firefoxAutoIT, username, password});
        }
        else if(driver.toString().contains("chrome")){
            Runtime.getRuntime().exec(new String[]{chromexAutoIT,username,password});
        }
        driver.get(authenUrl);
        this.setDelay(4);
    }

}
