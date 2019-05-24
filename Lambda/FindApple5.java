import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FindApple5 {
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(new Apple("red", 100), new Apple("green", 250), new Apple("green", 220));
        ArrayList<Apple> resultList = (ArrayList<Apple>) appleFiter(appleList, apple -> apple.getColor()=="green");
        System.out.println(resultList);

    }


    /**
     * predicate 是断言，test方法接受函数实现，返回boolean，相当于findapple4的自定义filter
     * @param appleList
     * @param filter
     * @return
     */
    public static List<Apple> appleFiter(List<Apple> appleList, Predicate<Apple> filter) {
        ArrayList<Apple> resultList = new ArrayList<>();
        for (Apple apple : appleList) {
            if (filter.test(apple)) {
                resultList.add(apple);
            }
        }
        return resultList;
    }
}
