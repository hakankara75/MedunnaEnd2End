package utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
public class Driver {
    private Driver(){

    }

    private static WebDriver driver;
    // driver'i baska class'lardan sadece Driver class ismi ile cagirabilmek icin STATIC yaptik
    // henuz bu driver ile ilgili ayarlar yapmadigim icin baska class'lar bunu yanlislikla kullanmasin diye
    // erisimi private yaptik (sadece bu class'in kullanimina acik yaptik)

    public static WebDriver getDriver(){
        if (driver==null){
            // if'i bu method her calistiginda yeni bir driver olusturmamasi icin kullaniyorruz
            // if driver'i kontrol edecek eger bir deger atamasi yapildiysa yeni bir driver olusturmayacak
            switch (ConfigReader.getProperty("browser")) {
                // case'i driver'i istedigimiz browser'da calistirmak icin kullaniyoruz
                // configuration.properties dosyasinda browser olarak ne yazdiksa tum testlerimiz o browser'da calisacak
                // browser secimi yapilmadiysa default olarak chrome devreye girecek
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
                    ChromeOptions options= new ChromeOptions();
                    options.addArguments("--headless"); // Headless test yapmak icin
                    options.addArguments("--disaple-gpu"); //GPU kullanimini devre disi birakir
                    //driver= new ChromeDriver(options); //bu kodu headless testte acarsinizi

                    //asagidaki kodlar file download yaparken default deger olan download klasoru yerine bir yol vermemize yarar
//                    ChromeOptions options = new ChromeOptions();
//                    String filePath ="C:\\Hakan Arsiv";
//                    Map<String, Object> prefs = new HashMap<>();
//                    prefs.put("download.default_directory", filePath);
//                    options.setExperimentalOption("prefs", prefs);
                    //  driver = new ChromeDriver(options);  //bu satirdaki yorum slashlarini bir alt satira indirip calistirmaliyiz.
                    driver = new ChromeDriver();
                    break;




                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    //bu secenekte chrome acilmadan test kosulur
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void closeDriver(){
        if (driver!=null){
            driver.quit();
        }
        // burada yeniden null degeri atamamizin sebebi. bir sonraki getDriver method'u cagirisimizda
        // yeni deger atayabilmek istememizdir.
        driver=null;
    }
}
