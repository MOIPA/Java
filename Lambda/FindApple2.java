import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindApple2 {
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(new Apple("red", 100), new Apple("green", 250), new Apple("green", 220));
        ArrayList<Apple> resultList = (ArrayList<Apple>) appleFiter(appleList, "green");
        System.out.println(resultList);

    }
    

    public static List<Apple> appleFiter(List<Apple> appleList,String color) {
        ArrayList<Apple> resultList = new ArrayList<>();
        for (Apple apple : appleList) {
            if (apple.getColor() == color) {
                resultList.add(apple);
            }
        }
        return resultList;
    }
}
