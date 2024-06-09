package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     @Test
     public void testCase01() throws InterruptedException
     {
        System.out.println("TestCase-01 Started");
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("viewform"));
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Crio Learner");
        long currentTime = System.currentTimeMillis();
        driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']")).sendKeys("I want to be the best QA Engineer! "+currentTime);
        driver.findElement(By.xpath("//div[@aria-label='3 - 5']")).click();
        driver.findElement(By.xpath("//div[@aria-label='Java']")).click();
        driver.findElement(By.xpath("//div[@aria-label='Selenium']")).click();
        driver.findElement(By.xpath("//div[@aria-label='TestNG']")).click();
        driver.findElement(By.xpath("//div[@jsname='LgbsSe']//div/div[1]/span")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-value='Mr' and @role='option']")));
        WebElement element = driver.findElement(By.xpath("//div[@data-value='Mr' and @role='option']"));
        Actions act=new Actions(driver);
        act.click(element).perform();
        act.build();
        Thread.sleep(3000);
        //Time
        Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-7);
        Date myDate = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate= formatter.format(myDate);  
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(strDate);
        driver.findElement(By.xpath("//input[@aria-label='Hour']")).sendKeys("07");
        driver.findElement(By.xpath("//input[@aria-label='Minute']")).sendKeys("30");
        driver.findElement(By.xpath("//span[text()='Submit']")).click();
        wait.until(ExpectedConditions.urlContains("/formResponse"));
        String s1=driver.findElement(By.xpath("//div[@role='heading']")).getText();
        System.out.println(s1);
        System.out.println("TestCase-01 Ended");
     }
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

     @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}