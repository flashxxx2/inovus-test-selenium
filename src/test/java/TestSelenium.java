
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.json.JsonOutput;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.ArrayList;
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

    /**
     * Открываем браузер и заходим на нужную нам страницу.
     */
    @Test(priority = 1, description = "Открыть в браузере сайт https://www.ozon.ru/.")
    public void openFirefox() {
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.ozon.ru");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }

    /**
     * Ищем нужную нам категорию товаров.
     */

    @Test(priority = 2, description = "В меню \"Каталог\" выбрать категорию \"Музыка\".")
    public void findMusic() {

        driver.findElementByXPath("//button[@value='Каталог']").click();
        driver.findElementByXPath("//span[contains(text(),'Музыка')]").click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    /**
     * Переходим на страницу нужных нам товаров.
     */

    @Test(priority = 3, description = "С открывшейся страницы перейти на страницу \"Виниловые пластинки\".")

    public void openVinil() {
        driver.findElementByXPath("//a[contains(text(),'Виниловые пластинки')]").click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    /**
     * Если на странице много элементов с атрибутом @class, значение которого равно атрибуту @class каждого товара - значит открылся именно список товаров.
     * Конкретно в этом случае, если их больше 20. Число может быть и другим.
     */
    @Test(priority = 4, description = "Проверить, что открылся список товаров.")
    public void truePage() {
        List<WebElement> webElements = driver.findElementsByXPath("//div/span[@data-test-id='tile-name']");
        Assert.assertTrue(webElements.size() > 30);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        System.out.println(webElements.size());

    }

    /**
     * Считаем количество товара на странице с одинаковым атрибутом @class,значение которого равно атрибуту @class каждого товара.
     * Это и есть число товаров на странице
     */

    @Test(priority = 5, description = "Получить количество товаров на странице.")
    public void amountProducts() {
        List<WebElement> webElements = driver.findElementsByXPath("//div/span[@data-test-id='tile-name']");
        amount = webElements.size();
        System.out.println("Количество товара на странице " + amount);
    }

    /**
     * Вычисляем первое рандомное число, использую метод из класса Util.
     */

    @Test(priority = 6, description = "Сгенерировать случайное число в диапазоне от 1 до количества товаров, полученного в п.5")
    public void generateFirstRandomNumber() {
        firstRandomNumber = Util.randomizer(amount);
        if (firstRandomNumber > 36 || firstRandomNumber == 0) {
            generateFirstRandomNumber();
        }
        System.out.println(firstRandomNumber);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }

    /**
     * Получаем первый товар исходя из сгенерированного числа.
     */

    @Test(priority = 7, description = "Выбрать товар под номером, полученным в п.6. ( Перейти на страницу товара )")
    public void getProductByFirstNumber() {
        String xPath = "//div/div[" + firstRandomNumber + "]";

        driver.findElementByXPath(xPath).click();
        System.out.println(xPath);


    }

    /**
     * Сохраняем в переменные наименование и цену первого товара.
     */

    @Test(priority = 8, description = "Запомнить стоимость и название данного товара.")
    public void saveFirstProduct() {
        firstName = driver.findElementByXPath("//div[1]/div/h1/span").getText();
        firstPrice = driver.findElement(By.xpath("//div[3]/div[2]/div/div[1]/div[1]/div/div[1]/div/div/div")).getText();

        System.out.println(firstName);
    }

    /**
     * Добавляем первый товар в корзину.
     */

    @Test(priority = 9, description = "Добавить товар в корзину.")
    public void sendInBasketFirstProduct() throws InterruptedException {

        driver.findElementByXPath("//div[1]/div[2]/div/div/div[1]/button/div").click();
        Thread.sleep(500);
    }

    /**
     * Проверяем, что наименование товара в корзине совпадает с выбранным нами товаром.
     */

    @Test(priority = 10, description = "Проверить то, что в корзине появился добавленный в п.9 товар. " +
            "( Проверка данных определенного товара. Необходим переход в корзину для этого.")
    public void checkBasket() {

        driver.findElementByXPath("//span[contains(text(),'Корзина')]").click();
        String productName = driver.findElementByXPath("//div[3]/div[2]/a/span").getText();
        Assert.assertEquals(productName, firstName);
        System.out.println(productName);
    }

    /**
     * Возвращаемся на страницу "Виниловые пластинки", дважды нажав кнопку назад в браузере.
     */
    @Test(priority = 11, description = "Вернуться на страницу \"Виниловые пластинки\".")
    public void back() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(500);
        driver.navigate().back();

    }

    /**
     * Вычисляем второе рандомное число, использую метод из класса Util.
     */

    @Test(priority = 12, description = " Сгенерировать случайное число в диапазоне от 1 до количества товаров, полученного в п.5")
    public void generateSecondRandomNumber() {
        secondRandomNumber = Util.randomizer(amount);

        if (secondRandomNumber == firstRandomNumber || secondRandomNumber>32 || secondRandomNumber==0) {
            generateSecondRandomNumber();
        }
        System.out.println(secondRandomNumber);
    }

    /**
     * Получаем второй товар исходя из сгенерированного числа.
     */

    @Test(priority = 13, description = "Выбрать товар под номером, полученным в п.12. ( Перейти на страницу товара )")
    public void getProductBySecondNumber() throws InterruptedException {
        Thread.sleep(500);
        String xPath = "//div[2]/div[2]/div[1]/div/div/div["+ secondRandomNumber + "]";
        driver.findElementByXPath(xPath).click();

    }

    /**
     * Сохраняем в переменные наименование и цену второго товара.
     */
    @Test(priority = 14, description = "Запомнить стоимость и название данного товара.")
    public void saveSecondProduct() throws InterruptedException {
        secondName = driver.findElementByXPath("//h1/span").getText();
        secondPrice = driver.findElementByXPath("//div[3]/div[2]/div/div[1]/div[1]/div/div[1]/div/div/div").getText();
        System.out.println(secondName);
        Thread.sleep(500);
    }

    /**
     * Добавляем второй товар в корзину.
     */

    @Test(priority = 15, description = "Добавить товар в корзину.")
    public void sendInBasketSecondProduct() throws InterruptedException {
        driver.findElementByXPath("//div/div[1]/div[2]/div/div/div[1]/button/div").click();
        Thread.sleep(500);
    }


    @Test(priority = 16, description = "Проверить то, что в корзине два товара." +
            "Проверка количества товаров в корзине может быть произведена без открытия корзины, а проверяя значение в header сайта, " +
            "где указано количество товаров в корзине")
    public void checkBasketByHeader() {
        driver.navigate().back();
        String count = driver.findElementByXPath("//header/div[1]/div[4]/a[2]/span[1]").getText();

        Assert.assertEquals(count, "2");
    }

    /**
     * Переходим в корзину.
     */

    @Test(priority = 16, description = "Открыть корзину.")
    public void openBasket() {

          driver.findElementByXPath("//a[2]/span[2]").click();

    }

    /**
     * Проверяем текстовые значения наименований товаров с наименованиями, сохраненными ранее.
     * Вычисляем общую стоимость товаров в корзине, для этого используем метод класса Util. Сравниваем вычисленную стоимость со значением из корзины.
     */

    @Test(priority = 18, description = "Проверить то, что в корзине раннее выбранные товары и итоговая стоимость по двум товарам рассчитана верно.")
    public void checkNameAndPriceProductsInBasket() {
        List<WebElement> elements = driver.findElementsByXPath("//div[5]/div[1]/a/span");
        List<String> list = new ArrayList<>();
        System.out.println(elements);
        for (WebElement element : elements) {
            list.add(element.getText());

        }
        Assert.assertEquals(list.get(1), firstName);
        Assert.assertEquals(list.get(0), secondName);
        String price = driver.findElementByXPath("//div[3]/span[2]").getText();
        Assert.assertEquals(Util.parseNum(price), Util.parseNum(firstPrice) + Util.parseNum(secondPrice));

    }

    /**
     * Удаляем все товары из корзины.
     */

    @Test(priority = 19, description = "Удалить из корзины все товары.")
    public void deleteAllProductsInBasket() {
        driver.findElementByXPath("//span[contains(text(),'Удалить выбранные')]").click();
        driver.findElementByCssSelector("//div[contains(text(),'Удалить')]").click();

    }

    /**
     * Ищем на странице текст - Корзина пуста.
     */

    @Test(priority = 20, description = "Проверить, что корзина пуста.")
    public void checkEmptyBasket() {
        String empty = driver.findElementByXPath("//h1[contains(text(),'Корзина пуста')]").getText();
        Assert.assertEquals(empty, "Корзина пуста");
    }

    /**
     * Завершаем работу тестов и закрываем браузер.
     */


//    @Test(priority = 21,description = "Закрыть браузер.")
//    public void close(){
//        driver.quit();
//    }
}
