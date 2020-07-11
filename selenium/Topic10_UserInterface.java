import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Topic10_UserInterface extends  Common{
    String rootFolder = System.getProperty("user.dir");
    String javascriptPath = rootFolder + "\\DrapAndDrop\\drag_and_drop_helper.js";
    String jquertPath = rootFolder + "\\DrapAndDrop\\jquery_load_helper.js";
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
        ArrayList<String> selectedItemText = new ArrayList<String>();
        for(WebElement element : itemSelected){
            selectedItemText.add(element.getText());
        }
        Assert.assertEquals(itemSelected.size(),8);
        Object[] actual = (Object[]) selectedItemText.toArray();
        Assert.assertEquals(actual,expected);
    } @Test
    public void TC_03_ClickAndHoldRamdon(){
        driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
        List<WebElement> listItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        action.keyDown(Keys.CONTROL).perform();
        action.click(listItem.get(0)).click(listItem.get(7)).click(listItem.get(8)).perform();
        action.keyUp(Keys.CONTROL).perform();
        this.setDelay(4);
        List<WebElement> itemSelected = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(itemSelected.size(),3);
    }
    @Test
    public void TC_04_DoubleClick(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement button = driver.findElement(By.xpath("//div[@class ='container']//button[text()='Double click me']"));
        action.doubleClick(button).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("#demo")).getText(), "Hello Automation Guys!");
    }
    @Test
    public void TC_05_RightClickElement(){
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'right click me')]"));
        action.contextClick(element).perform();
        WebElement menuQuit = driver.findElement(By.xpath("//li//span[contains(text(),'Quit')]"));
        action.moveToElement(menuQuit).click().perform();
    }
    @Test
    public void TC_06_DrapDownHTML4(){
        driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
        WebElement circle = driver.findElement(By.xpath("//div[@id ='draggable']"));
        WebElement targer = driver.findElement(By.xpath("//div[@id ='droptarget']"));
        action.dragAndDrop(circle,targer).perform();
    }
    @Test
    public void TC_07_Drap_Drop_HTMl5() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        String source = "#column-a";
        String target = "#column-b";
        String javascript = this.Readfile(javascriptPath);
        javascript = javascript +"$(\""+source + "\").simulateDragDrop({dropTarget: \"" + target +"\"});";
        jsExcector.executeScript(javascript);
        this.setDelay(3);
    }
}
