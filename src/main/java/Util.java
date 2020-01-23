import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {


    /**
     * Метод для вычисления рандомного числа от 0 и до количества товаров на странице
     */

    public static Integer randomizer(Integer amountProducts) {
        Random random = new Random();
        return random.nextInt(amountProducts - 1);
    }

    /**
     * Метод для перевода цены из строки в число
     */
    public static int parseNum(String price) {
        int start = 0;
        int result = 0;
        String[] numbers = price.split("");
        String numberNew = "";
        for (String number : numbers) {
            if (!number.equals(" ")) {
                numberNew += number;
            }
        }
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(numberNew);

        while (matcher.find(start)) {
            String value = numberNew.substring(matcher.start(), matcher.end());
            result = Integer.parseInt(value);
            start = matcher.end();
        }
        return result;

    }
}



