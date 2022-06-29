package Pages;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.base;

public class HotelPages extends base{
	
	public WebDriver driver ;
    public WebDriverWait wait;
	
	@FindBy(xpath="//*[@id='cookies-content']")
	private WebElement cookiesPopup;
	
	@FindBy(xpath="//*[@id='AcceptReload']")
	private WebElement acceptCookiesButton;
	
	@FindBy(xpath="//label[@class='tui-countries-popup__label'][contains(text(),'Brasil')]/..")
	private WebElement brasilcountrylink;
	
	@FindBy(xpath="//h3[@class='app-heading app-heading--h3']/following-sibling::search-start/div/div/div/div/div/input")
	private WebElement cityTextBox;
	
	@FindBy(xpath="(//span[@class='suggestion-dialog__list-item-text'])[1]")
	private WebElement firstItemIncityOptionList;
	
	@FindBy(xpath="(//div[@class='app-hero__search aos-init aos-animate']//span[@class='fake-form-input-button__text'])[1]")
	private WebElement dateSelectionBox;
	
	@FindBy(xpath="(//time[@class='calendar-month__day-label'][normalize-space()='26'])[1]")
	private WebElement fromDate;
	
	@FindBy(xpath="(//div[@class='app-hero__search aos-init aos-animate']//span[@class='fake-form-input-button__text'])[1]")
	private WebElement toDate;
	
	@FindBy(xpath="//span[normalize-space()='Aplicar']")
	private WebElement applyButton;
	
	@FindBy(xpath="//div[@class='app-hero__search aos-init aos-animate']//div[@class='guest-selection search-start__guests']//div[@class='fake-form-input-button__content']")
	private WebElement personTextBox;
	
	@FindBy(xpath="//form/div[1]/fieldset[1]/div/div/button[2]")
	private WebElement plusButton;
	
	@FindBy(xpath="//*[@id=\"__layout\"]/div/main/section[1]/div[1]/div/div[2]/div/search-start/div/div/a")
	private WebElement searchButton;
	
	@FindBy(xpath="//search-result-list-header[@class='srp__search-result-list-header']")
	private WebElement searchResultsHeader;
	
	@FindBy(xpath="//article[@class='offer-tile search-result-list__item']")
	private WebElement searchResultsList;
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Escolha'])[1]")
	private WebElement firsthotelSelectButton;
	
	@FindBy(xpath="//div[@class='offers-found__room']")
	private WebElement listOfOffers;
	
	
	
	
	public HotelPages(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,20);
	}
	
	public void clickOnAcceptCookies() throws InterruptedException {
		//wait for cookies popup to displayed - timeout 10 seconds
		wait.until(ExpectedConditions.visibilityOf(cookiesPopup));
		if(cookiesPopup.isDisplayed()) {
		  acceptCookiesButton.click();
		}
	}
	
	public void clickOnCountry(String Country) throws InterruptedException {
		//wait for country links to be clickable before clicking
		wait.until(ExpectedConditions.elementToBeClickable(brasilcountrylink));
		driver.findElement(By.xpath("//label[@class='tui-countries-popup__label'][contains(text(),'"+Country+"')]/..")).click();

	}

	 public void enterCityName(String city) throws InterruptedException {
		 //wait for city text box to be clickable
		 Thread.sleep(2000);		 
		 wait.until(ExpectedConditions.elementToBeClickable(cityTextBox));
	
		 //Using Actions class to click inside - .click is not working
			 Actions actions = new Actions(driver);
			 actions.moveToElement(cityTextBox).click().build().perform();
		 
		 Thread.sleep(2000);	
		 cityTextBox.sendKeys(city);
		//wait for the suggestion list to display city name
		 wait.until(ExpectedConditions.elementToBeClickable(firstItemIncityOptionList));
		 firstItemIncityOptionList.click();
	}

	public void selectDates(String fromDate, String toDate) throws InterruptedException {
		//wait for date selection box to be clickable
		 wait.until(ExpectedConditions.elementToBeClickable(dateSelectionBox));
		 dateSelectionBox.click();
		 
		 //select from date
		 Thread.sleep(2000);
		 driver.findElement(By.id("date-"+fromDate+"")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.id("date-"+toDate+"")).click();
		 
		//wait for apply button to be clickable and then click it
		 wait.until(ExpectedConditions.elementToBeClickable(applyButton));	
		 applyButton.click();
		
	}

	public void selectNumberOfGuests() {
		
		
		//click number of persons text box
		wait.until(ExpectedConditions.elementToBeClickable(personTextBox));
		personTextBox.click();	

        //Set number of persons to 3 by clicking on + button		
		wait.until(ExpectedConditions.elementToBeClickable(plusButton));
		plusButton.click();	
		
		//wait for apply button to be clickable and then click it
		 wait.until(ExpectedConditions.elementToBeClickable(applyButton));	
		 applyButton.click();
						
		
		
	}

	public void clickOnSearchButton() {
		//wait for search button to be clickable and then click it
				wait.until(ExpectedConditions.elementToBeClickable(searchButton));
				searchButton.click();
		
	}

	public void verifySearchResults() {
		//wait for search results header to display
		wait.until(ExpectedConditions.visibilityOf(searchResultsHeader));
		
		//Assert that search results header is displayed
		Assert.assertTrue(searchResultsHeader.isDisplayed());
		
		
		//wait for search results items to display
		wait.until(ExpectedConditions.visibilityOf(searchResultsList));
		
		
		
		
	}

	public void verifyAtLeastOneSearchResultDisplayed() {
		List<WebElement> listName = driver.findElements(By.xpath("//article[@class='offer-tile search-result-list__item']"));
		//System.out.println("Number of search results displayed "+listName.size());			
		//verify there are more than or euqal to 1 search results.
		Assert.assertTrue(listName.size()>=1);
		
	}

	public void clickOnHotelFromSearchResults() {
		
		//wait for select button to display
		wait.until(ExpectedConditions.visibilityOf(firsthotelSelectButton));
		firsthotelSelectButton.click();
		
		
	}

	public void verifySiteRedirectsToOfferDetailsInNewTab() {
		
		//get window handles to get the open tabs in a set
        Set<String> windowhandles = driver.getWindowHandles();
		//iterating through the set of window handles
		Iterator<String> itr = windowhandles.iterator();
		
		while(itr.hasNext()) {
			String element3 = itr.next();
			driver.switchTo().window(element3);
			//breaking out of loop if correct page which contains details/goal in URL is there
			if(driver.getCurrentUrl().contains("detail/goal")) {
				break;
			} 
		}
		 
		//wait for list of offers to be present on this page
	    wait.until(ExpectedConditions.visibilityOf(listOfOffers));
	    Assert.assertTrue(listOfOffers.isDisplayed());
	    
		
	}

	public void veriftAtLeastOneHotelOfferIsDisplayed() {
		List<WebElement> listOfOffers = driver.findElements(By.xpath("//div[@class='offers-found__room']"));
		Assert.assertTrue(listOfOffers.size()>=1);
	}
	
}
