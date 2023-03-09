package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TestGoogle3 {
    //VARIABLES
    private WebDriver driver;
    private static final String DRIVER_TYPE = "webdriver.chrome.driver";
    private static final String PATH_DRIVER = "src/test/resources/webDriver/chromedriver.exe";
    private String URL = "https://www.google.com";

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
        driver.get(URL);
    }

    @Test
    public void testSearch() throws Exception
    {
        driver.findElement(By.xpath("//*[@id=\"W0wltc\"]")).click();

        WebElement element = driver.findElement(By.tagName("form"));
        WebElement searchElement = element.findElement(By.name("q"));
        searchElement.sendKeys("Test03");
        searchElement.submit();
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
