import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.Constants;

public class BaseTest {

	public static WebDriver driver;
	public ExtentSparkReporter  reporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	@BeforeTest
	public void beforeTest()
	{
		reporter= new ExtentSparkReporter (System.getProperty("user.dir")+File.separator+"reports"+File.separator+"AutomationReport.html");
		reporter.config().setEncoding("utf-8");
		reporter.config().setDocumentTitle("Automation Execution Report");
		reporter.config().setReportName("Automation Test Results");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation Tester", "Ankur Dey");
		
		
		
	}
	
	@BeforeMethod
	@Parameters(value= {"browserName"})
	public void beforeMethod(String browserName, Method testMethod)
	{
		logger=extent.createTest(testMethod.getName());
		setupDriver(browserName);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: "+ methodName + "PASSED";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, m);
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: "+ methodName + "FAILED";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, m);
		}
		driver.quit();		
	}
	
	
	
	@AfterTest
	public void afterTest()
	{
		extent.flush();
	}
	
	public void setupDriver(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+File.separator+"driver"+File.separator+"chromedriver_101");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+File.separator+"driver"+File.separator+"msedgedriver");
			driver = new EdgeDriver();
		}
		/*else if(browserName.equalsIgnoreCase("opera"))
		{
			ChromeOptions options = new ChromeOptions();
			options.setBinary(new File(System.getProperty("user.dir")+File.separator+"driver"+File.separator+"msedgedriver"));

					 // For using Opera browser with ChromeDriver:
					 ChromeDriver driver = new ChromeDriver(options);
					 
					 
			System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")+File.separator+"driver"+File.separator+"operadriver");
			driver = new OperaDriver();
		}*/
		else
		{
			System.out.println("Unsupported version!");
		}
	
			
		
	}
	
	
}
