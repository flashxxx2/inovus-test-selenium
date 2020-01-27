

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    /**
     * Ищем нужную нам категорию товаров.
     */

    @Test(priority = 2, description = "В меню \"Каталог\" выбрать категорию \"Музыка\".")
    public void findMusic() {

        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@value='Каталог']")));
        driver.findElementByXPath("//button[@value='Каталог']").click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Музыка')]")));
        driver.findElementByXPath("//span[contains(text(),'Музыка')]").click();


    }

    /**
     * Переходим на страницу нужных нам товаров.
     */

    @Test(priority = 3, description = "С открывшейся страницы перейти на страницу \"Виниловые пластинки\".")

    public void openVinil() {


        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Виниловые пластинки')]")));
        driver.findElementByXPath("//a[contains(text(),'Виниловые пластинки')]").click();

    }

    /**
     * Если на странице много элементов с атрибутом @data-test-id- значит открылся именно список товаров.
     * Конкретно в этом случае, если их больше 20.
     * Количество товара так же ровно количеству элементов с атрибутом @data-test-id.
     */
    @Test(priority = 4, description = "Проверить, что открылся список товаров.")
    public void truePage() {

        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/span[@data-test-id='tile-name']")));
        List<WebElement> webElements = driver.findElementsByXPath("//div/span[@data-test-id='tile-name']");
        Assert.assertTrue(webElements.size() > 20);
        amount = webElements.size() / 2;


    }

    /**
     * Вычисляем первое рандомное число, использую метод из класса Util.
     */

    @Test(priority = 5, description = "Сгенерировать случайное число в диапазоне от 1 до количества товаров, полученного в п.5")
    public void generateFirstRandomNumber() {

        firstRandomNumber = Util.randomizer(amount);
        if (firstRandomNumber > amount || firstRandomNumber == 0) {
            generateFirstRandomNumber();
        }
        System.out.println(firstRandomNumber);



    }

    /**
     * Получаем первый товар исходя из сгенерированного числа.
     */

    @Test(priority = 6, description = "Выбрать товар под номером, полученным в п.6. ( Перейти на страницу товара )")
    public void getProductByFirstNumber() {


        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);

        String xPath = "//div[2]/div[1]/div[1]/div/div[" + firstRandomNumber + "]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        driver.findElementByXPath(xPath).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * Сохраняем в переменные наименование и цену первого товара.
     */

    @Test(priority = 7, description = "Запомнить стоимость и название данного товара.")
    public void saveFirstProduct() {
        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[1]/div/h1/span")));

        firstName = driver.findElementByXPath("//div[1]/div/h1/span").getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/div[2]/div/div[1]/div[1]/div/div[1]/div/div/div")));
        firstPrice = driver.findElement(By.xpath("//div[3]/div[2]/div/div[1]/div[1]/div/div[1]/div/div/div")).getText();


    }

    /**
     * Добавляем первый товар в корзину.
     */

    @Test(priority = 8, description = "Добавить товар в корзину.")
    public void sendInBasketFirstProduct() {
        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[1]/div[2]/div/div/div[1]/button/div")));
        driver.findElementByXPath("//div[1]/div[2]/div/div/div[1]/button/div").click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * Проверяем, что наименование товара в корзине совпадает с выбранным нами товаром.
     */

    @Test(priority = 9, description = "Проверить то, что в корзине появился добавленный в п.9 товар. " +
            "( Проверка данных определенного товара. Необходим переход в корзину для этого.")
    public void checkBasket() {
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Корзина')]")));
        driver.findElementByXPath("//span[contains(text(),'Корзина')]").click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/div[2]/a/span")));
        String productName = driver.findElementByXPath("//div[3]/div[2]/a/span").getText();
        Assert.assertEquals(productName, firstName);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    /**
     * Возвращаемся на страницу "Виниловые пластинки", дважды нажав кнопку назад в браузере.
     */
    @Test(priority = 10, description = "Вернуться на страницу \"Виниловые пластинки\".")
    public void back() throws InterruptedException {

        driver.navigate().back();
        Thread.sleep(800);
        driver.navigate().back();

    }

    /**
     * Вычисляем второе рандомное число, использую метод из класса Util.
     */

    @Test(priority = 11, description = " Сгенерировать случайное число в диапазоне от 1 до количества товаров, полученного в п.5")
    public void generateSecondRandomNumber() {

        secondRandomNumber = Util.randomizer(amount);
        if (secondRandomNumber == firstRandomNumber || secondRandomNumber > amount || secondRandomNumber == 0) {
            generateSecondRandomNumber();
        }



    }

    /**
     * Получаем второй товар исходя из сгенерированного числа.
     */

    @Test(priority = 12, description = "Выбрать товар под номером, полученным в п.12. ( Перейти на страницу товара )")
    public void getProductBySecondNumber() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        String xPath = "//div[2]/div[2]/div[1]/div/div/div[" + secondRandomNumber + "]";
        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        driver.findElementByXPath(xPath).click();
        Thread.sleep(1000);


    }

    /**
     * Сохраняем в переменные наименование и цену второго товара.
     */
    @Test(priority = 13, description = "Запомнить стоимость и название данного товара.")
    public void saveSecondProduct() {


        secondName = driver.findElementByXPath("//h1/span").getText();
        secondPrice = driver.findElementByXPath("//div[3]/div[2]/div/div[1]/div[1]/div/div[1]/div/div/div").getText();

    }

    /**
     * Добавляем второй товар в корзину.
     */

    @Test(priority = 14, description = "Добавить товар в корзину.")
    public void sendInBasketSecondProduct() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div[1]/div[2]/div/div/div[1]/button/div")));
        Thread.sleep(500);
        driver.findElementByXPath("//div/div[1]/div[2]/div/div/div[1]/button/div").click();

    }


    @Test(priority = 15, description = "Проверить то, что в корзине два товара." +
            "Проверка количества товаров в корзине может быть произведена без открытия корзины, а проверяя значение в header сайта, " +
            "где указано количество товаров в корзине")
    public void checkBasketByHeader() {


        driver.navigate().back();
        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/div[4]/a[2]/span[1]")));
        driver.navigate().refresh();
        String count = driver.findElementByXPath("//header/div[1]/div[4]/a[2]/span[1]").getText();
        Assert.assertEquals(count, "2");

    }

    /**
     * Переходим в корзину.
     */

    @Test(priority = 16, description = "Открыть корзину.")
    public void openBasket() {

        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[2]/span[2]")));
        driver.findElementByXPath("//a[2]/span[2]").click();

    }

    /**
     * Проверяем текстовые значения наименований товаров с наименованиями, сохраненными ранее.
     * Вычисляем общую стоимость товаров в корзине, для этого используем метод класса Util. Сравниваем вычисленную стоимость со значением из корзины.
     */

    @Test(priority = 17, description = "Проверить то, что в корзине раннее выбранные товары и итоговая стоимость по двум товарам рассчитана верно.")
    public void checkNameAndPriceProductsInBasket() throws InterruptedException {
        Thread.sleep(800);
        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        WebElement parent = driver.findElement(By.xpath("//div[5]/div[1]/div[1]/div/div[2]"));

        List<WebElement> elements = parent.findElements(By.xpath("div/div/a/span"));
        Assert.assertEquals(elements.get(1).getText(), firstName);
        Assert.assertEquals(elements.get(0).getText(), secondName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/span[2]")));
        String price = driver.findElementByXPath("//div[3]/span[2]").getText();

        Assert.assertEquals(Util.parseNum(price), Util.parseNum(firstPrice) + Util.parseNum(secondPrice));

    }

    /**
     * Удаляем все товары из корзины.
     */

    @Test(priority = 18, description = "Удалить из корзины все товары.")
    public void deleteAllProductsInBasket() {
        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Удалить выбранные')]")));

        driver.findElementByXPath("//span[contains(text(),'Удалить выбранные')]").click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Удалить')]")));
        driver.findElementByXPath("//div[contains(text(),'Удалить')]").click();
    }

    /**
     * Ищем на странице текст - Корзина пуста.
     */

    @Test(priority = 19, description = "Проверить, что корзина пуста.")
    public void checkEmptyBasket() {
        WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Корзина пуста')]")));

        String empty = driver.findElementByXPath("//h1[contains(text(),'Корзина пуста')]").getText();
        Assert.assertEquals(empty, "Корзина пуста");
    }

    /**
     * Завершаем работу тестов и закрываем браузер.
     */
    @Test(priority = 20, description = "Закрыть браузер.")
    public void close() {

        driver.quit();
    }
}
