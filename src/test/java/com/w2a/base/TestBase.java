package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel;
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser, testsiteurl;
	public static String userDir = System.getProperty("user.dir");

	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			// Load config properties file
			try {
				fis = new FileInputStream(userDir + "\\src\\test\\resources\\com\\w2a\\properties\\Config.properties");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded!!!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Load OR properties file
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\w2a\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				or.load(fis);
				log.debug("OR file loaded!!!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			excel = new ExcelReader(userDir + "\\src\\test\\resources\\com\\w2a\\excel\\testdata.xlsx");
			browser = config.getProperty("browser");
			testsiteurl = config.getProperty("testsiteurl");

			if ((System.getenv("browser") != null) && !(System.getenv("browser").isEmpty())) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser)", browser);

			if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equals("edge")) {
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\executables\\msedgedriver.exe");
				driver = new EdgeDriver();
			}
			else if (browser.equals("ie")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			else if (browser.equals("opera")) {
				System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\executables\\operadriver.exe");
				driver = new OperaDriver();
			}
			driver.get(testsiteurl);
			driver.manage().window().maximize();
			log.debug("Launched " + browser + " browser and navigated to: " + testsiteurl);
		}
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}

		log.debug("Test execution completed and " + browser + " browser is closed!!!");
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void click(String locator) {

		if (locator.endsWith("_CSS"))
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
		else if (locator.endsWith("_ID"))
			driver.findElement(By.id(or.getProperty(locator))).click();
		else if (locator.endsWith("_NAME"))
			driver.findElement(By.name(or.getProperty(locator))).click();
		else if (locator.endsWith("_XPATH"))
			driver.findElement(By.xpath(or.getProperty(locator))).click();
		else if (locator.endsWith("_LINK"))
			driver.findElement(By.linkText(or.getProperty(locator))).click();
		test.log(LogStatus.INFO, "Clicking on :" + locator);
	}

	public void type(String locator, String value) {

		if (locator.endsWith("_CSS"))
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
		else if (locator.endsWith("_ID"))
			driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
		else if (locator.endsWith("_NAME"))
			driver.findElement(By.name(or.getProperty(locator))).sendKeys(value);
		else if (locator.endsWith("_XPATH"))
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
		else if (locator.endsWith("_LINK"))
			driver.findElement(By.linkText(or.getProperty(locator))).sendKeys(value);

		// driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
		test.log(LogStatus.INFO, "Typing in :" + locator + " entered value as " + value);
	}

	public void verifyEquals(String expected, String actual) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			TestUtil.captureScreenshot();
			// ReportNg
			Reporter.log("<br>" + "Verification failure:" + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200> </img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// ExtentReports
			test.log(LogStatus.FAIL, "Verification failed with exception: " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		}
	}

}
