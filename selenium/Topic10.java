import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Topic10 extends  Common{

    @Test
    public void  RC_01(){
    driver.get("http://www.myntra.com/");
    WebElement menuKid = driver.findElement(By.xpath("//div[@class='desktop-navContent']/div/a[contains(text(),'Kids')]"));
    action.moveToElement(menuKid).perform();
    this.setDelay(3);
    WebElement menuHomeBath = driver.findElement(By.xpath("//ul[@class='desktop-navBlock']//a[contains(text(),'Home & Bath')]"));
    action.click(menuHomeBath).perform();
    this.setDelay(2);
    Assert.assertTrue(driver.findElement(By.xpath("//span[@class ='breadcrumbs-crumb'][contains(text(),'Kids Home Bath')]")).isDisplayed());
    }

    @Test
    public void TC_02_ClickAndHold(){
        String[] expected= {"1","2","3","4","5","6","7","8"};
        driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
        List<WebElement> listItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        action.clickAndHold(listItem.get(0)).moveToElement(listItem.get(7)).release().perform();

        List<WebElement> itemSelected = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(itemSelected.size(),8);
        String[] actual = (String[]) itemSelected.toArray();
        Assert.assertEquals(actual,expected);
    }
}
