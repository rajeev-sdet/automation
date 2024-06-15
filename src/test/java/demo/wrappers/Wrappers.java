package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    WebDriver driver;   
    WebDriverWait wait;
    Actions act;
    JavascriptExecutor js;


    public Wrappers(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        act=new Actions(driver);
        js=((JavascriptExecutor)driver);
    }

    public void enter_text(WebElement ele,String str)
    {
        ele.clear();
        ele.sendKeys(str);
    }

    public void click(WebElement ele)
    {
        ele.click();
    }

     public void mouseClick(WebElement elementToClick,WebDriver driver) throws InterruptedException {
        act.click(elementToClick).perform();
        act.build();
        Thread.sleep(3000);
    }   

    public String pastDate(int i)
    {
        Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-i);
        Date myDate = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate= formatter.format(myDate); 
        return strDate;
    }

    public long curTimeInEpoch()
    {
        long currentTime = System.currentTimeMillis();
        return currentTime;
    }

    public String curHr()
    {
        int hr=LocalDateTime.now().getHour();
		return Integer.toString(hr);        
    }

    public String curMin()
    {
        int min=LocalDateTime.now().getMinute();
		return Integer.toString(min);         
    }

    public void expUrlWait(String s)
    {
        wait.until(ExpectedConditions.urlContains(s));
    }

    public void expEleWait(String s )
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(s)));
    }

    public void scroll()
    {
        js.executeScript("window.scrollBy(0,400);");
    }

}
