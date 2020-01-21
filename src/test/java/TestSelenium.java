
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSelenium {


    private FirefoxDriver driver;
    private Integer amount;
    private Integer firstRandomNumber;
    private Integer secondRandomNumber;
    private String  firstName;
    private Integer firstPrice;


    @Test(priority = 1, description = "Открыть в браузере сайт https://www.ozon.ru/.")
    public void openFirefox() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.ozon.ru");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    @Test(priority = 2, description = "В меню \"Каталог\" выбрать категорию \"Музыка\".")
    public void findMusic() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        // driver.findElement(By.className("b1o4")).click();
        driver.findElementByCssSelector(".b1z1").click();
        // driver.findElement(By.className("a1k3")).click();
        driver.findElementByCssSelector("a.ac4:nth-child(23) > span:nth-child(1)").click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test(priority = 3, description = "С открывшейся страницы перейти на страницу \"Виниловые пластинки\".")

    public void openVinil() {
        driver.findElementByCssSelector("div.b7w7:nth-child(1) > a:nth-child(1)").click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    /**
     *
     */
    @Test(priority = 4, description = "Проверить, что открылся список товаров.")
    public void truePage() {
        List<WebElement> webElements = driver.findElementsByXPath("//div[@class = 'a1f8 a1g6 a1g']");
        Assert.assertTrue(webElements.size() > 30);

    }

    @Test(priority = 5, description = "Получить количество товаров на странице.")
    public void amountProducts() {
        List<WebElement> webElements = driver.findElementsByXPath("//div[@class = 'a1f8 a1g6 a1g']");
        amount = webElements.size();
        System.out.println("Количество товара на странице " + amount);
    }

    @Test(priority = 6, description = "Сгенерировать случайное число в диапазоне от 1 до количества товаров, полученного в п.5")
    public void generateFirstRandomNumber() {
        firstRandomNumber = Utill.randomizer(amount);
        System.out.println(firstRandomNumber);


    }

    @Test(priority = 7, description = "Выбрать товар под номером, полученным в п.6. ( Перейти на страницу товара )")
    public void getProductByNumber() {
        String xPath="//div[@class = 's4']/div["+firstRandomNumber+"]";
        driver.findElementByXPath(xPath).click();
        System.out.println(xPath);


    }

        @Test(priority = 8,description = "Запомнить стоимость и название данного товара.")
    public void saveProduct(){
        firstName=driver.findElementByXPath("//div[@class='b2s1']/h1[text()]").getText();
            System.out.println(firstName);
        }
//
//
//
//
//    @Test(priority = 9,description ="Добавить товар в корзину." )
//
//
//
//    @Test(priority = 10,description = "Проверить то, что в корзине появился добавленный в п.9 товар. ( Проверка данных\n" +
//            "определенного товара. Необходим переход в корзину для этого.")
//
//
//    @Test(priority = 11, description = "Вернуться на страницу \"Виниловые пластинки\".")
//    public void back() {
//        driver.navigate().back();
//        driver.navigate().back();
//
//    }
//
//
//    @Test(priority = 12, description = " Сгенерировать случайное число в диапазоне от 1 до количества товаров, полученного в\n" +
//            "п.5")
//    public void generateSecondRandomNumber() {
//        secondRandomNumber = Utill.randomizer(amount);
//        System.out.println(secondRandomNumber);
//        if (secondRandomNumber == firstRandomNumber) {
//            getProductByNumber();
//        }
//

//    @Test(priority = 13,description = "Выбрать товар под номером, полученным в п.12. ( Перейти на страницу товара )")
//
//
//
//    @Test(priority = 14,description = "Запомнить стоимость и название данного товара.")
//
//
//
//
//    @Test(priority = 15,description = "Добавить товар в корзину.")
//
//    @Test(priority = 16,description = "Проверить то, что в корзине два товара. ( Проверка количества товаров в корзине. Может\n" +
//            "быть произведена без открытия корзины, а проверяя значение в header сайта, где указано\n" +
//            "количество товаров в корзине )")
//
//
//    @Test(priority = 17,description = "Открыть корзину.")
//
//    @Test(priority = 18,description = "Проверить то, что в корзине раннее выбранные товары и итоговая стоимость по двум\n" +
//            "товарам рассчитана верно.")
//
//
//    @Test(priority = 19,description = "Удалить из корзины все товары.")
//
//
//    @Test(priority = 20,description = "Проверить, что корзина пуста.")
//
//
//    @Test(priority = 21,description = "Закрыть браузер.")


}
