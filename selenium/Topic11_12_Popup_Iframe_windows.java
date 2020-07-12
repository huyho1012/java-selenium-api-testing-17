import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class Topic11_12_Popup_Iframe_windows  extends  Common{
    Alert alert;
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
    @Test
    public void TC_04_Window(){
        String windowParent = driver.getWindowHandle();
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//div[@class='container']/a[text() ='GOOGLE']")).click();
        SwitchWindowByTitle("Google");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");
        SwitchWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
        Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//div[@class='container']/a[text() ='FACEBOOK']")).click();
        this.setDelay(4);
        SwitchWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");
        SwitchWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
        driver.findElement(By.xpath("//div[@class='container']/a[text() ='TIKI']")).click();
        SwitchWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
        closeAlltabWithouParent(windowParent);
    }
    @Test
    public void TC_05_WinDownPopUP() {
        String windowParent = driver.getWindowHandle();
        driver.get("https://kyna.vn/");
        this.setDelay(8);
        clickElementByScript("//div[@class='social']//a[position()=1]");
        SwitchWindowByTitle("Kyna.vn - Trang chủ");
        this.setDelay(2);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
        SwitchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
        clickElementByScript("//div[@class='social']//a[position()=2]");
        SwitchWindowByTitle("Kyna.vn - YouTube");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/user/kynavn");
        this.setDelay(2);
        SwitchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
        clickElementByScript("//div[@class='social']//a[position()=3]");
        SwitchWindowByTitle("Zalo - Nhắn Gửi Yêu Thương (Nhắn tin thoại - Trò chuyện nhóm ...)");
        Assert.assertEquals(driver.getCurrentUrl(),"https://zalo.me/1985686830006307471");
        this.setDelay(2);
        SwitchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
        clickElementByScript("//div[@class='icon-app']//a[@title ='Android']");
        SwitchWindowByTitle("Kyna 2 - Ứng dụng trên Google Play");
        Assert.assertEquals(driver.getCurrentUrl(),"https://play.google.com/store/apps/details?id=com.navikyna");
        this.setDelay(2);
        SwitchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class ='fanpage']//iframe")));
        clickElementByScript("//div[@class='lfloat']//a[text()='Kyna.vn']");
        SwitchWindowByTitle("Kyna.vn - Trang chủ | Facebook");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
        SwitchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
        closeAlltabWithouParent(windowParent);
    }
    @Test
    public void TC_06_WindowTab(){
        String windowParent = driver.getWindowHandle();
        driver.get("http://live.demoguru99.com/index.php/");
        clickElementByScript("//a[contains(text(),'Mobile')]");
        clickElementByScript("//a[contains(text(),'Sony Xperia')]/parent::h2/following-sibling::div[@class='actions']//a[contains(text(),'Add to Compare')]");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[contains(text(),'The product Sony Xperia has been added to comparison list.')]")).isDisplayed());
        clickElementByScript("//a[contains(text(),'Samsung Galaxy')]/parent::h2/following-sibling::div[@class='actions']//a[contains(text(),'Add to Compare')]");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[contains(text(),'The product Samsung Galaxy has been added to comparison list.')]")).isDisplayed());
        clickElementByScript("//button[@class='button']//span[text()='Compare']");
        SwitchWindowByTitle("Products Comparison List - Magento Commerce");
        closeAlltabWithouParent(windowParent);
        clickElementByScript("//a[contains(text(),'Clear All')]");
        alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[contains(text(),'The comparison list was cleared.')]")).isDisplayed());
    }

    // Hàm switch wimdow by ID(chi áp dụng 2 cho winddoww)
    public void SwitchWindowByID(String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow : allWindows){
            if(!runWindow.contains(parentID)){
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }
    public void SwitchWindowByTitle(String title){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow : allWindows){
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if(currentTitle.contains(title)){
                break;
            }
        }
    }
    // Hàm switch wimdow by ID(chi áp dụng 2 cho winddoww)
    public boolean closeAlltabWithouParent(String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow : allWindows){
            if(!runWindow.contains(parentID)){
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size()==1)
            return true;
        return false;
    }
    // Click bằng javascript
    public void clickElementByScript(String elementLocator){
        WebElement element = driver.findElement(By.xpath(elementLocator));
        jsExcector.executeScript("arguments[0].click();",element);
    }
}
