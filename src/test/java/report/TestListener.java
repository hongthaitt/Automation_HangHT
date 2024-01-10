package report;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        AllureManager.saveTextLog("Test case " + result.getName() + " is passed.");
        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        AllureManager.saveTextLog(result.getName() + " is failed.");
        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        AllureManager.saveTextLog("Test case " + result.getName() + " is skipped.");
        AllureManager.saveScreenshotPNG();
    }

}
