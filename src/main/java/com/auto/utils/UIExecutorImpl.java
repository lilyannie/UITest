package com.auto.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.auto.object.Locator;


/**
 * UIExecutor接口实现类
 * 
 * @author lijialong
 */
public class UIExecutorImpl implements UIExecutor {
	private WebDriver driver;
	public LogUtil log;

	public UIExecutorImpl(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
	/**
	 * 点击元素
	 * 
	 * @author lijialong
	 * @param locator
	 */
	public void click(Locator locator) {
		WebElement element = getElement(locator);
		element.click();
	}

	/**
	 * 输入文本
	 * 
	 * @author lijialong
	 */
	@Override
	public void sendKey(Locator locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * 获取文本
	 * 
	 */
	@Override
	public String getText(Locator locator) {
		WebElement element = getElement(locator);
		return element.getText();
	}

	/**
	 * 获取元素
	 * 
	 * @author lijialong
	 * 
	 */
	@Override
	public WebElement getElement(Locator locator) {
		WebElement element = null;
		String address = locator.getAddress();
		long waitTime = locator.getWaitSec();
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		log = new LogUtil(UIExecutorImpl.class);
		switch (locator.getByType()) {
		case xpath:
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(address)));
				element = driver.findElement(By.xpath(address));
			} catch (Exception e) {
				log.info("findElement ByXpath:" + address + " failed,NoSuchElement");
			}
			break;
		case id:
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(address)));
				element = driver.findElement(By.id(address));
			} catch (Exception e) {
				log.info("findElement ById:" + address + " failed,NoSuchElement");
			}
			break;
		case className:
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(address)));
				element = driver.findElement(By.className(address));
			} catch (Exception e) {
				log.info("findElement ByClassName:" + address + " failed,NoSuchElement");
			}
			break;
		case linkText:
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(address)));
				element = driver.findElement(By.linkText(address));
			} catch (Exception e) {
				log.info("findElement ByLinkText:" + address + " failed,NoSuchElement");
			}
			break;
		case name:
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(address)));
				element = driver.findElement(By.name(address));
			} catch (Exception e) {
				log.info("findElement ByName:" + address + " failed,NoSuchElement");
			}
			break;
		case tagName:
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(address)));
				element = driver.findElement(By.tagName(address));
			} catch (Exception e) {
				log.info("findElement ByTagName:" + address + " failed,NoSuchElement");
			}
			break;
		default:
			break;
		}
		return element;
	}

	/**
	 * 获取元素列表
	 * 
	 * @author lijialong
	 */
	@Override
	public List<WebElement> getElements(Locator locator) {
		List<WebElement> elements = null;
		String address = locator.getAddress();
		long waitTime = locator.getWaitSec();
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		log = new LogUtil(UIExecutorImpl.class);
		switch (locator.getByType()) {
		case xpath:
			try {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(address)));
				elements = driver.findElements(By.xpath(address));
			} catch (Exception e) {
				log.info("findElements ByXpath:" + address + " failed,NoSuchElements");
			}
			break;
		case id:
			try {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(address)));
				elements = driver.findElements(By.id(address));
			} catch (Exception e) {
				log.info("findElements ById:" + address + " failed,NoSuchElements");
			}
			break;
		case className:
			try {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(address)));
				elements = driver.findElements(By.className(address));
			} catch (Exception e) {
				log.info("findElements ByClassName:" + address + " failed,NoSuchElements");
			}
			break;
		case linkText:
			try {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(address)));
				elements = driver.findElements(By.linkText(address));
			} catch (Exception e) {
				log.info("findElements ByLinkText:" + address + " failed,NoSuchElements");
			}
			break;
		case name:
			try {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(address)));
				elements = driver.findElements(By.name(address));
			} catch (Exception e) {
				log.info("findElements ByName:" + address + " failed,NoSuchElements");
			}
			break;
		case tagName:
			try {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(address)));
				elements = driver.findElements(By.tagName(address));
			} catch (Exception e) {
				log.info("findElements ByTagName:" + address + " failed,NoSuchElements");
			}
			break;
		default:
			break;
		}
		return elements;
	}

	/**
	 * 元素是否显式显示
	 * 
	 * @author lijialong
	 */
	@Override
	public boolean isElementDisplayed(Locator locator) {
		boolean flag = false;
		WebElement element = getElement(locator);
		flag = element.isDisplayed();
		return flag;
	}

	/**
	 * 判断元素是否存在
	 * 
	 * @author lijialong
	 */
	@Override
	public boolean isElementExist(Locator locator) {
		boolean flag = false;
		if (getElement(locator) != null) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 智能等待，超过该时长抛出异常
	 * 
	 * @author lijialong
	 */
	@Override
	public void waitElement(Locator locator) throws Exception {
		//
	}

	/**
	 * 切换窗口
	 * 
	 * @author lijialong
	 */
	@Override
	public void switchWindow(String title) {
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (handle.equals(driver.getWindowHandle())) {
				continue;
			} else {
				driver.switchTo().window(handle);
				if (title.contains(driver.getTitle())) {
					break;
				} else {
					continue;
				}
			}
		}
	}

	/**
	 * 切换frame
	 * 
	 * @author lijialong
	 */
	@Override
	public void switchFrame(Locator locator) {
		driver.switchTo().frame(locator.getAddress());
	}

	/**
	 * 获取alert弹框内容并关闭弹框
	 * 
	 * @author lijialong
	 */
	@Override
	public String getAlertText() {
		String alertText = "";
		try {
			// webdriver对弹框的处理略坑，所以先等待3s再切换到弹框，否则可能无法切换
			sleep(2000);
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
			alert.accept();
		} catch (NoSuchElementException e) {
			log.info("no alert open,switch to alert failed");
		}
		return alertText;
	}

	/**
	 * 从多个元素中获取某个元素点击
	 * 
	 * @author lijialong
	 * @param index
	 *            元素下标
	 */
	@Override
	public void click(Locator locator, int index) {
		WebElement element = getElements(locator).get(index);
		element.click();
	}

	/**
	 * 从多个元素中获取某个元素输入
	 * 
	 * @author lijialong
	 * @param index
	 *            元素下标
	 */
	@Override
	public void sendKey(Locator locator, String value, int index) {
		WebElement element = getElements(locator).get(index);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * 从多个元素中获取某个元素获取该元素文本
	 * 
	 * @author lijialong
	 * @param index
	 *            元素下标
	 */
	@Override
	public String getText(Locator locator, int index) {
		return getElements(locator).get(index).getText();
	}
	
    /**
     * js执行器
     * @author lijialong
     * @param jsStr js代码块
     * */
	@Override
	public void jsExecutor(String jsStr) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(jsStr);
	}

	/**
	 * 获取层级元素列表，用于层级关系较复杂的元素定位
	 * 
	 * @author lijialong
	 * @param locator_indexs
	 *            locator与index键值对,locator为元素信息,index为要获取的元素下标,index=null,获取所有元素
	 *            ,默认为null;index=Integer,获取该下标元素,用于类似获取elements[1][2][all]的情况
	 * @throws Exception 
	 */
	@Override
	public List<WebElement> getLevelElement(Map<Locator, Integer> locator_indexs) throws Exception {
		List<WebElement> elements = new ArrayList<>();
		Locator locator = null;
		Integer index = null;
		for(Map.Entry<Locator, Integer> locator_index : locator_indexs.entrySet()){
			locator = locator_index.getKey();
			index = locator_index.getValue();
			
			String address = locator.getAddress();
			long waitTime = locator.getWaitSec();
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			log = new LogUtil(UIExecutorImpl.class);
			switch (locator.getByType()) {
			case xpath:
				try {
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(address)));
					//为空用driver获取元素，非空用elements用最后一个元素获取元素
					if(elements.isEmpty()){
						//为空获取所有元素，非空获取下标为index的元素
						if(index == null){
							elements.addAll(driver.findElements(By.xpath(address)));
						}else{
							elements.add(driver.findElements(By.xpath(address)).get(index));
						}
					}else{
						List<WebElement> elementsTemp = new ArrayList<>();
						elementsTemp.addAll(elements);
						elements.removeAll(elementsTemp);
						if(index == null){
							elements.addAll(elementsTemp.get(elementsTemp.size()-1).findElements(By.xpath(address)));
						}else{
							elements.add(elementsTemp.get(elementsTemp.size()-1).findElements(By.xpath(address)).get(index));
						}
					}
				} catch (Exception e) {
					log.info("findElements ByXpath:" + address + " failed,NoSuchElements");
				}
				break;
			case id:
				try {
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(address)));
					//为空用driver获取元素，非空用elements用最后一个元素获取元素
					if(elements.isEmpty()){
						//为空获取所有元素，非空获取下标为index的元素
						if(index == null){
							elements.addAll(driver.findElements(By.id(address)));
						}else{
							elements.add(driver.findElements(By.id(address)).get(index));
						}
					}else{
						List<WebElement> elementsTemp = new ArrayList<>();
						elementsTemp.addAll(elements);
						elements.removeAll(elementsTemp);
						if(index == null){
							elements.addAll(elementsTemp.get(elementsTemp.size()-1).findElements(By.id(address)));
						}else{
							elements.add(elementsTemp.get(elementsTemp.size()-1).findElements(By.id(address)).get(index));
						}
					}
				} catch (Exception e) {
					log.info("findElements ById:" + address + " failed,NoSuchElements");
				}
				break;
			case className:
				try {
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(address)));
					//为空用driver获取元素，非空用elements用最后一个元素获取元素
					if(elements.isEmpty()){
						//为空获取所有元素，非空获取下标为index的元素
						if(index == null){
							elements.addAll(driver.findElements(By.className(address)));
						}else{
							elements.add(driver.findElements(By.className(address)).get(index));
						}
					}else{
						List<WebElement> elementsTemp = new ArrayList<>();
						elementsTemp.addAll(elements);
						elements.removeAll(elementsTemp);
						if(index == null){
							elements.addAll(elementsTemp.get(elementsTemp.size()-1).findElements(By.className(address)));
						}else{
							elements.add(elementsTemp.get(elementsTemp.size()-1).findElements(By.className(address)).get(index));
						}
					}
				} catch (Exception e) {
					log.info("findElements ByClassName:" + address + " failed,NoSuchElements");
				}
				break;
			case linkText:
				try {
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(address)));
					//为空用driver获取元素，非空用elements用最后一个元素获取元素
					if(elements.isEmpty()){
						//为空获取所有元素，非空获取下标为index的元素
						if(index == null){
							elements.addAll(driver.findElements(By.linkText(address)));
						}else{
							elements.add(driver.findElements(By.linkText(address)).get(index));
						}
					}else{
						List<WebElement> elementsTemp = new ArrayList<>();
						elementsTemp.addAll(elements);
						elements.removeAll(elementsTemp);
						if(index == null){
							elements.addAll(elementsTemp.get(elementsTemp.size()-1).findElements(By.linkText(address)));
						}else{
							elements.add(elementsTemp.get(elementsTemp.size()-1).findElements(By.linkText(address)).get(index));
						}
					}
				} catch (Exception e) {
					log.info("findElements ByLinkText:" + address + " failed,NoSuchElements");
				}
				break;
			case tagName:
				try {
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(address)));
					//为空用driver获取元素，非空用elements用最后一个元素获取元素
					if(elements.isEmpty()){
						//为空获取所有元素，非空获取下标为index的元素
						if(index == null){
							elements.addAll(driver.findElements(By.tagName(address)));
						}else{
							elements.add(driver.findElements(By.tagName(address)).get(index));
						}
					}else{
						List<WebElement> elementsTemp = new ArrayList<>();
						elementsTemp.addAll(elements);
						elements.removeAll(elementsTemp);
						if(index == null){
							elements.addAll(elementsTemp.get(elementsTemp.size()-1).findElements(By.tagName(address)));
						}else{
							elements.add(elementsTemp.get(elementsTemp.size()-1).findElements(By.tagName(address)).get(index));
						}
					}
				} catch (Exception e) {
					log.info("findElements ByTagName:" + address + " failed,NoSuchElements");
				}
				break;
			default:
				break;
			}
		}
		return elements;
	}
    
	

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			//
		}
	}

	@Override
	public void appSwipeUp(int duration, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appSwipeDown(int duration, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appSwipeLeft(int duration, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appSwipeRight(int duration, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appTapByXY(int fingers, int x, int y, int duration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getToastText(String str, int sec) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveToElement(Locator locator) {
		// TODO Auto-generated method stub	
		WebElement element = getElement(locator);
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

}
