import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class OliverTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void setupBeforeSuite() {
        System.setProperty("webdriver.chrome.driver", "src/test/resourses/drivers/windows64/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
    }

    //    PLAN
    //    navigate to the page
    //    click 'Feedback' button on header
    //    Fill out the form
    //    submit the form
    //    make sure that confirmation page appears


    @Test
    public void testName() throws InterruptedException {
    navigatePage();
    feedbackClick();
    fillOutForm("Elena Tikhonova", "alenakabanova@gmail.com", "question", "test");
    submitFeedback();
    conformationPageAppears();
    boolean isConformationPageAppears = conformationPageAppears();
    Assert.assertTrue(isConformationPageAppears);
    }



    private boolean conformationPageAppears() {
        String url= driver.getCurrentUrl();
        System.out.println(url);
        String expectedURL = "http://zero.webappsecurity.com/sendFeedback.html";
        boolean result = false;
        if (url.equalsIgnoreCase(expectedURL)) {
            result = true;
        }
        return result;
    }

    private void submitFeedback() {
        WebElement submitButton = driver.findElement(By.xpath("//input[@name='submit']"));
        submitButton.click();
    }

    private void fillOutForm(String name, String email, String subject, String comment) {
        WebElement nameField = driver.findElement(By.xpath("//input[@id='name']"));
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);
        WebElement emailField = driver.findElement(By.xpath("//input[@id='email']"));
        emailField.sendKeys(email);
        WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
        subjectField.sendKeys(subject);
        WebElement commentField = driver.findElement(By.xpath("//*[@id='comment']"));
        commentField.sendKeys(comment);
    }

    private void feedbackClick() {
        WebElement feedbackButton = driver.findElement(By.xpath("//li[@id='feedback']"));
        feedbackButton.click();
    }

    private void navigatePage() throws InterruptedException {
        driver.get("http://zero.webappsecurity.com/");
        Thread.sleep(3000);
    }

    @AfterSuite
    public void setupAfterSuite() {
        driver.quit();
    }
}
