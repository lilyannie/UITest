package com.auto.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.auto.webcaction.HomeMenu;
import com.auto.object.BasePage;
import com.auto.utils.BrowserUtil;
import com.auto.utils.ReadExcel;
import com.auto.utils.TestNGListener;



public class HomeMenuTest {
	private WebDriver driver;
    private HomeMenu homemenu;
	
	@DataProvider(name = "homeMenu_param")	
	/*public Object[][] homeMenu_param() {
		return new Object[][] { { "用户管理", "账号管理"},
			{"医生管理","医生账号管理"}
		};
	}*/
	public Object[][] homeMenu_param() throws Exception {
		String sourcefile = this.getClass().getResource("").getPath()+"testdata.xlsx";
		ReadExcel exc = new ReadExcel(sourcefile);
		return exc.case_data_excel("homeMenu", sourcefile);		
	}

	@BeforeClass
	@Parameters({"browserType","browserDriverUrl","geckourl","waitTime", "url" })
	public void beforeClass(String browserType, String browserDriverUrl,String geckourl, long waitTime, String url) {
		driver = BrowserUtil.getInstance().getWebDriver(browserType, browserDriverUrl, browserDriverUrl, waitTime);
	}
	
	@BeforeMethod
	public void BeforeMethod() {
		homemenu = new HomeMenu(driver);
		TestNGListener.setDriver(driver);
	}
	
	@Test(dataProvider = "homeMenu_param",dependsOnMethods = "com.auto.testcase.LoginTest.trueLogin")
	public void ClickMenu(String mainmenu, String kidmenu,String expectedurl) throws Exception{
		homemenu.ClickMenu(mainmenu, kidmenu);
		//BasePage memberpage = new BasePage(driver, "memberPage", "memberpage.xml");
		//if (memberpage.isElementExist("总页数")){
			Assert.assertEquals(driver.getCurrentUrl(), expectedurl);
		//}	
	}
	
	@AfterClass
	public void afterClass() {
		
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
