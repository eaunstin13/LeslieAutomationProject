package wdMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import utils.GlobalVariables;
import utils.Reporter;

public class SeMethods extends Reporter implements WdMethods {


	protected static final ThreadLocal<SeMethods> driverThreadLocal = new ThreadLocal<SeMethods>();
	public RemoteWebDriver driver;
	protected Properties prop;
	public boolean bRemote;
	public String primaryWindowHandle,sHubUrl,sHubPort,browser;
	public int short_wait, long_wait, waitTime;

	public void setDriver(SeMethods methods) {
		driverThreadLocal.set(methods);
	}

	public RemoteWebDriver getDriver() {
		return driverThreadLocal.get().driver;
	}
	
	public SeMethods() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			bRemote = Boolean.valueOf(prop.getProperty("REMOTE"));
//			mobileWeb = val; 
			browser = prop.getProperty("BROWSER");
//			environment=GlobalVariables.getGlobalVariable("environment");
			short_wait = Integer.parseInt(prop.getProperty("SHORT_WAIT"));
			long_wait =Integer.parseInt(prop.getProperty("LONG_WAIT"));
			waitTime =Integer.parseInt(prop.getProperty("WAIT"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will launch the browser in local machine and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author    AutomationQA
	 * @param url - The url with http or https
	 * @return 
	 * 
	 */
	public RemoteWebDriver startApp(String browser, String testCase) {
		return startApp(browser, testCase,false);
	}

	/**
	 * This method will launch the browser in grid node (if remote) and maximise the browser and set the
	 * wait for 30 seconds and load the url 
	 * @author    AutomationQA
	 * @param url - The url with http or https
	 * @return 
	 * 
	 */
	public synchronized RemoteWebDriver startApp(String browser, String testCase, boolean bRemote) {
		try {
			// this is for remote run
			if (bRemote) {
				try {
//					ChromeOptions options = new ChromeOptions();
//					Map<String, Object> prefs = new HashMap<String, Object>();
//					prefs
					
					if(GlobalVariables.getGlobalVariable("mobileWeb").contains("true")? true : false) {
						Map<String, Object> deviceMetrics = new HashMap<>();
						deviceMetrics.put("width", 360);
						deviceMetrics.put("height", 640);
						deviceMetrics.put("pixelRatio", 3.0);
						
						Map<String, Object> mobileEmulation = new HashMap<>();
						mobileEmulation.put("deviceMetrics", deviceMetrics);
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, "
						        + "like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");
						    options.addArguments("--start-maximized");
						options.addArguments("disable-infobars");
						options.addArguments("--disable-extensions");
						options.setExperimentalOption("mobileEmulation", mobileEmulation);
						options.addArguments("--headless");
					//	driver = new RemoteWebDriver(new URL("http://localhost:5000/wd/hub"), options);
						driver = new RemoteWebDriver(new URL("http://10.0.2.192:4444/wd/hub"), options);

						
					}else {
						ChromeOptions options = new ChromeOptions();
						//Map<String, Object> prefs = new HashMap<String, Object>();
						//driver = new RemoteWebDriver(new URL("http://localhost:5000/wd/hub"), options);
						driver = new RemoteWebDriver(new URL("http://10.0.2.192:4444/wd/hub"), options);

					}
					
					//reportStep("Running with this remote URL: http://"ava":"+remoteHubPort+"/wd/hub", "INFO");	
					System.out.println("Script start to execuitng using "+browser+" Browser" );
					
					
					
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
					reportStep("Getting exception in Remote Run", "FAIL",false);
				} 
			
//				try {
//					prop = new Properties();
//					prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
//					DesiredCapabilities caps = new DesiredCapabilities();
//					caps.setCapability("name", testCase);
//					caps.setCapability("testFileNameTemplate", "{testName}_{browser}_{testStatus}");
//					caps.setCapability("browserName", prop.getProperty("browserName"));
//					caps.setCapability("device", prop.getProperty("device"));
//					caps.setCapability("realMobile", prop.getProperty("realMobile"));
//					caps.setCapability("os_version", prop.getProperty("os_version"));
//					caps.setCapability("name", prop.getProperty("name"));
//					String URL = "https://" +  prop.getProperty("USERNAME") + ":" + prop.getProperty("AUTOMATE_KEY") + "@hub-cloud.browserstack.com/wd/hub";
//					driver = new RemoteWebDriver(new URL(URL) , caps);
//					System.out.println("Started running on Browser stack");
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//					reportStep("Getting exception in Browser stack Run", "FAIL", false);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			else{ // this is for local run
				if(browser.equalsIgnoreCase("chrome")){
					System.setProperty("webdriver.chrome.driver",prop.getProperty("Chrome_Driver_Path")); 
					if(GlobalVariables.getGlobalVariable("mobileWeb").contains("true")? true : false) {
						Map<String, Object> deviceMetrics = new HashMap<>();
						deviceMetrics.put("width", 360);
						deviceMetrics.put("height", 640);
						deviceMetrics.put("pixelRatio", 3.0);
						
						Map<String, Object> mobileEmulation = new HashMap<>();
						mobileEmulation.put("deviceMetrics", deviceMetrics);
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, "
						        + "like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");
						    options.addArguments("--start-maximized");
						options.addArguments("disable-infobars");
						options.addArguments("--disable-extensions");
						options.setExperimentalOption("mobileEmulation", mobileEmulation);
						driver = new ChromeDriver(options);
						
					}else {
					driver = new ChromeDriver();
					}
					
				}else{
					System.setProperty("webdriver.gecko.driver", prop.getProperty("FF_Driver_Path"));
					driver = new FirefoxDriver();
				}
				
			}
			SeMethods sm = new SeMethods();
			sm.driver = driver;
			setDriver(sm);
			Capabilities cap = driver.getCapabilities();
			if(!GlobalVariables.getGlobalVariable("mobileWeb").contains("true")? true : false) {
            if (cap.getPlatform().toString().contains("MAC")) {
                driver.manage().window().fullscreen();
            }else {
                driver.manage().window().maximize();
            }
			}
			getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			switch (GlobalVariables.getGlobalVariable("environment")) {
			case "US":
				getDriver().get(prop.getProperty("US_URL"));
				break;
			case "CANADA":
				getDriver().get(prop.getProperty("CANADA_URL"));
				break;
			default:
				System.out.println("Could not find the specified environment "+GlobalVariables.getGlobalVariable("environment")+" please check it.");
				reportStep("Could not find the specified environment "+GlobalVariables.getGlobalVariable("environment")+" please check it.", "FAIL",false);
				break;
			}
			
			waitForServerToPerformAction(10);
			//Close the TOP Sale Banner
			try {
				getDriver().findElement(By.xpath("//button[@aria-label='Close']")).click();
			} catch (Exception e) {
			}
			//Close the Privacy Policy and Terms of Use
			try{
				getDriver().findElement(By.xpath("//div[@id='close_btn_target']")).click();
			}catch (Exception e ){
			}

			//reportStep("The browser:" + browser + " launched successfully", "PASS");

		} catch (Exception e) {
			e.printStackTrace();
			//reportStep("The browser:" + browser + " could not be launched", "FAIL");
		}

		return getDriver();
	}
	
