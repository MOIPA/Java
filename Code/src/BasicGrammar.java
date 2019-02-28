import java.util.Random;
import java.util.Scanner;

public class BasicGrammar {

    public static void  main(String[] args){
        //1.与或非
        System.out.println(true | false); //true
        System.out.println(false | false); //false
        System.out.println(true & false); //false
        System.out.println(false & false); //false
        System.out.println(true & true); //true
        System.out.println(true || false); //true
        System.out.println(true && false); //false

        //&和&&都是逻辑运算符，都是判断两边同时真则为真，否则为假；但是&&当第一个条件不成之后，后面的条件都不执行了，
        // 而&则还是继续执行，直到整个条件语句执行完为止。如&&例子中的i++>5被执行了，而i++<9并没有被执行，
        // 这就是他们的差别。&例子中的i++>5和i++<9都被执行了。
        System.out.println(true ^ true); //false
        System.out.println(false ^ false); //false
        System.out.println(true ^ false); //true

        //2.三元运算符
        System.out.println(3>4?false:true); //true

        //3.input
//        Scanner scanner = new Scanner(System.in);
//        String inputStr = scanner.next();
//        System.out.println(inputStr);

        //4.random
        Random random = new Random();
        int randomNumber = random.nextInt(100);//指定[0 --多少)
        System.out.println(randomNumber);

        //5.array
        int[][] array1 = new int[3][1];
        System.out.println(array1[2]);
        String[] nameList = {"a","b","c","d","e","f","g"};
        int randNameIndex = random.nextInt(nameList.length);
        System.out.println(nameList[randNameIndex]);


        //7. 对象的堆地址和内部方法的this指针是同一个位置
        Student student = new Student();
        System.out.println(student);
        student.printThis();

        //8.继承的问题：
        /*
        * 成员变量：编译和运行都只看父类，如果父类没，编译失败，有运行父类变量。
        * 成员方法：编译时看父类有没有，运行时跑的是子类。
        *
        * 多态转型：想上转型：son和father对象类型不同，赋值时范围小的提升为范围大的，son提升为father
        * 好处：可以调用父类和子类的共有方法
        * 坏处：无法调用子类特有方法
        * 向下转型：就是想使用子类特有方法时使用强制转换   转换时最好instanceof判断一下
        */
        Father a = new Son();
        a.show();
        ((Son) a).sonAction();    //        直接a.sonAction();  错误
        System.out.println(a.j);

    }

    //6.overload重载   /与返回值无关，和参数列表相关
    public int getInfo(int i){
        return 1;
    }
    public float getInfo(float i){
        return 1.1f;
    }

}
class Student{
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printThis() {
        System.out.println(this);
    }

}
class Father{
    public int j=0;
    private int i=1;
    public void show(){
        System.out.println("father 's show"+this.i);
    }
}
class Son extends Father{
    private int i=2;
    public int j=11;
    public void show(){
        int i=4;
        System.out.println("son 's private num"+this.i+"/n son's internal i:"+i+"/n father's i:");
        super.show();
    }
    public void sonAction(){
        System.out.println("son action");
    }
}
