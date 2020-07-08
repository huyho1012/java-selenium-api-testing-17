import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic07_08 {
    WebDriver driver;
    Topic04_05_Xpath_and_Css topic4 = new Topic04_05_Xpath_and_Css();

    String userName, passWord, loginURL;
    String email = "huyho" + topic4.Random() + "@mailinator.com";

    String name, dob, address, city, state, pin, phone,pass;
    String updateName,updateDob,updateAddress, updateCity, updateState, updatePhone, updatePin, updatePass;

    By fullNameTextBox = By.name("name");
    By birthdayTextBox = By.name("dob");
    By addressTextArea = By.name("addr");
    By cityTextBox = By.name("city");
    By stateTextBox = By.name("state");
    By phoneTextBox = By.name("telephoneno");
    By pinTextBox = By.name("pinno");
    By emailTextBox = By.name("emailid");
    By passTextBox = By.name("password");

    @BeforeTest
    public void Connect() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://demo.guru99.com/v4/");
        loginURL = driver.getCurrentUrl();

        // Create new data
        name = "Adam Smith";
        dob = "12-10-1992";
        address = "CMT8";
        city = "Ho Chi Minh City";
        state = "Tan Binh";
        pin = "123456";
        pass = "123456a";
        phone= "0936709449";

        // Update data
        updateName = "Adam Smith";
        updateDob = "12-10-1992";
        updateAddress = "Dien Bien Phu";
        updateCity = "Ha Noi Capital";
        updateState = "Hoang Kiem";
        updatePin = "101010";
        updatePhone= "0352101783";
    }
    public void GetCode() {
        driver.findElement(By.xpath("//a[text()= 'here']")).click();
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("btnLogin")).click();
        userName = driver.findElement(By.xpath("//td[text() = 'User ID :']/following-sibling::td")).getText();
        passWord = driver.findElement(By.xpath("//td[text() = 'Password :']/following-sibling::td")).getText();
    }
    public void LoginPage() {
        GetCode();
        driver.get(loginURL);
        driver.findElement(By.name("uid")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(passWord);
        driver.findElement(By.name("btnLogin")).click();
        Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),"Welcome To Manager's Page of Guru99 Bank");
    }
    @Test
    public void Tcs_01_TextAre() throws ParseException {
        LoginPage();
        driver.findElement(By.linkText("New Customer")).click();
        // Create new customer
        driver.findElement(fullNameTextBox).sendKeys(name);
        driver.findElement(birthdayTextBox).sendKeys(dob);
        driver.findElement(addressTextArea).sendKeys(address);
        driver.findElement(cityTextBox).sendKeys(city);
        driver.findElement(stateTextBox).sendKeys(state);
        driver.findElement(pinTextBox).sendKeys(pin);
        driver.findElement(phoneTextBox).sendKeys(phone);
        driver.findElement(emailTextBox).sendKeys(email);
        driver.findElement(passTextBox).sendKeys(pass);
        driver.findElement(By.name("sub")).click();
        // Xác minh lại các thông tin hiển thị tại form tạo mới khách hàng với dữ liệu đã nhập trước đó
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText(),name);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText(),ConvertDate(dob));
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText(),address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(),city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText(),state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText(),pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText(),phone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText(),email);
        String customerID = driver.findElement(By.xpath("//td[text() = 'Customer ID']/following-sibling::td")).getText();
        // Điều hướng sang chức năng chỉnh sửa customer
        driver.findElement(By.linkText("Edit Customer")).click();
        driver.findElement(By.name("cusid")).sendKeys(customerID);
        driver.findElement(By.name("AccSubmit")).click();
        Assert.assertEquals(driver.findElement(fullNameTextBox).getAttribute("value"),name);
        Assert.assertEquals(driver.findElement(addressTextArea).getAttribute("value"),address);
        // Cập nhạt thông tin mới
        driver.findElement(addressTextArea).clear();
        driver.findElement(addressTextArea).sendKeys(updateAddress);
        driver.findElement(cityTextBox).clear();
        driver.findElement(cityTextBox).sendKeys(updateCity);
        driver.findElement(stateTextBox).clear();
        driver.findElement(stateTextBox).sendKeys(updateState);
        driver.findElement(pinTextBox).clear();
        driver.findElement(pinTextBox).sendKeys(updatePin);
        driver.findElement(phoneTextBox).clear();
        driver.findElement(phoneTextBox).sendKeys(updatePhone);
        driver.findElement(emailTextBox).clear();
        driver.findElement(emailTextBox).sendKeys(email);
        driver.findElement(By.name("sub")).click();
        // Kiểm tra sau khi submit
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText(),updateAddress);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(),updateCity);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText(),updateState);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText(),updatePin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText(),updatePhone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText(),email);
    }
    public String ConvertDate(String a) throws ParseException {
        SimpleDateFormat fdate = new SimpleDateFormat("dd-mm-yyyy");
        Date date = fdate.parse(dob);
        SimpleDateFormat newfdate = new SimpleDateFormat("yyyy-dd-mm");
        return newfdate.format(date);
    }
    @Test
    public void TC_02(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Select job1 = new Select(driver.findElement(By.id("job1")));
        Assert.assertFalse(job1.isMultiple());
        job1.selectByVisibleText("Mobile Testing");
        Assert.assertTrue(job1.getFirstSelectedOption().isSelected());
        job1.selectByValue("manual");
        Assert.assertTrue(job1.getFirstSelectedOption().isSelected());
        job1.selectByIndex(9);
        Assert.assertTrue(job1.getFirstSelectedOption().isSelected());
        Assert.assertEquals(job1.getOptions().size(),10);
        Select job2 = new Select(driver.findElement(By.id("job2")));
        Assert.assertTrue(job2.isMultiple());
        job2.selectByVisibleText("Automation");
        job2.selectByVisibleText("Mobile");
        job2.selectByVisibleText("Desktop");
        int numOpt = job2.getAllSelectedOptions().size();
        Assert.assertEquals(numOpt,3);

        List<WebElement> options  = job2.getAllSelectedOptions();

        for(WebElement option: options){
            System.out.println(option.getText());
        }
        job2.deselectAll();
        int numOpt2 = job2.getAllSelectedOptions().size();
        Assert.assertEquals(numOpt2,0);
    }
    @Test
    public void TC_03(){
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.xpath("//a[@class= 'ico-register']")).click();
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Huy");
        driver.findElement(By.id("LastName")).sendKeys("Hồ");
        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByValue("1");
        int numDay = day.getOptions().size();
        Assert.assertEquals(numDay,32);
        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByValue("5");
        int numMonth = month.getOptions().size();
        Assert.assertEquals(numMonth,13);
        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByValue("1980");
        int numYear = year.getOptions().size();
        Assert.assertEquals(numYear,112);

        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Company")).sendKeys("Hahalolo Company");
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[@class= 'ico-account']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class= 'ico-logout']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class ='result']")).getText(),"Your registration completed");
    }
}
