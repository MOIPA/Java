import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindApple1 {
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(new Apple("red", 100), new Apple("green", 250), new Apple("green", 220));
        ArrayList<Apple> resultList = new ArrayList<>();
        for (Apple apple : appleList) {
            if (apple.getColor() == "green") {
                resultList.add(apple);
            }
        }
        System.out.println(resultList);

    }
}
