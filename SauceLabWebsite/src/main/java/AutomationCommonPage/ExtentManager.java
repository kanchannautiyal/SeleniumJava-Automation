package AutomationCommonPage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentManager {

	public static ExtentReports extent;
	
	
	
	public static ExtentReports getExtentReports() {
	if(extent == null) {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport2.HTML");
		extent.attachReporter(spark);
		
	}
	return extent;
}
}
