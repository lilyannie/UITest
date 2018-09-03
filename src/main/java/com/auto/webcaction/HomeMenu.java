package com.auto.webcaction;

import org.openqa.selenium.WebDriver;

import com.auto.object.BasePage;

public class HomeMenu {
	private WebDriver driver;
	private String pageXml = "homepage.xml";

	public HomeMenu(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 点击菜单
	 * 
	 * @author lilu
	 */
	public void ClickMenu(String mainMenu,String kidMenu) throws Exception {
		BasePage homepage = new BasePage(driver, "homePage", pageXml);
	    homepage.moveToElement(mainMenu);
	    homepage.click(kidMenu);
	}
}
