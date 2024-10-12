package com.practiceWeb_package;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SeleniumGridDemo {
	
	@Test
	public void test() throws MalformedURLException
	{
	    Reporter.log("class-1 "+Thread.currentThread().getId(), true);
	    URL url=new URL("http://192.168.0.121:4545/wd/hub");
	    DesiredCapabilities cap=new DesiredCapabilities();
	    cap.setBrowserName("chrome");
	    cap.setPlatform(Platform.WINDOWS);
	    RemoteWebDriver driver=new RemoteWebDriver(url, cap);
	    driver.get("https://rmgtestingserver/domain/");
	}

}
