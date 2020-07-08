import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Topic08_CustomDropdown {
    WebDriver driver;
    WebDriverWait expliciWait;
    @BeforeTest
    public void Getpage(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        expliciWait = new WebDriverWait(driver,30);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TC01(){
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemOnDropdown("//span[@id='number-button']","//li[@class='ui-menu-item']/div","5");
        selectItemOnDropdown("//span[@id='number-button']","//li[@class='ui-menu-item']/div","10");
        selectItemOnDropdown("//span[@id='number-button']","//li[@class='ui-menu-item']/div","3");
        selectItemOnDropdown("//span[@id='number-button']","//li[@class='ui-menu-item']/div","3");
    }
    public void selectItemOnDropdown(String urlXpath, String itemDropdown, String expectedItem){
        driver.findElement(By.xpath(urlXpath)).click();
        SleepTime(3);
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemDropdown)));
        List <WebElement> allItems = driver.findElements(By.xpath(itemDropdown));
        for(WebElement item : allItems){
            if(item.getText().equals(expectedItem)){
                item.click();
                SleepTime(2);
                break;
            }
        }

    }
    public void SleepTime(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
