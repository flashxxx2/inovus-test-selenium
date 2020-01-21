
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSelenium {


    private FirefoxDriver driver;
    private Integer amount;
    private Integer firstRandomNumber;
    private Integer secondRandomNumber;

    private String firstName;
    private String firstPrice;
    private String secondName;
    private String secondPrice;


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
        Assert.assertTrue(webElements.size() > 10);

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
    public void getProductByFirstNumber() {
        String xPath = "//div[@class = 's4']/div[" + firstRandomNumber + "]";
        driver.findElementByXPath(xPath).click();
        System.out.println(xPath);


    }

    @Test(priority = 8, description = "Запомнить стоимость и название данного товара.")
    public void saveFirstProduct() {
        firstName = driver.findElementByCssSelector(".b2s1 > span:nth-child(1)").getText();
        firstPrice = driver.findElementByCssSelector(".b2y5").getText();
        System.out.println(firstName);
    }

    @Test(priority = 9, description = "Добавить товар в корзину.")
    public void sendInBasketFirstProduct() {
        driver.findElementByCssSelector(".b3u4 > button:nth-child(1) > div:nth-child(1)").click();
    }


    @Test(priority = 10, description = "Проверить то, что в корзине появился добавленный в п.9 товар. ( Проверка данных\n" +
            "определенного товара. Необходим переход в корзину для этого.")
    public void checkBasket() throws InterruptedException {
        Thread.sleep(500);
        driver.findElementByCssSelector(".b1k5").click();
    }


    @Test(priority = 11, description = "Вернуться на страницу \"Виниловые пластинки\".")
    public void back() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(500);
        driver.navigate().back();

    }


    @Test(priority = 12, description = " Сгенерировать случайное число в диапазоне от 1 до количества товаров, полученного в\n" +
            "п.5")
    public void generateSecondRandomNumber() {
        secondRandomNumber = Utill.randomizer(amount);
        System.out.println(secondRandomNumber);
        if (secondRandomNumber == firstRandomNumber) {
            getProductByFirstNumber();
        }
    }


    @Test(priority = 13, description = "Выбрать товар под номером, полученным в п.12. ( Перейти на страницу товара )")
    public void getProductBySecondNumber() {
        String xPath = "//div[@class = 's4']/div[" + secondRandomNumber + "]";
        driver.findElementByXPath(xPath).click();

    }

    @Test(priority = 14, description = "Запомнить стоимость и название данного товара.")
    public void saveSecondProduct() {
        secondName = driver.findElementByCssSelector(".b2s1 > span:nth-child(1)").getText();
        secondPrice = driver.findElementByCssSelector(".b2y5").getText();
        System.out.println(secondName);
    }


    @Test(priority = 15, description = "Добавить товар в корзину.")
    public void sendInBasketSecondProduct() {
        driver.findElementByCssSelector(".b3u4 > button:nth-child(1) > div:nth-child(1)").click();
    }


    @Test(priority = 16, description = "Проверить то, что в корзине два товара. ( Проверка количества товаров в корзине. Может\n" +
            "быть произведена без открытия корзины, а проверяя значение в header сайта, где указано\n" +
            "количество товаров в корзине )")
    public void checkBasketByHeader() {
        driver.navigate().back();
        String count = driver.findElementByCssSelector("a.a0s1:nth-child(4)").getText();

        Assert.assertEquals(count, "2");
    }

    @Test(priority = 17, description = "Открыть корзину.")
    public void inBasket() {

        driver.findElementByCssSelector(".b1k5").click();
    }

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
