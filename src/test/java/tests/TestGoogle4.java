package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestGoogle4 {
    //VARIABLES
    private WebDriver driver;
    private static final String DRIVER_TYPE = "webdriver.chrome.driver";
    private static final String PATH_DRIVER = "src/test/resources/webDriver/chromedriver.exe";
    private String URL = "https://www.nordcurrent.com/";

    private String URL2 = "https://www.nordcurrent.com/jobs";

    private String URL3 = "https://support-forms.nordcurrent.com/";

    @BeforeClass //what will be executed before our class
    public static void setUpBeforeClass() throws Exception {
        System.out.println("Test Start");
        System.setProperty(DRIVER_TYPE, PATH_DRIVER);
    }

    @Before //to be executed before tests
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }


    /**
     * Given URL
     * Then search element is enabled
     */
    @Test
    public void testSearchBoxExists() throws Exception {
        driver.get(URL);
        WebElement lens = driver.findElement(By.xpath("//*[@id=\"search-toggle\"]/i"));

        Assert.assertEquals(true, lens.isEnabled());
    }

    /**
     * Given URL
     * When we click on the search button
     * Then search box will appear
     */
    @Test
    public void testLensIconIsClicked() throws Exception {
        driver.get(URL);
        WebElement lens2 = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        Assert.assertEquals(true, lens2.isEnabled());
    }

    /**
     * Given URL
     * And the string Video games
     * When searching for the string
     * Then receive a result about the string searched
     */
    @Test
    public void testSearchSomething() throws Exception {
        String textToSearch = "Video games";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/h3")).getText();
        Assert.assertEquals("Your results for '" + textToSearch + "':", textResulted);


    }

    /**
     * Given URL
     * And the string Buscar that is Spanish word
     * When searching for the string
     * Then receive a result about the string searched
     */
    //Test to search in another language and get other language results
    @Test
    public void testSearchSpanish() throws Exception {
        String textToSearch = "Buscar";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/ul/li[1]/a")).getText();
        Assert.assertEquals("Español", textResulted);


    }

    /**
     * Given URL
     * And the string AvAvAoPPa
     * When searching for the string
     * Then receive a result taking into account the capital letters
     */
    @Test
    public void testSearchWithCaps() throws Exception {
        String textToSearch = "AvAvAoPPa";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/h3")).getText();
        Assert.assertEquals("No results found for '" + textToSearch + "'", textResulted);
    }

    /**
     * Given URL
     * And the string algo
     * When searching for the string
     * Then receive a results according to the string
     */
    @Test
    public void testSearchResults() throws Exception {
        String textToSearch = "algo";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        WebElement resultFound = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div"));
        Assert.assertEquals(true, resultFound.isDisplayed());
    }

    /**
     * Given URL
     * And the string sniper game
     * And the string SNIPER GAME
     * When searching for the strings
     * Then receive same result for both strings
     */
    @Test
    public void testSameStringDifferentLettersResults() throws Exception {
        String textToSearch = "sniper game";
        String textToSearch2 = "SNIPER GAME";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted1 = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/ul/li/a")).getText();
        WebElement lupa3 = driver.findElement(By.xpath("//*[@id=\"support-form\"]/div/div/div/div/input"));
        lupa3.click();
        lupa3.clear();
        lupa3.sendKeys(textToSearch2);
        lupa3.submit();
        String textResulted2 = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/ul/li/a")).getText();
        Assert.assertEquals(textResulted2, textResulted1);

    }

    /**
     * Given URL
     * And two strings meaning the same in different languages
     * When searching for the strings
     * Then receive same results for both searches
     */
    @Test
    public void testSameStringDifferentLanguage() throws Exception {
        String textToSearch = "sniper game";
        String textToSearch2 = "juego francotirador";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted1 = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/ul/li/a")).getText();
        WebElement search2 = driver.findElement(By.xpath("//*[@id=\"support-form\"]/div/div/div/div/input"));
        search2.click();
        search2.clear();
        search2.sendKeys(textToSearch2);
        search2.submit();
        //Is going to fail because it does give the same outcome after submitting - it throws a different result
        String textResulted2 = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/ul/li/a")).getText();
        Assert.assertEquals(textResulted2, textResulted1);


    }

    //Negative TEST CASES

    /**
     * Given URL
     * And the string emtpy
     * When searching for the string
     * Then receive validation error
     */
    @Test
    public void testSearchNothing() throws Exception {
        String textToSearch = "";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/h3")).getText();
        //Assertion is going to fail - I consider this a bug. Some message should appear.
        Assert.assertEquals("No results found for '" + textToSearch + "'", textResulted);


    }

    /**
     * Given URL
     * And the string " "
     * When searching for the string
     * Then receive a validation saying that no result was found
     */
    @Test
    public void testSearchBlankSpace() throws Exception {
        String textToSearch = " ";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/h3")).getText();
        Assert.assertEquals("No results found for '" + textToSearch + "'", textResulted);


    }

    /**
     * Given URL
     * And the string !@#$@%#^#&*
     * When searching for the string
     * Then receive validation error mentioning the string used
     */
    @Test
    public void testSearchSymbols() throws Exception {
        String textToSearch = "!@#$@%#^#&*";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/h3")).getText();
        Assert.assertEquals("No results found for '" + textToSearch + "'", textResulted);


    }

    /**
     * Given URL
     * And the string with a term that does not exist in our database
     * When searching for the string
     * Then receive validation error mentioning the string used is not found in our database
     */
    @Test
    public void testNotExistentTermInDataBase() throws Exception {
        String textToSearch = "Age of Empires II" +
                " - Conquerors' Expansion";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/h3")).getText();
        Assert.assertEquals("No results found for '" + textToSearch + "'", textResulted);


    }

    /**
     * Given URL
     * When clicking straight away in the search button
     * Then receive validation error mentioning No results were found
     */

    //I CONSIDER THAT THERE IS A BUG AFTER RUNNING THE FOLLOWING TEST CASE
    @Test
    public void testClickingWithoutMessage() throws Exception {

        driver.get(URL2);
        clickSearchToggleElement();
        driver.findElement(By.xpath("//*[@id=\"global-search\"]/div/form/div/div/div/div/button/i")).click();
        String textResulted = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/h3")).getText();
        //Assertion is going to fail - I consider this a bug. Some message should appear.
        Assert.assertEquals("No results found for ''", textResulted);


    }

    /**
     * Given URL
     * And the string is too long
     * When searching for the string
     * Then receive validation error mentioning the string used is too long
     */
    @Test
    public void testTooLongString() throws Exception {
        String textToSearch = "1234567890abcdewrwqadsfafassndgmsangsagsagfsa12" +
                "34567890adfawfasfsafsadfasdfas1234123456789012345678901234567890123456789012345" +
                "67890aafasfdfsafds12345678901234567890123456789012345678901234567890df,sanfasnf,asdfasdf" +
                "nsa12345678901234567890123456789012345678901234567890nfakfns,dfnsfnaf";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/h3")).getText();
        //Is not going to pass the test. It cuts the string until the limit allowed. An error message should appear
        Assert.assertEquals("The number of entered characters exceeded the limit allowed", textResulted);


    }


    /**
     * Given URL
     * And the string is too short
     * When searching for the string
     * Then receive validation error mentioning the string used is too short
     */
    @Test
    public void testShortMessage() throws Exception {
        String textToSearch = "a";
        driver.get(URL2);
        WebElement search = driver.findElement(By.xpath("//*[@id=\"global-search-input\"]"));
        clickSearchToggleElement();
        clickGlobalSearchInputElement();
        search.sendKeys(textToSearch);
        search.submit();
        String textResulted = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div[3]/div/div/h3")).getText();
        //Some error should appear, therefore is not passing the Assertion
        Assert.assertEquals("Not enough characters", textResulted);


    }

    //Featured position test case

    /**
     * Given the URL
     * When city Vilnius is selected in the dropdown list
     * Then Only jobs that are located in Vilnius will appear
     */
    @Test
    public void testFeaturedJobInVilnius() throws Exception {
        driver.get(URL2);
        WebElement featured = driver.findElement(By.className("select2-selection__arrow"));
        featured.click();
        WebElement vilniusSelection = driver.findElement(By.xpath("//*[@id=\"career-filters\"]/div/div[1]/select/option[3]"));
        vilniusSelection.click();
        String cityExpected = "Vilnius";
        List<WebElement> elements = driver.findElements(By.className("career-item-wrap-v2"));
        for (WebElement element : elements) {
            System.out.println(element.getText());
            Assert.assertTrue(element.getText().contains(cityExpected));
        }

    }

    //Testcommit

    @Test
    public void assertSomething(){
        int n1 = 1;
        int n2 = 2;

        Assert.assertEquals(3,n1+n2);
    }

    //Automation of ticket creation for support

    /**
     * Given URL
     * When inputting values in the fields for email, body and attachment
     * Then send them correctly
     */
    @Test
    public void testCreateTicket() throws Exception {
        driver.get(URL3);
        String emailAddress = "test@email.com";
        String messageContent = "Sorry to disturb you, this is a test for getting a job";
        driver.findElement(By.id("email")).sendKeys(emailAddress);
        driver.findElement(By.id("body")).sendKeys(messageContent);
        driver.findElement(By.className("filepond--browser"))
                .sendKeys("C:\\Users\\piglesias\\IdeaProjects\\SeleniumProject\\src\\test\\resources\\webDriver\\sev.jpg");
        WebElement submitButton = driver.findElement(By.id("submit-form-btn"));
        submitButton.click();
        String messageReceived = "Your message has been sent successfully";
        String afterSubmittingMessage = driver.findElement(By.xpath("//*[@id=\"support-form\"]/h3[1]")).getText();
        Assert.assertEquals(messageReceived, afterSubmittingMessage);

    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("Tests finalized");
    }

    private void clickSearchToggleElement() {
        driver.findElement(By.xpath("//*[@id=\"search-toggle\"]/i")).click();
    }

    private void clickGlobalSearchInputElement() {
        driver.findElement(By.xpath("//*[@id=\"global-search-input\"]")).click();

    }

}