	public WebElement locateElement(String locator, String locValue) {
		try {
			switch(locator) {
			case("id"): return getDriver().findElementById(locValue);
			case("link"): return getDriver().findElementByLinkText(locValue);
			case("xpath"):return getDriver().findElementByXPath(locValue);
			case("name"): return getDriver().findElementByName(locValue);
			case("class"): return getDriver().findElementByClassName(locValue);
			case("tag"):return getDriver().findElementByTagName(locValue);
			}
		} catch (NoSuchElementException e) {
			reportStep("The element with locator "+locator+" not found.","FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while finding "+locator+" with the value "+locValue, "FAIL");
		}
		return null;
	}

	public WebElement locateElement(String locValue) {
		return getDriver().findElementById(locValue);
	}

	public void type(WebElement ele, String data) {
		try {
			ele.clear();
			ele.clear();
			ele.sendKeys(data);
			reportStep("The data: "+data+" entered successfully in the desired field", "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The data: "+data+" could not be entered in the field :"+ele,"FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while entering "+data+" in the field :"+ele, "FAIL");
		}
	}
	
	public void typeAndEnter(WebElement ele, String data) {
		try {
			ele.clear();
			ele.clear();
			ele.sendKeys(data,Keys.ENTER);
			reportStep("The data: "+data+" entered successfully in the desired field", "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The data: "+data+" could not be entered in the field :"+ele,"FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while entering "+data+" in the field :"+ele, "FAIL");
		}
	}

