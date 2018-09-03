package com.auto.object;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.auto.utils.LogUtil;
import com.auto.utils.UIExecutorImpl;
import com.auto.utils.XMLUtil;

/**
 * 基础页面类
 * 
 * @author lijialong
 */
public class BasePage extends UIExecutorImpl {
	protected WebDriver driver;
	protected String pageName;// 页面名称
	protected String xmlPath;// 页面元素配置文件路径
	protected String pageXml;// 存储页面元素的xml全称,带后缀
	protected HashMap<String, Locator> locatorMap;
	public LogUtil log;

	public BasePage(WebDriver driver, String pageName, String pageXml) throws Exception {
		super(driver);
		this.driver = driver;
		this.pageName = pageName;
		// 获取page.xml路径
		xmlPath = this.getClass().getResource("").getPath() + pageXml;
		locatorMap = XMLUtil.readXMLDocument(xmlPath, pageName);
	}

	public void click(String locatorName) {
		super.click(getLocator(locatorName));
	}
	
	public void moveToElement(String locatorName) {
		super.moveToElement(getLocator(locatorName));
	}
   
	public void click(String locatorName, int index) {
		super.click(getLocator(locatorName), index);
	}

	public void sendKey(String locatorName, String value) {
		super.sendKey(getLocator(locatorName), value);
	}

	public void sendKey(String locatorName, String value, int index) {
		super.sendKey(getLocator(locatorName), value, index);
	}
   
	public String getText(String locatorName) {
		return super.getText(getLocator(locatorName));
	}

	public String getText(String locatorName, int index) {
		return super.getText(getLocator(locatorName), index);
	}

	public WebElement getElement(String locatorName) {
		return super.getElement(getLocator(locatorName));
	}

	public List<WebElement> getElements(String locatorName) {
		return super.getElements(getLocator(locatorName));
	}

	public boolean isElementDisplayed(String locatorName) {
		return super.isElementDisplayed(getLocator(locatorName));
	}

	
	public void switchWindow(String title) {
		super.switchWindow(title);
	}

	public void switchFrame(String locatorName) {
		super.switchFrame(getLocator(locatorName));
	}

	public String getAlertText() {
		return super.getAlertText();
	}

	public boolean isElementExist(String locatorName) {
		return super.isElementExist(getLocator(locatorName));
	}

	public void jsExecutor(String jString) {
		super.jsExecutor(jString);
	}

	public List<WebElement> getLevelElements(Map<String, Integer> locatorName_indexs) throws Exception {
		Map<Locator, Integer> locator_indexs = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> locatorName_index : locatorName_indexs.entrySet()) {
			locator_indexs.put(getLocator(locatorName_index.getKey()), locatorName_index.getValue());
		}
		return super.getLevelElement(locator_indexs);
	}
	
	public void appSwipeUp(int duration, int num) {
		super.appSwipeUp(duration, num);
	}

	public void appSwipeDown(int duration, int num) {
		super.appSwipeDown(duration, num);
	}

	public void appSwipeLeft(int duration, int num) {
		super.appSwipeLeft(duration, num);
	}
    
	public void appSwipeRight(int duration, int num) {
		super.appSwipeDown(duration, num);
	}
	
	public void appTapByXY(int fingers, int x, int y, int duration) {
		super.appTapByXY(fingers, x, y, duration);
	}
	
	public boolean getToastText(String str, int sec) {
		return super.getToastText(str, sec);
	}

	/**
	 * 根据locatorName返回对应的locator
	 * 
	 * @author lijialong
	 */
	public Locator getLocator(String locatorName) {
		Locator locator = null;
		if (locatorMap != null) {
			locator = locatorMap.get(locatorName);
		}
		return locator;
	}
}
