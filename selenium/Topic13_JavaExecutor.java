import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic13_JavaExecutor extends Common {
    String email = "huyho" + Random() +"@mailinator.com";
    @Test
    public void Logout() {
        driver.get("https://test-newsfeed.hahalolo.com/auth/signin");
        driver.findElement(By.id("signin-identity")).sendKeys("balo_04@mailinator.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.xpath("//button[text() ='Đăng nhập']")).click();
        this.setDelay(8);
        WebElement logout = driver.findElement(By.xpath("//span[text()='Logout']"));
        jsExcector.executeScript("arguments[0].click();",logout);
    }

    @Test
    public void TC01_JSExcutor(){
        NagativeOtherPage("http://live.demoguru99.com/");
        String domainPage = GetDomainName();
        Assert.assertEquals(domainPage,"live.demoguru99.com");
        String urlPage = GetURLnName();
        Assert.assertEquals(urlPage,"http://live.demoguru99.com/");
        clickElementByJS("//a[contains(text(),'Mobile')]");
        clickElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']");
        Assert.assertTrue(GetInnerText().contains("Samsung Galaxy was added to your shopping cart."));
        clickElementByJS("//a[text()='Customer Service']");
        Assert.assertEquals(GetTitle(),"Customer Service");
        ScrollToElement("//span[text()='Newsletter']");
        Assert.assertTrue(GetInnerText().contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
        NagativeOtherPage("http://demo.guru99.com/v4/");
        Assert.assertEquals(GetDomainName(),"demo.guru99.com");
    }
    @Test
    public void TC02_RemoveAttribute(){
        driver.get("http://demo.guru99.com/v4");
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.name("emailid")).sendKeys("huyho1210@gmail.com");
        driver.findElement(By.name("btnLogin")).click();
        String userID = driver.findElement(By.xpath("//td[text()='User ID :']/following::td")).getText();
        String passWord = driver.findElement(By.xpath("//td[text()='Password :']/following::td")).getText();

        driver.get("http://demo.guru99.com/v4");
        driver.findElement(By.name("uid")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(passWord);
        driver.findElement(By.name("btnLogin")).click();
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        driver.findElement(By.name("name")).sendKeys("Ho Doan Quoc huy");
        RemoveAttribute("//input[@id ='dob']","type");
        driver.findElement(By.xpath("//input[@id ='dob']")).sendKeys("12-10-1992");
        driver.findElement(By.name("addr")).sendKeys("400A Ung Van Khiem");
        driver.findElement(By.name("city")).sendKeys("Thanh pho HCM");
        driver.findElement(By.name("state")).sendKeys("HCM city");
        driver.findElement(By.name("pinno")).sendKeys("123456");
        driver.findElement(By.name("telephoneno")).sendKeys("0936709449");
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.name("sub")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3'][text()='Customer Registered Successfully!!!']")).isDisplayed());
    }
    @Test
    public void TC04_CreateAnAccount(){
        NagativeOtherPage("http://live.demoguru99.com/");
        clickElementByJS("//div[@id='header-account']//a[text()='My Account']");
        clickElementByJS("//span[text()='Create an Account']");
        sendKeyWithJS("//input[@id = 'firstname']","Huy");
        sendKeyWithJS("//input[@id = 'middlename']","Doãn Quốc");
        sendKeyWithJS("//input[@id = 'lastname']","Hồ");
        sendKeyWithJS("//input[@id = 'email_address']",email);
        sendKeyWithJS("//input[@id = 'password']","123456");
        sendKeyWithJS("//input[@id = 'confirmation']","123456");
        clickElementByJS("//span[text()='Register']");
        Assert.assertTrue(GetInnerText().contains("Thank you for registering with Main Website Store."));
        clickElementByJS("//div[@id='header-account']//a[text()='Log Out']");
        this.setDelay(5);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'account-cart-wrapper']")).isDisplayed());
    }
    public void sendKeyWithJS(String location, String value){
        WebElement element = driver.findElement(By.xpath(location));
        jsExcector.executeScript("arguments[0].value = '"+value+"';",element);
    }
    public void RemoveAttribute(String location, String value){
        WebElement item = driver.findElement(By.xpath(location));
        jsExcector.executeScript("arguments[0].removeAttribute('" + value +"');",item);
    }
    public String GetInnerText(){
        return (String) jsExcector.executeScript("return document.documentElement.innerText");
    }
    public void clickElementByJS(String elementLocator){
        WebElement element = driver.findElement(By.xpath(elementLocator));
        jsExcector.executeScript("arguments[0].click();",element);
    }
    public void NagativeOtherPage(String urlWeb){
        jsExcector.executeScript("window.location='"+ urlWeb+"'");
    }
    public String GetDomainName(){
        return (String) jsExcector.executeScript("return document.domain");
    }
    public String GetURLnName(){
        return (String) jsExcector.executeScript("return document.URL");
    }
    public String GetTitle(){
        return (String) jsExcector.executeScript("return document.title");
    }
    public void ScrollToElement(String xpathElement){
        WebElement item = driver.findElement(By.xpath(xpathElement));
        jsExcector.executeScript("arguments[0].scrollIntoView(true);",item);

    }
}
