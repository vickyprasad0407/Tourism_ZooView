package com.practiceWeb_package;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowRoot {
	
	@Test
	public void testShadowRoot()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("chrome://downloads/");
		driver.manage().window().maximize();
	    JavascriptExecutor js=(JavascriptExecutor)driver;
	    //js.executeScript("document.querySelector('downloads-manager').shadowRoot.querySelector('downloads-toolbar').shadowRoot.querySelector('cr-toolbar').shadowRoot.querySelector('cr-toolbar-search-field').shadowRoot.querySelector('#searchInput').value='sqldevloper'");
	    WebElement wb=(WebElement)js.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('downloads-toolbar').shadowRoot.querySelector('cr-toolbar').shadowRoot.querySelector('cr-toolbar-search-field').shadowRoot.querySelector('#searchInput')");
	    wb.sendKeys("Tyss_rmg");
	}
}
