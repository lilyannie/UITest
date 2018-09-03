package com.auto.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.auto.object.BasePage;
import com.auto.utils.BrowserUtil;
import com.auto.utils.ReadExcel;
import com.auto.utils.TestNGListener;
import com.auto.webcaction.Login;

/**
 * 登录测试类
 * @author lilu
 * */
@Listeners({ TestNGListener.class })

public class LoginTest {
	
	private WebDriver driver;
	private Login login;
    private String pageXml = "loginpage.xml";
    
	@DataProvider(name = "false_loginParams")
	/*
	public Object[][] false_loginParams() {
		return new Object[][] { { "", "12345678", "账号 字段是必需的。" }
		};
	}*/

	public Object[][] false_loginParams() throws Exception {
		String sourcefile = this.getClass().getResource("").getPath()+"testdata.xlsx";
		ReadExcel exc = new ReadExcel(sourcefile);
		return exc.case_data_excel("errorLogin", sourcefile);
	}
		
	@DataProvider(name = "true_loginParams")
	
	public Object[][] true_loginParams() {
		return new Object[][] { { "lilu", "12345678", "http://thjkadmin-qa2.tuhuanjk.com/account/nindex" } };
	}

	@BeforeClass
	@Parameters({ "browserType","browserDriverUrl","geckourl","waitTime", "url" })
	public void beforeClass(String browserType, String browserDriverUrl,String geckourl, long waitTime, String url) {
		driver = BrowserUtil.getInstance().getWebDriver(browserType, browserDriverUrl, geckourl, waitTime);
		driver.get(url);
	}

	@BeforeMethod
	public void BeforeMethod() {
		login = new Login(driver);
		TestNGListener.setDriver(driver);
	}

	@Test(dataProvider = "false_loginParams", description = "错误的用户信息登录")
	public void errorLogin(String username, String pwd) throws Exception {
		login.login(username, pwd);
		BasePage loginpage = new BasePage(driver, "loginPage", pageXml);	
		if(username.equals("")){
			Assert.assertEquals(loginpage.isElementExist("用户名为空"),true);
		}else if(pwd.equals("")){
			Assert.assertEquals(loginpage.isElementExist("密码为空"),true);
		}else{
			Assert.assertEquals(loginpage.isElementExist("登录失败"),true);
		}
		Thread.sleep(1000);
	}
	
	@Test(dataProvider = "true_loginParams", description = "正确的用户信息登录")
	public void trueLogin(String username, String pwd, String expectedurl) throws Exception {
		login.login(username, pwd);
		Thread.sleep(3000);
		String url=driver.getCurrentUrl();
		Assert.assertEquals(url, expectedurl);
	}
	

	@AfterClass
	public void afterClass() {
		
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
