package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener
{
  public ExtentSparkReporter sparkReporter;//UI of the report
  public ExtentReports extent;//Project common information
  public ExtentTest test;//Write entries in report
  String reportName;
  public void onStart(ITestContext testContext)
  {
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    reportName = "Test Report - "+timeStamp+".html";
    sparkReporter = new ExtentSparkReporter("reports\\"+reportName);//specify location of the report

    sparkReporter.config().setDocumentTitle("Automation Report"); // Tile of report
    sparkReporter.config().setReportName("Rest API Testing Report"); // name of the report
    sparkReporter.config().setTheme(Theme.DARK);

    extent = new ExtentReports();
    extent.attachReporter(sparkReporter);
    extent.setSystemInfo("Project Name", "PetStore API");
    extent.setSystemInfo("Host name", "localhost");
    extent.setSystemInfo("Environemnt", "QA");
    extent.setSystemInfo("user", "chathurika");

  }

  public void onTestSuccess(ITestResult result)
  {

    test = extent.createTest(result.getName()); // create new entry in th report

    test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
  }

  public void onTestFailure(ITestResult result)
  {
    test = extent.createTest(result.getName()); // create new entry in th report
    test.createNode(result.getName());
    test.assignCategory(result.getMethod().getGroups());

    test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
    test.log(Status.FAIL,
             "TEST CASE FAILED IS " + result.getThrowable().getMessage()); // to add error/exception in extent report

  }

  public void onTestSkipped(ITestResult result)
  {
    test = extent.createTest(result.getName()); // create new entry in th report
    test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
  }

  public void onFinish(ITestContext testContext)
  {
    extent.flush();
  }
}
