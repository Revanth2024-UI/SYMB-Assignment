package org.symb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.symb.baseclass.BaseClass;

public class SeleniumTask extends BaseClass {
	@BeforeMethod
	public void browserLaunchh() throws FileNotFoundException, IOException {
		browserLaunch();
		launchtheappln(getPropertyValue("url"));
		maximizewindow();
		implicitWait();
	}

	@Test(priority = 0)
	public void TC001() {
		// "Verify that the text input element with xpath .//input[@name='my-disabled']
		// is disabled in the form"
		WebElement textDisabledInput = locatorByXpath("//label//input[@name='my-disabled']");
		boolean isenabled = isenabled(textDisabledInput);
		Assert.assertFalse(isenabled, "Verify that text box is diabled");

	}

	@Test(priority = 1)
	public void TC002() {
		// "Verify that the text input with value 'Readonly input' is in readonly state
		// by using 2 xpaths"
		// xpath-1
		String ReadOnly1 = elementGetAttribute(locatorByXpath("//label[contains(text(),'Readonly')]//input"), "value");
		Assert.assertEquals(ReadOnly1, "Readonly input", "Verify that text box is Read Only");
		// xpath-2
		String ReadOnly2 = elementGetAttribute(locatorByXpath("//input[@value='Readonly input']"), "value");
		Assert.assertEquals(ReadOnly2, "Readonly input", "Verify that text box is Read Only");

	}

	@Test(priority = 2)
	public void TC003() {
		// "Verify that the dropdown field to select color is having 8 elements using 2
		// xpaths"
		// xpath-1
		Select select = new Select(locatorByXpath("//select[@class='form-select']"));
		List<WebElement> options = select.getOptions();
		int size = options.size();
		Assert.assertEquals(size, 8, "Verify the Select color has 8 elements");
		// xpath-2
		Select select2 = new Select(locatorByXpath("//label[contains(text(),'Select Color')]//select"));
		List<WebElement> options2 = select2.getOptions();
		int size2 = options2.size();
		Assert.assertEquals(size2, 8, "Verify the Select color has 8 elements");
	}

	@Test(priority = 3)
	public void TC004() {

		// "Verify that the submit button is disabled when no data is entered in Name
		// field"
		WebElement btnSubmit = locatorbyid("submit-form");
		if (!btnSubmit.isEnabled()) {
			System.out.println("Button is disabled when the fields are empty");
		} else {
			System.out.println("Button is not disabled when the fields are empty");
		}

	}

	@Test(priority = 4)
	public void TC005() {
		// "Verify that the submit button enabled when both Name and Password field is
		// entered"
		WebElement txtName = locatorbyid("my-name-id");
		WebElement txtPassword = locatorbyid("my-password-id");
		WebElement btnSubmit = locatorbyid("submit-form");
		elementSendKeys(txtName, "Revanth");
		elementSendKeys(txtPassword, "Revanth@123");
		if (btnSubmit.isEnabled()) {
			System.out.println("Button is enabled when the fields are entered");
		} else {
			System.out.println("Button is not enabled when the fields are entered");
		}
	}

	@Test(priority = 5)
	public void TC006() {
		// "Verify that on submit of 'Submit' button the page shows 'Received' text"
		WebElement txtName = locatorbyid("my-name-id");
		elementSendKeys(txtName, "Revanth");
		WebElement txtPassword = locatorbyid("my-password-id");
		elementSendKeys(txtPassword, "Revanth@123");
		WebElement btnSubmit = locatorbyid("submit-form");
		elementClick(btnSubmit);
		String actualText = elementGetText(locatorByXpath("//p[@id='message']"));
		Assert.assertTrue(actualText.contains("Received"),
				"Verify that on submit of 'Submit' buuton the page shows 'Received'");
	}

	@Test(priority = 6)
	public void TC007() {
		// "Verify that on submit of form all the data passed to the UR"
		WebElement txtName = locatorbyid("my-name-id");
		elementSendKeys(txtName, "Revanth");
		WebElement txtPassword = locatorbyid("my-password-id");
		elementSendKeys(txtPassword, "Revanth@123");
		WebElement btnSubmit = locatorbyid("submit-form");
		elementClick(btnSubmit);
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
		Assert.assertTrue(currentUrl.contains("Revanth"));
	}

}
