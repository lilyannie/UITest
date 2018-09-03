package com.auto.utils;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.auto.object.Locator;

/**
 * webDriver常见的API
 * 
 * @author lijialong
 */
public interface UIExecutor {
	// 点击
	public void click(Locator locator);

	// 从多个元素中获取某个元素点击
	public void click(Locator locator, int index);

	// 输入文本
	public void sendKey(Locator locator, String value);
	
	//鼠标悬停
	public void moveToElement(Locator locator);
	
	// 从多个元素中获取某个元素输入文本
	public void sendKey(Locator locator, String value, int index);

	// 获取元素文本
	public String getText(Locator locator);

	// 从多个元素中获取某个元素的文本内容
	public String getText(Locator locator, int index);

	// 获取元素
	public WebElement getElement(Locator locator) throws Exception;

	// 获取元素列表
	public List<WebElement> getElements(Locator locator);

	// 获取层级元素列表
	public List<WebElement> getLevelElement(Map<Locator, Integer> locator_indexs) throws Exception;

	// 判断元素是否显示
	public boolean isElementDisplayed(Locator locator);

	// 判断元素是否存在
	public boolean isElementExist(Locator locator);

	// 切换页面
	public void switchWindow(String title);

	// 切换frame
	public void switchFrame(Locator locator);

	// 智能等待
	public void waitElement(Locator locator) throws Exception;

	// 获取alert弹框内容并关闭弹框
	public String getAlertText();

	// js执行器
	public void jsExecutor(String js);

	// app上滑
	public void appSwipeUp(int duration, int num);

	// app下滑
	public void appSwipeDown(int duration, int num);

	// app左滑
	public void appSwipeLeft(int duration, int num);

	// app右滑
	public void appSwipeRight(int duration, int num);

	// app通过坐标点击
	public void appTapByXY(int fingers, int x, int y, int duration);
	
	//获取toast浮层文本内容
	public boolean getToastText(String str,int sec);

}
