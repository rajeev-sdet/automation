package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import demo.wrappers.Wrappers;

import java.util.logging.Level;

public class TestCases {
    ChromeDriver driver;
    Wrappers wrapper;


    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     //Navigate to this google form. "https://forms.gle/wjPkzeSEk1CM7KgGA"
     @Test
     public void testCase01()
     {
        System.out.println("TestCase-01 Started");
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        wrapper.expUrlWait("/viewform");
        System.out.println("TestCase-01 Ended");
     }

     //Fill in Crio Learner in the 1st text box
     @Test
     public void testCase02()
     {
        System.out.println("TestCase-02 Started");
        WebElement name_loc=driver.findElement(By.xpath("//input[@type='text']"));
        wrapper.enter_text(name_loc, "Crio Learner");
        System.out.println("TestCase-02 Ended");
     }

    //Write down "I want to be the best QA Engineer! 1710572021'' where 1710572021 is variable - needs to be the current epoch time.
     @Test
     public void testCase03()
     {
        System.out.println("TestCase-03 Started");
        WebElement q2=driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"));
        wrapper.enter_text(q2, "I want to be the best QA Engineer! "+wrapper.curTimeInEpoch());
        System.out.println("TestCase-03 Ended");
     }

     //Enter your Automation Testing experience in the next radio button
     @Test
     public void testCase04()
     {
        System.out.println("TestCase-04 Started");
        WebElement exp_loc=driver.findElement(By.xpath("//div[@aria-label='3 - 5']"));
        wrapper.click(exp_loc);
        System.out.println("TestCase-04 Ended");
     }

      //Select Java, Selenium and TestNG from the next check-box
      @Test
      public void testCase05()
      {
        System.out.println("TestCase-05 Started");
        String[] op={"Java","Selenium","TestNG"};
        for(int i=0;i<=2;i++)
        {
            WebElement ele=driver.findElement(By.xpath("//div[@aria-label='"+op[i]+"']"));
            wrapper.click(ele);
        }
        System.out.println("TestCase-05 Ended");
    }

    //Provide how you would like to be addressed in the next dropdown
    @Test
     public void testCase06() throws InterruptedException
     {
        System.out.println("TestCase-06 Started");
        WebElement open_menu_loc=driver.findElement(By.xpath("//div[@jsname='LgbsSe']//div/div[1]/span"));
        wrapper.click(open_menu_loc);
        wrapper.expEleWait("//div[@data-value='Mrs' and @role='option']");
        WebElement choice = driver.findElement(By.xpath("//div[@data-value='Mr' and @role='option']"));
        wrapper.mouseClick(choice, driver); 
        wrapper.scroll();
        System.out.println("TestCase-06 Ended");
     }

     //Provided the current date minus 7 days in the next date field, it should be dynamically calculated and not hardcoded.
     @Test
     public void testCase07()
     {
        System.out.println("TestCase-07 Started");
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(wrapper.pastDate(7));
        System.out.println("TestCase-07 Ended");
     }

     //Provide the time 07:30 in the next field (Can also be in 24 hour clock)
     @Test
     public void testCase08()
     {
        System.out.println("TestCase-08 Started");
        WebElement hour_loc=driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        wrapper.enter_text(hour_loc,"07");
        WebElement min_loc=driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        wrapper.enter_text(min_loc,"30");
        wrapper.scroll();
        System.out.println("TestCase-08 Ended");
     }

     //Submit the form
     @Test
     public void testCase09()
     {
        System.out.println("TestCase-09 Started");
        WebElement submit_loc=driver.findElement(By.xpath("//span[text()='Submit']"));
        wrapper.click(submit_loc);
        wrapper.expUrlWait("/formResponse");    
        System.out.println("TestCase-09 Ended");
     }

     //You will see a success message on the website. Print the same message on the console upon successful completion
     @Test
     public void testCase10()
     {
        System.out.println("TestCase-10 Started");
        String s1=driver.findElement(By.xpath("//div[@role='heading']")).getText();
        String s2=driver.findElement(By.xpath("//div[@class='vHW8K']")).getText();
        System.out.println("TestCase ended");
        System.out.println(s1+"\n"+s2);
        System.out.println("TestCase-10 Ended");
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
        wrapper=new Wrappers(driver);

        
    }

     @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}