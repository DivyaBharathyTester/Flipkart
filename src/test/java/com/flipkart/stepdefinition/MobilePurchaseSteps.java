package com.flipkart.stepdefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MobilePurchaseSteps {
	
	static WebDriver driver;
	
	@Given("User launches flipkart application")
	public void user_launches_flipkart_application() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("User login using credentials")
	public void user_login_using_credentials() {
		try {
			WebElement close = driver.findElement(By.xpath("//button[contains(text(),'✕')]"));
			close.isDisplayed();
			close.click();
		} catch (Exception e) {
			System.out.println("No popup is displayed");
		}
	}

	@When("Navigates into the page and choses the mobile")
	public void navigates_into_the_page_and_choses_the_mobile() throws InterruptedException, AWTException {
		WebElement text = driver.findElement(By.xpath("//input[@type='text']"));
		text.sendKeys("realme",Keys.ENTER);
	}

	@When("Proceed to payment")
	public void proceed_to_payment() throws InterruptedException {
		Actions a = new Actions(driver);
		WebElement realme = driver.findElement(By.xpath("(//div[contains(text(),'realme C21Y')])[1]"));
		a.click(realme);
		Thread.sleep(2000);
		
		String parentUrl = driver.getWindowHandle();
		Set<String> childUrl = driver.getWindowHandles();
		for (String x : childUrl) {
			if (!(parentUrl.equals(childUrl))) {
				driver.switchTo().window(x);
			}			
		}
		WebElement atc = driver.findElement(By.xpath("//button[contains(text(),'ADD TO CART')]"));
		atc.click();
	}

	@Then("Message to be displayed as Order has been placed.")
	public void message_to_be_displayed_as_Order_has_been_placed() {
		System.out.println("Your order has been confirmed");
	
	}

}
