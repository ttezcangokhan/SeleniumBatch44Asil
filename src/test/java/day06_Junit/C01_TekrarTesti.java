package day06_Junit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.Arrays;
public class C01_TekrarTesti {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //2- https://www.google.com/ adresine gidin
        driver.get("https://www.google.com/");
        //3- cookies uyarisini kabul ederek kapatin
        driver.findElement(By.xpath("//div[text()='Ich stimme zu']")).click();
        //4-Sayfa basliginin “Google” ifadesi icerdigini test edin
        String actualSayfaTitle=driver.getTitle();
        String arananKelime="Google";
        if (actualSayfaTitle.contains(arananKelime)){
            System.out.println("Title testi PASSED");
        }else{
            System.out.println("Title testi FAILED");
        }
        //5- Arama cubuguna “Nutella” yazip aratin
        WebElement aramaKutusu=driver.findElement(By.xpath("//input[@name='q']"));
        aramaKutusu.sendKeys("Nutella" + Keys.ENTER );
        //6-Bulunan sonuc sayisini yazdirin
        WebElement sonucSayisiElementi=driver.findElement(By.xpath("//div[@id='result-stats']"));
        System.out.println(sonucSayisiElementi.getText());
        //7- sonuc sayisinin 10 milyon’dan fazla oldugunu test edin
        String sonucSayisiString=sonucSayisiElementi.getText();
        String sonucKelimeleri[]=sonucSayisiString.split(" ");
        String sonucNutellaSayisiString=sonucKelimeleri[1]; // 73.900.000
        sonucNutellaSayisiString=sonucNutellaSayisiString.replace(".","");
        int nutellaAramaSonucu=Integer.parseInt(sonucNutellaSayisiString);
        if (nutellaAramaSonucu>10000000){
            System.out.println("Nutella arama testi PASSED");
        } else {
            System.out.println("Nutella arama testi FAILED");
        }
        //8-Sayfayi kapatin
        driver.close();
    }
}