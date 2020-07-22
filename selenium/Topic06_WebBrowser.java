import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic06_WebBrowser {
    WebDriver driver;

    @BeforeMethod
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver",".\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TC_01_Verify_URL() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,"http://live.demoguru99.com/index.php/customer/account/login/");
    }
    @Test
    public void TC_02_Verify_Title() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        String titlePage = driver.getTitle();
        Assert.assertEquals(titlePage,"Customer Login");
    }
    @Test
    public void TC_03_Navigate_Page() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,"http://live.demoguru99.com/index.php/customer/account/create/");
        driver.navigate().back();
        currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,"http://live.demoguru99.com/index.php/customer/account/login/");
        driver.navigate().forward();
        String titlePage = driver.getTitle();
        Assert.assertEquals(titlePage,"Create New Customer Account");
    }
    @Test
    public void TC_04_Get_Page_Source() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Login or Create an Account"));
        driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
        pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Create an Account"));
    }
    @Test
    public void TC_05_Get_Page_Source() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if(isElementDisplay("//input[@id ='mail']")){
            driver.findElement(By.xpath("//input[@id ='mail']")).sendKeys("Automation");
        }
        if(isElementDisplay("//label[text()='Under 18']")){
            driver.findElement(By.xpath("//label[text()='Under 18']")).click();
        }
        if(isElementDisplay("//textarea[@id='edu']")){
            driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation");
        }
    }
    @Test
    public void TC_06_Check_Element_Is_Display() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if(isElementDisplay("//input[@id ='mail']")){
            driver.findElement(By.xpath("//input[@id ='mail']")).sendKeys("Automation");
        }
        if(isElementDisplay("//label[text()='Under 18']")){
            driver.findElement(By.xpath("//label[text()='Under 18']")).click();
        }
        if(isElementDisplay("//textarea[@id='edu']")){
            driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation");
        }
    }
    @Test
    public void TC_06_Check_Element_Is_Enable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        isElementEnable("//input[@id ='mail']");
        isElementEnable("//label[text()='Under 18']");
        isElementEnable("//textarea[@id='edu']");
        isElementEnable("//select[@id ='job1']");
        isElementEnable("//select[@id ='job2']");
        isElementEnable("//input[@id ='development']");
        isElementEnable("//input[@id ='slider-1']");

        isElementEnable("//input[@id ='password']");
        isElementEnable("//input[@id ='radio-disabled']");
        isElementEnable("//textarea[@id ='bio']");
        isElementEnable("//select[@id ='job3']");
        isElementEnable("//input[@id ='check-disbaled']");
        isElementEnable("//input[@id ='slider-2']");

    }
    @Test
    public void TC_07_Check_Element_Is_Selected(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//input[@id= 'under_18']")).click();
        setTimeDelay(2);
        driver.findElement(By.xpath("//input[@id ='development']")).click();
        setTimeDelay(2);
        Assert.assertTrue(isElementIsSelected("//input[@id= 'under_18']"));
        Assert.assertTrue(isElementIsSelected("//input[@id ='development']"));
        setTimeDelay(2);
        driver.findElement(By.xpath("//input[@id ='development']")).click();
        Assert.assertFalse(isElementIsSelected("//input[@id ='development']"));
    }
    @Test
    public void TC_08_Register_Function_At_MailChimp(){
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.xpath("//input[@id= 'email']")).sendKeys("huyho1210@gmail.com");
        driver.findElement(By.xpath("//input[@id ='new_username']")).sendKeys("Huy hồ");
        WebElement passWordTextBox = driver.findElement(By.xpath("//input[@id ='new_password']"));
        // Truong hop nhâp so
        passWordTextBox.sendKeys("123456");
        Assert.assertTrue(isElementEnable("//li[@class ='number-char completed']"));
        setTimeDelay(2);
        // Truong hop nhâp chu thương
        passWordTextBox.clear();
        passWordTextBox.sendKeys("abcdef");
        Assert.assertTrue(isElementEnable("//li[@class ='lowercase-char completed']"));
        setTimeDelay(2);
        // Truong hop nhâp chu hoa
        passWordTextBox.clear();
        passWordTextBox.sendKeys("ABCDED");
        Assert.assertTrue(isElementEnable("//li[@class = 'uppercase-char completed']"));
        setTimeDelay(2);
        // Truong hop nhâp ký tự đặt biệt
        passWordTextBox.clear();
        passWordTextBox.sendKeys("@@@@");
        Assert.assertTrue(isElementEnable("//li[@class = 'special-char completed']"));
        setTimeDelay(2);
        // Truong hop nhâp 8 ky tu
        passWordTextBox.clear();
        passWordTextBox.sendKeys("dsadsadas");
        Assert.assertTrue(isElementEnable("//li[@class = '8-char completed']"));
        Assert.assertFalse(isElementEnable("//button[@id = 'create-account']"));

        setTimeDelay(2);
        driver.findElement(By.xpath("//input[@id='marketing_newsletter']")).click();
        Assert.assertTrue(isElementIsSelected("//input[@id='marketing_newsletter']"));
        setTimeDelay(2);

        driver.findElement(By.xpath("//input[@id ='new_username']")).sendKeys("Huy hồ");
    }
    public boolean isElementIsSelected(String locator) {
        if (driver.findElement(By.xpath(locator)).isSelected()) {
            return true;
        }
        return false;
    }
    public boolean isElementDisplay(String locator) {
        if (driver.findElement(By.xpath(locator)).isDisplayed()) {
            return true;
        }
        return false;
    }
    public boolean isElementEnable(String locator) {
        if (driver.findElement(By.xpath(locator)).isEnabled()) {
            System.out.println("Element" + locator+ "is enable");
            return true;
        } else{
            System.out.println("Element" + locator+ "is disable");
            return false;
        }
    }
    public void setTimeDelay(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @AfterTest
    public void closePage(){
        driver.close();
    }

}