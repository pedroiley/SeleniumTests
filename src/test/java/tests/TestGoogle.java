package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestGoogle {
    //VARIABLES
    private WebDriver driver;
    private static final String DRIVER_TYPE = "webdriver.chrome.driver";
    private static final String PATH_DRIVER = "src/test/resources/webDriver/chromedriver.exe";
    private String URL = "https://www.nordcurrent.com/";

    private String URL2 = "https://www.nordcurrent.com/jobs";

    @BeforeClass //what will be executed before our class
    public static void setUpBeforeClass() throws Exception
    {
        System.out.println("Test Start");
        System.setProperty(DRIVER_TYPE, PATH_DRIVER);
    }

    @Before //to be executed before tests
    public void setUp() throws Exception
    {
        driver = new ChromeDriver();
        //driver.get(URL);
    }

    @Test
    public void testSearchBoxExists() throws Exception
    {
        driver.get(URL2);
        WebElement lupa = driver.findElement(By.xpath("//*[@id=\"search-toggle\"]/i"));

        Assert.assertEquals(true,lupa.isEnabled());
        //driver.findElement(By.xpath("//*[@id=\"search-toggle\"]/i")).click();
        //driver.findElement(By.xpath("//*[@id=\"global-search-input\"]")).click();



       /* String sentText = "TEST01";
        //tHIS IS GOOGLE SEARCH BOX
        WebElement textSearch = driver.findElement(By.name("q"));
        textSearch.sendKeys(sentText);
        textSearch.submit();
        //Wait till google responds
        Thread.sleep(3000);
        //Validate if the title has the sentText
        String title = driver.getTitle();
        Assert.assertTrue("Validate title",title.contains(sentText));
        // isEnabled verify if is habilitado
        //isSelected verify if is selected*/
    }

    @Test
    public void testSearch() throws Exception
    {
        driver.get(URL2);
        driver.findElement(By.xpath("//*[@id=\"W0wltc\"]")).click();

        String sentText = "TEST01";
        //tHIS IS GOOGLE SEARCH BOX
        WebElement textSearch = driver.findElement(By.name("q"));
        textSearch.sendKeys(sentText);
        textSearch.submit();
        //Wait till google responds
        Thread.sleep(3000);
        //Validate if the title has the sentText
        String title = driver.getTitle();
        Assert.assertTrue("Validate title",title.contains(sentText));
        // isEnabled verify if is habilitado
        //isSelected verify if is selected
    }

    @After
    public void tearDown() throws Exception
    {
        driver.quit();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        System.out.println("Tests finalized");
    }
}
