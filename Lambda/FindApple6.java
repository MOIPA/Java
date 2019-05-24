import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class FindApple6 {
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(new Apple("red", 100), new Apple("green", 250), new Apple("green", 220));
        ArrayList<Apple> resultList = new ArrayList<>();
        appleFiterByConsumer(appleList,resultList,(result,apple)->{
            if (apple.getWeight() > 100) {
                result.add(apple);
            }
        });
//        appleFiterByConsumer(appleList, resultList, new BiConsumer<List<Apple>, Apple>() {
//            @Override
//            public void accept(List<Apple> result, Apple apple) {
//                if (apple.getWeight() > 100) {
//                    result.add(apple);
//                }
//            }
//        });
        System.out.println(resultList);

    }


    /**
     * BiConsumer 会自动添加apple到自己的入参的list中，判断方法为accept，内部实现在11行
     * @param appleList
     * @param resultList
     * @param biConsumer
     */
    public static void appleFiterByConsumer(List<Apple> appleList, List<Apple> resultList, BiConsumer<List<Apple>,Apple> biConsumer) {
        for (Apple apple : appleList) {
            biConsumer.accept(resultList,apple);
        }
    }
}
