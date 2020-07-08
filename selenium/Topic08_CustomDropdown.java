import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Topic08_CustomDropdown {
    WebDriver driver;
    WebDriverWait expliciWait;
    JavascriptExecutor jsExcutor;
    @BeforeTest
    public void Getpage(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        jsExcutor = (JavascriptExecutor) driver;
        expliciWait = new WebDriverWait(driver,30);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    public void TC01(){
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemOnDropdown("//span[@id='number-button']","//li[@class='ui-menu-item']/div","5");
        selectItemOnDropdown("//span[@id='number-button']","//li[@class='ui-menu-item']/div","10");
        selectItemOnDropdown("//span[@id='number-button']","//li[@class='ui-menu-item']/div","3");
        selectItemOnDropdown("//span[@id='number-button']","//li[@class='ui-menu-item']/div","3");
    }
    @Test
    public void TC02() {
        driver.get("https://bit.ly/2UV2vYi");
        selectItemOnDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]","//ul[@id = 'games_options']//li","Badminton");
        Assert.assertEquals(GetHiddText("select[id ='games_hidden'] option"),"Badminton");
    }
        public void SleepTime(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Do là hidden text nên càn trick và phải dung query selector
    public String GetHiddText(String locator){
        return (String) jsExcutor.executeScript("return document.querySelector(\""+ locator+"\").textContent");
    }
    // Hàm select item trong custom dropdown
    public void selectItemOnDropdown(String urlXpath, String itemDropdown, String expectedItem){
        driver.findElement(By.xpath(urlXpath)).click();
        SleepTime(2);
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemDropdown)));
        List <WebElement> allItems = driver.findElements(By.xpath(itemDropdown));
        for(WebElement item : allItems){
            if(item.getText().equals(expectedItem)){
                // Scroll tới dúng vị trí của element
                jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
                SleepTime(1);
                expliciWait.until(ExpectedConditions.elementToBeClickable(item));
                item.click();
                SleepTime(1);
                break;
            }
        }
    }
}
