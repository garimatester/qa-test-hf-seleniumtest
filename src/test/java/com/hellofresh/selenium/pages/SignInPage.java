package com.hellofresh.selenium.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hellofresh.data.common.RandomDataGenerator;
import com.hellofresh.selenium.base.BaseSeleniumWebPageAction;





public class SignInPage extends BaseSeleniumWebPageAction{



	private By email_create=By.id("email_create");
	private By SubmitCreate=By.id("SubmitCreate");

	private By id_gender2=By.id("id_gender2");
	private By login=By.className("login");

	private By customer_firstname=By.id("customer_firstname");
	private By customer_lastname=By.id("customer_lastname");

	private By passwd=By.id("passwd");

	private By days=By.id("days");

	private By months=By.id("months");

	private By years=By.id("years");

	private By company=By.id("company");

	private By address1=By.id("address1");

	private By address2=By.id("address2");
	private By city=By.id("city");

	private By id_state=By.id("id_state");

	private By postcode=By.id("postcode");

	private By other=By.id("other");

	private By phone=By.id("phone");

	private By phone_mobile=By.id("phone_mobile");

	private By alias=By.id("alias");

	private By submitAccount=By.id("submitAccount");


	protected String xpath;
	protected  WebDriverWait wait;
	protected WebDriver driver;

	public SignInPage(final WebDriver driver, final WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		this.wait = wait;
	}






	/**
	 * Close browser.
	 * @throws InterruptedException 
	 *
	 * @throws Exception the exception
	 */
	/*protected void closeBrowser() throws Exception {
		log.info("Close browser");
		driver.close();
	}

	 */


	public String fillRegistrationData(RandomDataGenerator data) throws InterruptedException {



		wait.until(ExpectedConditions.visibilityOfElementLocated(login));
		click(login);

		String email = data.getEmail();
		String name = data.getName();
		String surname = data.getSurname();
		String strcity = data.getStrcity();
		String strcompany = data.getStrcompany();
		String straddress1 = data.getStraddress1();
		String straddress2 = data.getStraddress2();
		String strpostcode = data.getStrpostcode();
		String strother = data.getStrother();
		String strphone = data.getStrphone();
		String strphonemobile = data.getStrphonemobile();
		String stralias = data.getStralias();



		WebElement elementEmail=driver.findElement(email_create);
		enterValueToInputField("email", elementEmail, email, false);
		click(SubmitCreate);
		click(id_gender2);
		WebElement elementCustomerFirstname=driver.findElement(customer_firstname);
		enterValueToInputField("customerfirstname", elementCustomerFirstname, name, false);
		WebElement elementCustomerLaststname=driver.findElement(customer_lastname);
		enterValueToInputField("customerlastname", elementCustomerLaststname, surname, false);
		WebElement elementPasswd=driver.findElement(passwd);
		enterValueToInputField("Passwd", elementPasswd, "Qwerty", false);
		selectInDropDownMenuByValue(driver, days, "1");
		selectInDropDownMenuByValue(driver, months, "1");
		selectInDropDownMenuByValue(driver, years, "2000");

		WebElement elementCompany=driver.findElement(company);
		enterValueToInputField("company", elementCompany,strcompany, false);
		WebElement elementAddress1=driver.findElement(address1);
		enterValueToInputField("address1", elementAddress1,straddress1,false);

		WebElement elementAddress2=driver.findElement(address2);
		enterValueToInputField("address2", elementAddress2,straddress2,false);

		WebElement elementcity=driver.findElement(city);
		enterValueToInputField("city", elementcity,strcity,false);

		selectInDropDownMenu(driver, id_state, "Colorado");



		WebElement elementpostcode=driver.findElement(postcode);
		enterValueToInputField("postcode", elementpostcode,strpostcode,false);

		WebElement elementother=driver.findElement(other);
		enterValueToInputField("other", elementother,strother,false);

		WebElement elementphone=driver.findElement(phone);
		enterValueToInputField("phone", elementphone,strphone,false);

		WebElement elementphonemobile=driver.findElement(phone_mobile);
		enterValueToInputField("phone_mobile", elementphonemobile,strphonemobile,false);


		WebElement elementalias=driver.findElement(alias);
		enterValueToInputField("alias", elementalias,stralias,false);



		click(submitAccount);
		
		return name + " " + surname;






	}


}












