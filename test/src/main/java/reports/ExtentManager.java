package reports;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	/*
	 * 	 * 
	 */
	

	
	private static ExtentReports extent; //need to download 2 jar files of extent reports
	public static String screenshotFolderPath;
	
	
	public static ExtentReports getInstance(String reportPath, String folderName) {
		Date date = new Date();
		String reportFileName = "report.html";
		folderName = folderName + date.toString().replace(":", "_").replace(" ", "_") + "/";
		new File(reportPath + folderName + "//screenshots").mkdirs();
		System.out.println("cerated new file");
		System.out.println("Report Name = "  + reportFileName);
		reportPath = reportPath + folderName;
		screenshotFolderPath = reportPath + "//screenshots//";
		if(extent == null) {
			System.out.println(reportPath +  reportFileName);
			createInstance(reportPath + reportFileName);
			
		}
		return extent;
	}
	
	public static ExtentReports createInstance(String fileName) {
		System.out.println("createInstance of extent");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Reports");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Reports - Telstra Automation");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		return extent;
		
	}
	
	public static void endTest() {
		extent.flush();
	}
	
}