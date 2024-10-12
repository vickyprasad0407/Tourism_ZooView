package com.practiceWeb_package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MMTFlight {
   
	@Test
	public void test() throws InterruptedException, EncryptedDocumentException, IOException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[@class='ic_circularclose_grey']")).click();
		
		driver.findElement(By.id("fromCity")).sendKeys("bengaluru");
		driver.findElement(By.xpath("//p[.='Bengaluru, India']")).click();
		
		driver.findElement(By.id("toCity")).sendKeys("mumbai");
		driver.findElement(By.xpath("//p[.='Mumbai, India']")).click();
		
		//driver.findElement(By.xpath("//input[@id='departure']")).click();
		//div[@aria-label='Sat Apr 01 2023']
		driver.findElement(By.xpath("//div[@aria-label='Sat Apr 01 2023']")).click();
		driver.findElement(By.xpath("//a[.='Search']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='bgProperties icon20 overlayCrossIcon']")).click();
		driver.findElement(By.xpath("//span[.='+ 8 more']")).click();
		for(int i=1;i<=10;i++)
		{
			try {
			List<WebElement> list = driver.findElements(By.xpath("(//div[@class='makeFlex spaceBetween appendBottom12']/..)[1]//p[contains(.,'("+i+")')]"));
			for (int j=0;j<list.size();j++) {
				list.get(j).click();
			}
			}
			catch (NoSuchElementException e) {
				
			}
		}
		List<WebElement> jetName = driver.findElements(By.xpath("//div[@class='makeFlex align-items-center gap-x-10 airline-info-wrapper']/div/p[1]"));
	    List<WebElement> jetNo = driver.findElements(By.xpath("//div[@class='makeFlex align-items-center gap-x-10 airline-info-wrapper']/div/p[2]"));
	    List<WebElement> arrAndDept = driver.findElements(By.xpath("//p[@class='appendBottom2 flightTimeInfo']/span"));
	    List<WebElement> price = driver.findElements(By.xpath("//p[@class='blackText fontSize18 blackFont white-space-no-wrap']"));
	   
	    FileInputStream fis=new FileInputStream("./src/test/resources/Excel.xlsx");
	    Workbook wb=WorkbookFactory.create(fis);
	    System.out.println(jetName.size());
		for (int i=0;i<jetName.size();i++)
		{
			 wb.getSheet("sheet1").createRow(i).createCell(0).setCellValue(jetName.get(i).getText());
			 wb.getSheet("sheet1").getRow(i).createCell(1).setCellValue(jetNo.get(i).getText());
			 wb.getSheet("sheet1").getRow(i).createCell(4).setCellValue(price.get(i).getText());
			 
		}
		int x=1;
	    for (int i = 0; i < jetName.size(); i++) {
		  for(int j=2;j<4;j++)
		  {
			  wb.getSheet("sheet1").getRow(i).createCell(j).setCellValue(arrAndDept.get(x).getText());
			  x++;
		  }
	}
	    FileOutputStream fos=new FileOutputStream("./src/test/resources/Excel.xlsx");
		  wb.write(fos);
	}
	}

