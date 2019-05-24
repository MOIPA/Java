import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindApple4 {
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(new Apple("red", 100), new Apple("green", 250), new Apple("green", 220));
        ArrayList<Apple> resultList = (ArrayList<Apple>) appleFiter(appleList, apple -> apple.getColor()=="green");
        System.out.println(resultList);

    }

    /**
     * 函数式接口中只能由一个抽象方法，但是default方法可以有多个，
     */
    @FunctionalInterface
    public interface Filter {
        boolean filter(Apple apple);

        boolean equals(Object object);

        default int getId() {
            return 0;
        }
    }

    public static List<Apple> appleFiter(List<Apple> appleList, Filter filter) {
        ArrayList<Apple> resultList = new ArrayList<>();
        for (Apple apple : appleList) {
            if (filter.filter(apple)) {
                resultList.add(apple);
            }
        }
        return resultList;
    }
}
