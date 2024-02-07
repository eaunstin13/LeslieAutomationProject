package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public abstract class Reporter {
	
	public static ExtentHtmlReporter html;
    public static ExtentReports extent;
    public ExtentTest test, suiteTest;
    public String testCaseName, testNodes, testDescription, category, authors;
    public Boolean appendExisting = true;

	String path ="C:\\Results";
	String imagePath = "C:\\Results\\Image";


	private static Map<RemoteWebDriver,ExtentTest> testDriver;

	public void reportStep(String desc, String status, boolean bSnap) {

		 MediaEntityModelProvider img = null;
	        if(bSnap && !status.equalsIgnoreCase("INFO")){

	            long snapNumber = 100000L;
	            snapNumber = takeSnap();
	            try {
	                /*img = MediaEntityBuilder.createScreenCaptureFromPath
	                        ("./../reports/images/"+snapNumber+".jpg").build();

	                 */
					img = MediaEntityBuilder.createScreenCaptureFromPath
							(imagePath+"/"+snapNumber+".jpg").build();
	            } catch (IOException e) {                
	            }
	        }

		// Write if it is successful or failure or information
		if(status.toUpperCase().equals("PASS")){
			testDriver.get(getDriver()).pass(desc,img);
		}else if(status.toUpperCase().equals("FAIL")){
			testDriver.get(getDriver()).fail(desc, img);
			throw new RuntimeException("FAILED");
		}else if(status.toUpperCase().equals("INFO")){
			testDriver.get(getDriver()).info(desc);
		}else if(status.toUpperCase().equals("WARN")){
			testDriver.get(getDriver()).warning(desc, img);
		}
	}
	
	public void reportStep(String desc, String status) {
        reportStep(desc, status, false);
    }
	
	public abstract long takeSnap();


	public void startResult(){
		try {
			String date = DateFormat.getDateInstance().format(new Date());
			String dateTime = DateFormat.getDateTimeInstance().format(new Date());
			dateTime = dateTime.replace(",","_");
			dateTime = dateTime.replace(" ","_");
			dateTime = dateTime.replace(":","-");


			File f = new File(imagePath);
			if (f.mkdir() == true) {
				System.out.println("Directory has been created successfully");
			}
			else {
				System.out.println("Directory cannot be created");
			}

			testDriver = new HashMap<RemoteWebDriver, ExtentTest>();
		//html = new ExtentHtmlReporter("./reports/result_"+dateTime+".html");
			html = new ExtentHtmlReporter(path+"/result_"+dateTime+".html");

			html.setAppendExisting(appendExisting);
        extent = new ExtentReports();        
        extent.attachReporter(html);
        if (!appendExisting) {
    		
			FileUtils.cleanDirectory(new File("./reports/images/"));
        }
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public synchronized ExtentTest startTestModule(String testCaseName, String testDescription) {
		return testDriver.put(getDriver(), extent.createTest(testCaseName,testDescription));
    }
	
	public synchronized ExtentTest startTestCase(String testCaseName, String testDescription,String testNodes) {
		return testDriver.put(getDriver(), extent.createTest(testCaseName,testDescription)).createNode(testNodes);
    }


	public void endResult(){		
		extent.flush();
	}

	public abstract RemoteWebDriver getDriver();


}