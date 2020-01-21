import java.util.Random;

public class Utill {


    /**
     * Метод для вычисления рандомного числа от 0 и до количества товаров на странице
     */

    public static Integer randomizer(Integer amountProducts){
        Random random=new Random();
        return random.nextInt(amountProducts-1);
    }


}