	public void click(WebElement ele) {
		try {	
			WebDriverWait wait = new WebDriverWait(getDriver(), 20);
			wait.until(ExpectedConditions.elementToBeClickable(ele));	
			ele.click();
		} catch (InvalidElementStateException e) {
			scrollIntoViewTopOfScreen(ele);
			clickElementByJavaScript(ele);
		} catch (WebDriverException e) {
			e.printStackTrace();
			reportStep("Unknown exception occured while clicking in the "+ele+" field : "+e.getMessage(), "FAIL");
		} 
	}

	public void clickWithNoSnap(WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 5);
			wait.until(ExpectedConditions.elementToBeClickable(ele));	
			ele.click();			
			reportStep("The element :"+ele+"  is clicked.", "PASS",false);
		} catch (InvalidElementStateException e) {
			reportStep("The element: "+ele+" could not be clicked", "FAIL",false);
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking in the field :","FAIL",false);
		} 
	}

	public String getText(WebElement ele) {	
		String elementText = "";
		try {
			elementText = ele.getText();
		} catch (WebDriverException e) {
			e.printStackTrace();
			reportStep("The element: "+ele+" could not be found.", "FAIL");
		}
		return elementText;
	}

	public String getTitle() {		
		String appTitle = "";
		try {
			appTitle =  getDriver().getTitle();
		} catch (WebDriverException e) {
			reportStep("Unknown Exception Occured While fetching Title", "FAIL");
		} 
		return appTitle;
	}

	public String getAttribute(WebElement ele, String attribute) {		
		String attributeName = "";
		try {
			attributeName=  ele.getAttribute(attribute);
		} catch (WebDriverException e) {
			reportStep("The element: "+ele+" could not be found.", "FAIL");
		} 
		return attributeName;
	}

	public void selectDropDownUsingText(WebElement ele, String value) {
        try {
            new Select(ele).selectByVisibleText(value);
            reportStep("The dropdown is selected with text "+value,"PASS");
        } catch (WebDriverException e) {
            reportStep("The element: "+ele+" could not be found."+e.getMessage(), "FAIL");
        }
    }
	
	public void selectAngularDropDownUsingText(WebElement ele, String value) {
        try {
             ele.click();
             click(getDriver().findElement(By.xpath("//mat-option/span[contains(.,'"+value+"')]")));
        } catch (WebDriverException e) {
            reportStep("The element: "+ele+" could not be found."+e.getMessage(), "FAIL");
        }
    }
	
	public void selectDropDownUsingIndex(WebElement ele, int index) {
		try {
			new Select(ele).selectByIndex(index);
			reportStep("The dropdown is selected with index "+index,"PASS");
		} catch (WebDriverException e) {
			reportStep("The element: "+ele+" could not be found.", "FAIL");
		} 

	}

	public boolean verifyTitle(String title) {
		boolean bReturn =false;
		try {
			if(getTitle().equals(title)) {
				reportStep("The title of the page matches with the value :"+title,"PASS");
				bReturn= true;
			}else {
				reportStep("The title of the page:"+getDriver().getTitle()+" did not match with the value :"+title, "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		} 
		return bReturn;
	}
	
	
	public boolean verifyTitleContains(String title) {
		boolean bReturn =false;
		try {
			if(getTitle().contains(title)) {
				reportStep("The title of the page matches with the value :"+title,"PASS");
				bReturn= true;
			}else {
				reportStep("The title of the page:"+getDriver().getTitle()+" did not match with the value :"+title, "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		} 
		return bReturn;
	}

	public void verifyExactText(WebElement ele, String expectedText) {
		try {
			if(getText(ele).equals(expectedText)) {
				reportStep("The text: "+getText(ele)+" matches with the value :"+expectedText,"PASS");
			}else {
				reportStep("The text "+getText(ele)+" doesn't matches the actual "+expectedText,"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Text", "FAIL");
		} 

	}

	public void verifyPartialText(WebElement ele, String expectedText) {
		try {
			if(getText(ele).contains(expectedText)) {
				reportStep("The expected text "+expectedText+" contains the actual "+getText(ele),"PASS");
			}else {
				reportStep("The expected text "+expectedText+" doesn't contain the actual "+getText(ele),"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Text", "FAIL");
		} 
	}
	
	 public void verifyIgnoreCaseText(WebElement ele, String expectedText) {
	        try {
	            if(getText(ele).equalsIgnoreCase(expectedText)) {
	                reportStep("The text: "+getText(ele)+" matches with the value :"+expectedText,"PASS");
	            }else {
	                reportStep("The text "+getText(ele)+" doesn't matches the actual "+expectedText,"FAIL");
	            }
	        } catch (WebDriverException e) {
	            reportStep("Unknown exception occured while verifying the Text", "FAIL");
	        } 

	    } 

	public void verifyExactAttribute(WebElement ele, String attribute, String value) {
		try {
			if(getAttribute(ele, attribute).equals(value)) {
				reportStep("The expected attribute :"+attribute+" value matches the actual "+value,"PASS");
			}else {
				reportStep("The expected attribute :"+attribute+" value does not matches the actual "+value,"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Attribute Text", "FAIL");
		} 

	}

	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		try {
			if(getAttribute(ele, attribute).contains(value)) {
				reportStep("The expected attribute :"+attribute+" value contains the actual "+value,"PASS");
			}else {
				reportStep("The expected attribute :"+attribute+" value does not contains the actual "+value,"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Attribute Text", "FAIL");
		}
	}

	public void verifySelected(WebElement ele) {
		try {
			if(ele.isSelected()) {
				reportStep("The element "+ele+" is selected","PASS");
			} else {
				reportStep("The element "+ele+" is not selected","FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException as occured: In verifySelected: "+e.getMessage(), "FAIL");
		}
	}
	public void verifyEnabled(WebElement ele) {
		try {
			if(ele.isEnabled()) {
				reportStep("The element "+ele+" is Enabled","PASS");
			} else {
				reportStep("The element "+ele+" is not Enabled","FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException as occured: In verifyEnabled"+e.getMessage(), "FAIL");
		}
	}

	public void verifyDisplayed(WebElement ele) {
		try {
			if(ele.isDisplayed()) {
				reportStep("The element "+ele+" is visible","PASS");
			} else {
				reportStep("The element "+ele+" is not visible","FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException as occured: In VerifyDisplayed"+e.getMessage(), "FAIL");
		} 
	}
	
	public boolean isDisplayed(WebElement ele) {
		boolean bReturn =false;
		try {
			if(ele.isDisplayed()) {
				bReturn= true;
			} 
		} catch (WebDriverException e) {
			//reportStep("WebDriverException as occured: In VerifyDisplayed"+e.getMessage(), "FAIL");
		} 
		return bReturn;
	}
	
	public void isNotDisplayed(WebElement ele) {
		try {
			if(ele.isDisplayed()) {
				reportStep("The element "+ele+" is visible","FAIL");
			} 
		} catch (WebDriverException e) {
			reportStep("The element "+ele+" is not visible"+e.getMessage(), "PASS");
		} 
		
	}
	
	public boolean isExists(WebElement ele) {
		if(ele.getSize() != null) {
			return true;
		}
		else {
			return false;
		}
	}
	

	public void switchToWindow(int index) {
		try {
			Set<String> allWindowHandles = getDriver().getWindowHandles();
			List<String> allHandles = new ArrayList<>();
			allHandles.addAll(allWindowHandles);
			getDriver().switchTo().window(allHandles.get(index));
		} catch (NoSuchWindowException e) {
			reportStep("The driver could not move to the given window by index "+index,"PASS");
		} catch (WebDriverException e) {
			reportStep("WebDriverException as occured in SwitchToWindow : "+e.getMessage(), "FAIL");
		}
	}
	
	public void switchToParentWindow() {
		try {

			Set<String> winHandles = driver.getWindowHandles();
			for (String wHandle : winHandles) {
				driver.switchTo().window(wHandle);
				break;
			}
		} catch (Exception e) {
			reportStep("The window could not be switched to the first window.", "FAIL");
		}
	}
	
	public void switchToLastWindow() {
		try {
			Set<String> winHandles = driver.getWindowHandles();
			for (String wHandle : winHandles) {
				driver.switchTo().window(wHandle);
			}
		} catch (Exception e) {
			reportStep("The window could not be switched to the last window.", "FAIL");
		}
	}
	
	public void switchToParentFrame() {
		try {
			driver.switchTo().defaultContent();
			reportStep("switch In to the Parent Frame ","PASS");
		} catch (NoSuchFrameException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException occured in switchToFrame: "+e.getMessage(), "FAIL");
		}
	}

	public void switchToFrame(WebElement ele) {
		try {
			getDriver().switchTo().frame(ele);
			reportStep("switch In to the Frame "+ele,"PASS");
		} catch (NoSuchFrameException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException occured in switchToFrame: "+e.getMessage(), "FAIL");
		} 
	}

	public void acceptAlert() {
		String text = "";		
		try {
			Alert alert = getDriver().switchTo().alert();
			text = alert.getText();
			alert.accept();
			reportStep("The alert "+text+" is accepted.","PASS");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.","FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException occured in acceptAlert: "+e.getMessage(), "FAIL");
		}  
	}

	public void dismissAlert() {
		String text = "";		
		try {
			Alert alert = getDriver().switchTo().alert();
			text = alert.getText();
			alert.dismiss();
			reportStep("The alert "+text+" is dismissed.","PASS");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.","FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException occured in dismissAlert: "+e.getMessage(), "FAIL");
		} 

	}
	
	public void waitForElementToLoad(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 30);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (Exception e) {
        	e.printStackTrace();
            reportStep("WebDriverException occured in waitForElement:"+e.getMessage(), "Fail");
        }
    } 
	
	public void clickElementByJavaScript(WebElement ele) {
		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", ele);
			// reportStep("Element clicked successfully", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("WebDriverException occured in waitForElement:" + e.getMessage(), "Fail");
		}
	}

	public String getAlertText() {
		String alertText = "";		
		try {
			Alert alert = getDriver().switchTo().alert();
			alertText = alert.getText();
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.","FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException occured in getAlertText: "+e.getMessage(), "FAIL");
		} 
		return alertText;
	}

	public long takeSnap(){
		long snapNumber = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile(getDriver().getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+snapNumber+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return snapNumber;
	}
	
	public void waitForServerToPerformAction(int second){
	//	System.out.println("Waiting for server second");
        try {
        	Thread.sleep(second*500);
        } catch (InterruptedException e) {
            reportStep("Unexpected error occured when waiting for server to perform action","FAIL", false);
        }
    }
	
	public void closeBrowser() {
		try {
			getDriver().close();
			reportStep("The browser is closed","PASS", false);
		} catch (Exception e) {
			reportStep("The browser could not be closed","FAIL", false);
		}
	}

	public void closeAllBrowsers() {
		try {
			getDriver().quit();
			reportStep("The opened browsers are closed","PASS", false);
		} catch (Exception e) {
			reportStep("Unexpected error occured in Browser","FAIL", false);
		}
	}
	
	public void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean doesPageObjectExist(By locator){
		List<WebElement> ele = getDriver().findElements(locator);
		return !ele.isEmpty();
	} 
	
	public void scrollIntoViewBottomOfScreen(WebElement element) {
		try {
			 getDriver().executeScript("arguments[0].scrollIntoView(false);", element);
			 waitForServerToPerformAction(1);
			 new WebDriverWait(getDriver(),long_wait).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			reportStep("Unexpected error occured in Browser"+e,"FAIL", false);
		}
	}
	
	public void scrollIntoViewTopOfScreen(WebElement element) {
		try {
			 getDriver().executeScript("arguments[0].scrollIntoView(true);", element);
			 waitForServerToPerformAction(1);
			 new WebDriverWait(getDriver(),long_wait).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			reportStep("Unexpected error occured in Browser"+e,"FAIL", false);
		}
	}
}