
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;


import java.util.List;

public class Test {


    private FirefoxDriver driver;

    @org.testng.annotations.Test(priority = 1, description = "Открыть в браузере сайт https://www.ozon.ru/.")
    public void openFirefox() {
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.ozon.ru");

    }

    @org.testng.annotations.Test(priority = 2, description = "В меню \"Каталог\" выбрать категорию \"Музыка\".")
    public void findMusic() throws InterruptedException {
        driver.findElementByCssSelector("#__layout > div > div.block-vertical > header > div.s7.index > div.t > div > button").click();
        driver.findElementByCssSelector("a.a1h7:nth-child(23) > span:nth-child(1)").click();
        Thread.sleep(500);
    }

    @org.testng.annotations.Test(priority = 3, description = "С открывшейся страницы перейти на страницу \"Виниловые пластинки\".")

    public void openVinil() throws InterruptedException {
        driver.findElementByCssSelector("div.b9n5:nth-child(1) > a:nth-child(1)").click();
        Thread.sleep(500);

    }

    /**
     *
     */
    @org.testng.annotations.Test(priority = 4, description = "Проверить, что открылся список товаров.")
    public void truePage() {
        List<WebElement> webElements = driver.findElementsByXPath("//div[@class = 'a7w6'");
        Assert.assertTrue(webElements.size() > 30);

    }


}
