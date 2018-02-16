package seleniumcodes;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;



public class UploadDownloadFile {

	public String downloadFilepath = "C:\\Users\\nkp\\Downloads";
	public String uploadFilePath = "C:\\Users\\nkp\\Desktop\\illusion.docx";
	public WebDriver driver=null;
	
	
	public void initialization()
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void testUpload() throws InterruptedException
	{
		initialization();
		driver.get("https://encodable.com/uploaddemo/");
		driver.findElement(By.xpath("//*[@id='uploadname1']")).click();
		Thread.sleep(2000);
		uploadFile(uploadFilePath);
		Thread.sleep(2000);
		driver.quit();
	}
	
	/**
     * This method will set any parameter string to the system's clipboard.
     */
	public void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		   
		}
	
	public void uploadFile(String fileLocation) {
        try {
        	//Setting clipboard with file location
            setClipboardData(fileLocation);
            Thread.sleep(3000);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
            
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
	@Test(priority=2)
	public void testDownloadFile(){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//chromedriver.exe");

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		options.addArguments("--disable-extensions"); // to disable browser
														// extension popup
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("http://www.seleniumhq.org/download/");
		driver.findElement(By.linkText("32 bit Windows IE")).click();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	}
