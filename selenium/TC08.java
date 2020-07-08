import net.bytebuddy.build.ToStringPlugin;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TC08 {
    WebDriver driver;
    WebDriverWait implicitlyWait;
    JavascriptExecutor jsExcutor;

    @BeforeTest
    public void Precondition(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        implicitlyWait = new WebDriverWait(driver,30);
        jsExcutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Tescase_01(){
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        SelectItemOnCustomDropdown("//span[@id ='number-button']","//li[@class = 'ui-menu-item']/div","5");
        SelectItemOnCustomDropdown("//span[@id ='number-button']","//li[@class = 'ui-menu-item']/div","10");
        SelectItemOnCustomDropdown("//span[@id ='number-button']","//li[@class = 'ui-menu-item']/div","2");
    }

    @Test
    public void Tescase_02_Angular(){
        driver.get("https://bit.ly/2UV2vYi");
        SelectItemOnCustomDropdown("//ejs-dropdownlist[@id = 'games']//span[contains(@class,'e-search-icon')]","//ul[@id = 'games_options']//li","Badminton");
        Assert.assertEquals(GetHiddText("select[id ='games_hidden'] option"),"Badminton");;
    }
    public void SelectItemOnCustomDropdown(String xpathDropdown , String xpathItem, String expectedItem){
        driver.findElement(By.xpath(xpathDropdown)).click();
        implicitlyWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathItem)));
        List<WebElement> itemList = driver.findElements(By.xpath(xpathItem));
        for(WebElement item : itemList){
            if(item.getText().equals(expectedItem)){
                jsExcutor.executeScript("arguments[0].scrollIntoView()",item);
                implicitlyWait.until(ExpectedConditions.elementToBeClickable(item));
                item.click();
                SetDelaytime(1);
                break;
            }
        }
    }

    public void SetDelaytime(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String GetHiddText(String locator){
        return (String) jsExcutor.executeScript("return document.querySelector(\""+ locator+"\").textContent");
    }
    @Test
    public void Testcase03_React(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        SelectItemOnCustomDropdown(" //i[@class ='dropdown icon']","//span[@class='text']","Jenny Hess");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='text'][contains(text(),'Jenny Hess')]")).isDisplayed());
    }
    @Test
    public void Testcase04_EditableDropdown(){
        driver.get("http://indrimuska.github.io/jquery-editable-select/");
        SendKeyToEditableDropdown("//div[@id ='default-place']//input","BMW");
        Assert.assertEquals(GetHiddText("#default-place li.es-visible"),"BMW");
    }
    @Test
    public void Testcase04_2_Testcase04_EditableDropdown(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        SendKeyToEditableDropdown("//input[@class= 'search']","Afghanistan");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@class ='search']/following-sibling::div[@class= 'text']")).getText(),"Afghanistan");
    }
    public void SendKeyToEditableDropdown(String xpathLocator, String value){
        driver.findElement(By.xpath(xpathLocator)).clear();
        driver.findElement(By.xpath(xpathLocator)).sendKeys(value);
        SetDelaytime(1);
        driver.findElement(By.xpath(xpathLocator)).sendKeys(Keys.ENTER);
        SetDelaytime(1);
    }
}
