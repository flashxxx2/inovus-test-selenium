import java.util.Random;

public class Utill {


    /**
     * Метод для вычисления рандомного числа от 0 и до количества товаров на странице
     */

    public static Integer randomizer(Integer amountProducts){
        Random random=new Random();
        return random.nextInt(amountProducts-1);
    }
    /**
     * Метод для перевода цены из строки в число
     */
    public static int parseNum(String price) {

        String[] numbers = price.split("");
        String numberNew = "";
        for (String number : numbers) {
            if (!number.equals(" ")) {
                numberNew += number;
            }
        }
        return Integer.parseInt(numberNew);
    }
}



