import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Common {
    WebDriver driver;
    WebDriverWait expcilitWait;
    JavascriptExecutor jsExcector;
    Actions action;
    String UserPath = System.getProperty("user.dir");
    @BeforeTest
    public void Precondition() {
        System.setProperty("webdriver.chrome.driver", UserPath + "/browserDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        expcilitWait = new WebDriverWait(driver,30);
        jsExcector = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    public int Random() {
        Random r = new Random();
        return r.nextInt(999999);
    }
    public void setDelay(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String Readfile(String File) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(File);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream,cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while((read= reader.read(buffer,0,buffer.length))>0){
                builder.append(buffer,0,read);
            }
            return  builder.toString();
        }finally {
            stream.close();
        }

    }
//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
}
