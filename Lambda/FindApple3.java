import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindApple3 {
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(new Apple("red", 100), new Apple("green", 250), new Apple("green", 220));
        ArrayList<Apple> resultList = (ArrayList<Apple>) appleFiter(appleList, new Filter() {
            @Override
            public boolean filter(Apple apple) {
                return apple.getWeight() > 220 && apple.getColor() == "green";
            }
        });
        System.out.println(resultList);

    }

    public interface Filter {
        boolean filter(Apple apple);
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
