package com.auto.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserUtil {
	private WebDriver driver;
	private static BrowserUtil browserUtil = null;

	/**
	 * 使用单例模式，只启用一个流浏览器
	 * 
	 */
	private BrowserUtil() {
	}

	public static synchronized BrowserUtil getInstance() {
		if (browserUtil == null) {
			browserUtil = new BrowserUtil();
		}
		return browserUtil;
	}

	/**
	 * 按需启动浏览器并获取其driver
	 * 
	 * @author lilu
	 */
	public WebDriver getWebDriver(String browser, String browserDriverUrl, String geckourl,long sec) {
		if (getDriver() == null) {
			switch (browser) {
			case "chrome":
				driver = chrome(browserDriverUrl, geckourl, sec);
				break;
			case "firefox":
				driver = fireFox(browserDriverUrl, geckourl,sec);
				break;
			}
			setDriver(driver);
		}
		return getDriver();
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * 启动chrome浏览器
	 * 
	 * @param browserDriverUrl
	 *            浏览器驱动url
	 * @param sec
	 *            所以页面操作的等待超时时长
	 * @author lijialong
	 */
	public WebDriver chrome(String browserDriverUrl, String geckourl,long sec) {
		System.setProperty("webdriver.gecko.driver",geckourl); 
		System.setProperty("webdriver.chrome.driver", browserDriverUrl);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * 启动fireFox浏览器
	 * 
	 * @param browserDriverUrl
	 *            浏览器驱动url
	 * @param sec
	 *            所有页面操作的等待超时时长
	 * @author lijialong
	 */
	public WebDriver fireFox(String browserDriverUrl,String geckourl, long sec) {
		System.setProperty("webdriver.gecko.driver",geckourl); 
		System.setProperty("webdriver.firefox.bin", browserDriverUrl);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(sec, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		return driver;
	}

	
	
}
