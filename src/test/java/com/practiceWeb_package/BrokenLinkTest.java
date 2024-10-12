// to verify wheather the link is broken or not
package com.practiceWeb_package;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinkTest {

	@Test
	public void testLink()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rmgtestingserver/domain/");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.linkText("Proceed to rmgtestingserver (unsafe)")).click();
		List<WebElement> linkList = driver.findElements(By.xpath("//a"));
		List<String> bLink=new ArrayList<String>();
		for (WebElement brokenlink : linkList) {
			String link = brokenlink.getAttribute("href");
			URL url;
			int statusCode=0;
			try
			{
				url=new URL(link);
				URLConnection urlConn = url.openConnection();
				HttpURLConnection httpUrlConnection=(HttpURLConnection)urlConn;
				statusCode=httpUrlConnection.getResponseCode();
				String statusMessage = httpUrlConnection.getResponseMessage();
				if(statusCode>=400)
				{
					bLink.add(link+" ------> "+statusCode+" ---->"+statusMessage);
				}
			}
			catch(Exception e)
			{
				bLink.add(link);
				continue;
			}
		}
		for(String string:bLink)
		{
		System.out.println(string);
		}
		driver.close();
	}
}
