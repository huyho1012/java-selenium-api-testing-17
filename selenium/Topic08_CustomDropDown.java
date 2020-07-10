import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Topic08_CustomDropDown  extends Common{

    public void chooseItemDropdown(String selectorDropdown , String selectorItem, String expectedItem){
        driver.findElement(By.xpath(selectorDropdown)).click();
        this.setDelay(1);
        expcilitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(selectorItem)));
        List<WebElement> itemList = driver.findElements(By.xpath(selectorItem));
        for(WebElement item : itemList){
            if(item.getText().equals(expectedItem)){
                expcilitWait.until(ExpectedConditions.elementToBeClickable(item));
                item.click();
                this.setDelay(1);
                break;
            }
        }
    }
    @Test
    public void TC01_DropdownJquery(){
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        chooseItemDropdown("//span[@id='number-button']","//li[@class = 'ui-menu-item']/div","1");
        String itemSelected = driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text'][text()= '1']")).getText();
        Assert.assertEquals(itemSelected,"1");
    }
    @Test
    public void TC02_AngularDropdown(){
        driver.get("https://bit.ly/2UV2vYi");
        chooseItemDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]","//ul[@id ='games_options']/li","Basketball");
        Assert.assertEquals(getHiddenText("select[id ='games_hidden'] option"),"Basketball");
    }
    @Test
    public void TC03_React(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        chooseItemDropdown("//i[@class ='dropdown icon']","//div[@class='item']/span","Christian");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='text'][text()= 'Christian']")).isDisplayed());
    }
    @Test
    public void TC04_VueDropDown(){
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        chooseItemDropdown("//li[@class ='dropdown-toggle']","//ul[@class ='dropdown-menu']/li/a[contains(text(), 'Third Option' )]","Third Option");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='dropdown-toggle'][contains(text(), 'Third Option')]")).isDisplayed());
    }
    @Test
    public void TC05_EditableDropdown01(){
        driver.get("http://indrimuska.github.io/jquery-editable-select/");
        SendKeyOnEditableDropdown("//div[@id='default-place']/input","BMW");
        Assert.assertEquals(getHiddenText("#default-place li.es-visible"),"BMW");
    }
    @Test
    public void TC05_EditableDropdown02(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        SendKeyOnEditableDropdown("//input[@class= 'search']","Afghanistan");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='text'][text()='Afghanistan']")).isDisplayed());
    }
    @Test void TC05_EditableDropdown03(){
        driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
        driver.switchTo().frame(2);;

    }
    public String getHiddenText(String cssSelector){
       return (String) jsExcector.executeScript("return document.querySelector(\""+ cssSelector +"\").textContent");
    }
    public void SendKeyOnEditableDropdown(String selectorDropdown, String value ){
        driver.findElement(By.xpath(selectorDropdown)).clear();
        driver.findElement(By.xpath(selectorDropdown)).sendKeys(value);
        driver.findElement(By.xpath(selectorDropdown)).sendKeys(Keys.ENTER);
    }
}
