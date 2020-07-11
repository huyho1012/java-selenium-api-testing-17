import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

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
        Assert.assertTrue(driver.findElement(By.className("username")).isDisplayed());
    }
    @Test
    public void TC02_RamdonPopUp(){
        driver.get("https://blog.testproject.io/");
        this.setDelay(6);
        if(driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()){
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class ='link'][contains(text(),' Sign Up Now ')]")).isDisplayed());
            driver.findElement(By.xpath("//div[@class='close-mailch']")).click();
        }
        driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("Selenium");
        driver.findElement(By.cssSelector("#search-2 .glass")).click();

        List<WebElement> postList = driver.findElements(By.cssSelector(".post-title"));
        for(WebElement postTitle : postList){
           String postTitleText = postTitle.getText().trim();
           Assert.assertTrue(postTitleText.contains("Selenium"));
        }
    }
    @Test
    public void TC_03_Iframe(){
        driver.get("https://kyna.vn/");
        this.setDelay(8);
        if(driver.findElement(By.xpath("//div[@class='fancybox-inner']")).isDisplayed()){
            driver.findElement(By.xpath("//div[@class='fancybox-inner']/following::a")).click();
        }
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class ='fanpage']//iframe")));
        String totalFBLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
        Assert.assertEquals(totalFBLike,"169K likes");
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'chat-area')]")).isDisplayed());
        driver.findElement(By.xpath("//div[@class= 'textarea_wrapper']/textarea")).sendKeys("Hello");
        driver.findElement(By.xpath("//div[@class= 'textarea_wrapper']/textarea")).sendKeys(Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@name= 'profile_menu']")).isDisplayed());
        driver.switchTo().defaultContent();
        driver.findElement(By.id("live-search-bar")).sendKeys("Java");
        driver.findElement(By.xpath("//span[@class ='input-group-btn']//i")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class ='menu-heading']//h1[contains(text(),'Java')]")).isDisplayed());
    }
}
