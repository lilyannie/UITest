package com.auto.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	private WebDriver driver;

	public ScreenShot(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 保存截图
	 * 
	 * @param path
	 *            截图保存路径
	 * @param shotName
	 *            图片命名
	 *            
	 * @author lijialong
	 */
	public void saveScreenShot(String path, String shotName) {
		LogUtil log = new LogUtil(ScreenShot.class);
		//TakesScreenshot接口是依赖于具体的浏览器API操作的，所以在HTMLUnit Driver中并不支持该操作。
		System.setProperty("org.uncommons.reportng.escape-output", "false"); 
		TakesScreenshot tScreenshot = (TakesScreenshot)driver;
		// 截图
		File photo = tScreenshot.getScreenshotAs(OutputType.FILE);
		File shotFile = new File(path+shotName);
		try {
			// 将截图复制到指定目录
			FileUtils.copyFile(photo, shotFile);
		} catch (IOException e) {
			log.error(getClass() + " 保存截图失败");
			e.printStackTrace();
		}
	}
}
