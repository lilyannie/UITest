package com.auto.utils;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.auto.utils.ScreenShot;
import com.auto.utils.TimeUtil;

/**
 * 记录测试过程
 * 
 * @author lijialong
 */
public class TestNGListener extends TestListenerAdapter {

	private static WebDriver driver;
    private String curTime;
	LogUtil log = new LogUtil(TestNGListener.class);

	public static void setDriver(WebDriver driver) {
		TestNGListener.driver = driver;
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		log.info(tr.getMethod()+"Test Success");
		super.onTestSuccess(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		log.error(tr.getMethod()+"Test Failure");
		super.onTestFailure(tr);
		try {
			takeScreenShot(tr);
			showScreenShot(tr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void showScreenShot(ITestResult tr) {
		// TODO Auto-generated method stub
		String filePath = System.getProperty("user.dir").replace("\\", "/")+"/img"; 
        Reporter.log("<img src='"+filePath+"/"+tr.getName()+curTime+".png' hight='300' width='500'/>");
	}

	private void takeScreenShot(ITestResult tr) throws IOException {
		// TODO Auto-generated method stub        
        ScreenShot screenShot = new ScreenShot(driver);
		//获取当前project目录
		String path = System.getProperty("user.dir").replace("\\", "/");
		//加上时间戳以区分截图
		curTime = TimeUtil.getDate("yyyyMMddHHmmss");
		screenShot.saveScreenShot(path + "/img/", "testFail" + tr.getName()+curTime+ ".png");
		

	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log.error("Test Skipped");
		super.onTestSkipped(tr);
	}

	@Override
	public void onStart(ITestContext testContext) {
		log.info("Test Start");
		super.onStart(testContext);
	}

	@Override
	public void onFinish(ITestContext testContext) {
		log.info("Test Finish");
		super.onFinish(testContext);
	}

}
