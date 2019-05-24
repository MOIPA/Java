package MethodReference;

import java.util.Arrays;
import java.util.List;

public class TestMethodReference1 {

    /**
     * 双冒号是方法引用可以用lambda代替，实质是简化lambda
     * @param args
     */
    public static void main(String[] args) {

        List<Student> list = Arrays.asList(new Student("zhangsan", 64), new Student("lisi", 85),
                new Student("wangwu", 71), new Student("zhaoliu", 82));
//        list.sort(Student::compareByScore);
        list.sort((stu1,stu2)->{return stu1.getScore()-stu2.getScore();});
        System.out.println(list);
    }
}

