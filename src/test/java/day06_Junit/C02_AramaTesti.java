package day06_Junit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
public class C02_AramaTesti {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        //1. “https://www.saucedemo.com” Adresine gidin
        driver.navigate().to("https://www.saucedemo.com");
        //2. Username kutusuna “standard_user” yazdirin
        driver.findElement(By.xpath("(//input[@class='input_error form_input'])[1]")).sendKeys("standard_user");
        //3. Password kutusuna “secret_sauce” yazdirin
        driver.findElement(By.xpath("(//input[@class='input_error form_input'])[2]")).sendKeys("secret_sauce");
        //4. Login tusuna basin
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        //5. Ilk urunun ismini kaydedin ve bu urunun sayfasina gidin
        WebElement ilkUrun=driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        String ilkurunismi=ilkUrun.getText();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).click();
        //6. Add to Cart butonuna basin
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        //7. Alisveris sepetine tiklayin
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        //8. Sectiginiz urunun basarili olarak sepete eklendigini control edin
        WebElement secilenUrunElementi=driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        String secilenUrun=secilenUrunElementi.getText();
        String expectedUrun=ilkurunismi;
        if (expectedUrun.contains(secilenUrun)){
            System.out.println("urun tamam");
        }else {
            System.out.println("urun yanlis");
        }
        //9. Sayfayi kapatin
        driver.close();
    }
}