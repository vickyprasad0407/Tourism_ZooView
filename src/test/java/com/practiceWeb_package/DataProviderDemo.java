package com.practiceWeb_package;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProviderDemo {
	
	@DataProvider
	public Object [][] data()
	{
		Object [][] ob=new Object[3][2];
		ob [0][0]="admin1";
		ob [0][1]="manager1";
		ob [1][0]="admin2";
		ob [1][1]="manager2";
		ob [2][0]="admin";
		ob [2][1]="manager";
		return ob;
	}
	
	@Test(dataProvider = "data")
	public void login(String un,String pwd)
	{
	    WebDriverManager.chromedriver().setup();
	    WebDriver driver=new ChromeDriver();
		driver.get("https://demo.actitime.com/login.do");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(un);
		driver.findElement(By.name("pwd")).sendKeys(pwd);
		driver.findElement(By.id("loginButton")).click();
	}

}
