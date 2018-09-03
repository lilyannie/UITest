package com.auto.webcaction;

import org.openqa.selenium.WebDriver;

import com.auto.object.BasePage;

public class Login {
	private WebDriver driver;
	private String pageXml = "loginpage.xml";

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 登录操作
	 * 
	 * @author lilu
	 */
	public void login(String username, String pwd) throws Exception {
		BasePage loginPage = new BasePage(driver, "loginPage", pageXml);
		loginPage.sendKey("用户名", username);
		loginPage.sendKey("密码", pwd);
		loginPage.click("登录按钮");
	}


}
