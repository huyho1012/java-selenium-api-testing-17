import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Topic15_WebDriver_Wait extends Common{
    FluentWait fluentWait;
    WebElement element;
    String file1 = UserPath + "\\UploadFile\\image1.jpg";
    String file2 = UserPath + "\\UploadFile\\image2.jpg";

    String uploadMoreFIleWithChrome = UserPath + "\\AutoIT\\chromeUploadMultiple.exe";
    String uploadMoreFIleWithFirefox = UserPath + "\\AutoIT\\firefoxUploadMultiple.exe";
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
    @Test
    public void TC_02_StaticWait() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'finish']//h4[text() ='Hello World!']")).isDisplayed());
    }
    @Test
    public void TC_03_ImplicitWait(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")).isDisplayed());
    }
    @Test
    public void TC_04_Explicit(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        expcilitWait =  new WebDriverWait(driver,3);
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        expcilitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id ='loading']")));
    }
    @Test
    public void TC_04_Explicit_02(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        expcilitWait =  new WebDriverWait(driver,5);
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        expcilitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id ='loading']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")).isDisplayed());
    }
    @Test
    public void TC_05_Explicit_01(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        expcilitWait =  new WebDriverWait(driver,3);
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        expcilitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")).isDisplayed());
    }
    @Test
    public void TC_05_Explicit_02(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        expcilitWait =  new WebDriverWait(driver,5);
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        expcilitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")).isDisplayed());
    }
    @Test
    public void TC_06_Explicit(){
        driver.get("https://bit.ly/explicit-wait");
        expcilitWait =  new WebDriverWait(driver,15);
        expcilitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='rcTitlebar']")));
        String text = driver.findElement(By.xpath("//span[@class = 'label'][text()= 'No Selected Dates to display.']")).getText();
        System.out.println(text);
        driver.findElement(By.xpath("//tr[@class ='rcRow']//td//a[text() = '5']")).click();
        expcilitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='RadAjax RadAjax_Silk']//div[@class='raDiv']")));
        expcilitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='5']/parent::td[@class ='rcSelected']")));
        String text2 = driver.findElement(By.xpath("//span[@class = 'label']")).getText();
        System.out.println(text2);
        Assert.assertEquals(text2, "Sunday, July 5, 2020");
    }
    @Test
    public void TC_07_ExplicitWait() throws IOException {
        driver.get("https://gofile.io/?t=uploadFiles");
        driver.findElement(By.id("btnChooseFiles")).click();
        if (driver.toString().contains("firefox")) {
            Runtime.getRuntime().exec(new String[]{uploadMoreFIleWithFirefox, file1, file2});
        } else if (driver.toString().contains("chrome")) {
            Runtime.getRuntime().exec(new String[]{uploadMoreFIleWithChrome, file1, file2});
        }
        List<WebElement> listItemUpload = driver.findElements(By.xpath("//td[@class='sorting_1']"));
        for(WebElement item: listItemUpload){
            Assert.assertTrue(driver.findElement(By.xpath("//button[contains(@class,'remove')]")).isDisplayed());
        }
        driver.findElement(By.id("btnUpload")).click();
    }
    @Test
    public void TC_08_FluentWait(){
        driver.get("https://automationfc.github.io/fluent-wait/");
        expcilitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("javascript_countdown_time")));
        FluentWaitedElementAndGetText(By.xpath("//div[@id ='javascript_countdown_time']"), ".getText().endsWith('02')");
    }
    @Test
    public void TC_09_FluentWait() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
    }
    public WebElement FluentWaitedElement(By locator){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
               return driver.findElement(locator);
            }
        });
        return element;
    }
    public String FluentWaitedElementAndGetText(By locator, String condition){
        element = FluentWaitedElement(locator);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        String text = wait.until(new Function<WebDriver, String>() {
            public String apply(WebDriver driver) {
                boolean status = Boolean.valueOf(element + condition);
                if(status ==true) return element.getText();
                return null;
            }
        });
        return text;
    }
    public void FluentWaitAndClick(By Locator){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(Locator);
            }
        });
        element.click();
    }
    public Boolean FluentWaitAndCheckDisplay(By Locator) {
        element = FluentWaitedElement(Locator);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
        boolean isDisplay = wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
            boolean status = element.isDisplayed();
            return  status;
            }
        });
        return  isDisplay;
    }
}
