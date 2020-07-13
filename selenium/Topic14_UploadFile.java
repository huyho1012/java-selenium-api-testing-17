import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Topic14_UploadFile extends Common{
    String file1Name = "huy.jpg";
    String file2Name = "image1.jpg";
    String file3Name = "image2.jpg";
    String file1Path = UserPath + "\\UploadFile\\" + file1Name;
    String file2Path = UserPath + "\\UploadFile\\" + file2Name;
    String file3Path = UserPath + "\\UploadFile\\" + file3Name;

    String uploadOneFileFirefox = UserPath+ "\\AutoIT\\firefoxUploadOneTime.exe";
    String uploadOneFileChrome = UserPath+ "\\AutoIT\\chromeUploadOneTime.exe";
    String uploadMutiFileFirefox = UserPath+ "\\AutoIT\\firefoxUploadMultiple.exe";
    String uploadMutiFileChrome = UserPath+ "\\AutoIT\\chromeUploadMultiple.exe";
    @Test
    public void TC_01_UploadOneFileOneTime(){
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@name ='files[]']")).sendKeys(file1Path);
        Assert.assertTrue(checkBooleanDisplay("//p[text()='"+file1Name+"']"));
        driver.findElement(By.xpath("//p[text()='"+file1Name+"']/parent::td/following-sibling::td//span[text()='Start']")).click();
        Assert.assertTrue(checkBooleanDisplay("//a[text()='"+file1Name+"']/ancestor::td/following-sibling::td/input[@name = 'delete']"));
        this.setDelay(1);

        driver.findElement(By.xpath("//input[@name ='files[]']")).sendKeys(file2Path);
        this.setDelay(1);
        Assert.assertTrue(checkBooleanDisplay("//p[text()='"+file2Name+"']"));
        driver.findElement(By.xpath("//p[text()='"+file2Name+"']/parent::td/following-sibling::td//span[text()='Start']")).click();
        Assert.assertTrue(checkBooleanDisplay("//a[text()='"+file2Name+"']/ancestor::td/following-sibling::td/input[@name = 'delete']"));
    }
    @Test
    public void TC_01_UploadMoreFileOneTime(){
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@name ='files[]']")).sendKeys( file1Path+"\n"+file2Path+"\n"+file3Path);
        Assert.assertTrue(checkBooleanDisplay("//p[text()='"+file1Name+"']/parent::td/following-sibling::td//span[text()='Edit']"));
        Assert.assertTrue(checkBooleanDisplay("//p[text()='"+file2Name+"']/parent::td/following-sibling::td//span[text()='Edit']"));
        Assert.assertTrue(checkBooleanDisplay("//p[text()='"+file3Name+"']/parent::td/following-sibling::td//span[text()='Edit']"));

        this.setDelay(1);
    }
    @Test
    public void TC02_UploadOneFileWithAutoIT() throws IOException {
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");
        driver.findElement(By.xpath("//span[contains(@class,'fileinput-button')]")).click();
        this.setDelay(2);
        if(driver.toString().contains("firefox")){
            Runtime.getRuntime().exec(new String[] {uploadOneFileFirefox ,file2Path});
        } else if(driver.toString().contains("chrome")){
            Runtime.getRuntime().exec(new String[] {uploadOneFileChrome ,file2Path});
        }
        Assert.assertTrue(checkBooleanDisplay("//p[text()='"+file2Name+"']"));
    }
    @Test
    public void TC02_UploadMoreFileWithAutoIT() throws IOException {
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");
        driver.findElement(By.xpath("//span[contains(@class,'fileinput-button')]")).click();
        this.setDelay(2);
        if(driver.toString().contains("firefox")){
            Runtime.getRuntime().exec(new String[] {uploadMutiFileFirefox ,file1Path,file2Path,file3Path});
        } else if(driver.toString().contains("chrome")){
            Runtime.getRuntime().exec(new String[] {uploadMutiFileChrome ,file1Path,file2Path,file3Path});
        }
    }
    @Test
    public void TC04_UploadWithRobo() throws IOException, AWTException {
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");
        driver.findElement(By.xpath("//span[contains(@class,'fileinput-button')]")).click();
        StringSelection select = new StringSelection(file1Path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select,null);
        Robot robot = new Robot();
        this.setDelay(2);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    public boolean checkBooleanDisplay(String linkURLFile){
        return driver.findElement(By.xpath(linkURLFile)).isDisplayed();
    }
}
