//import sun.tools.java.ScannerInputReader;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;

public class learnHashSet {

    public static void main(String[] args){
        Collection<String> set1 = new HashSet<String>();
        String a1 = "tr";
        String a2 = "tzq";
        String a3 = "tr";
        set1.add(a1);
        set1.add(a2);
        set1.add(a3);
//        set1.remove(a1);
        Iterator<String> iterator = set1.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Student a = Student.INSTANCE;
        a.sayHello();

//        try {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
    public enum Student{
        INSTANCE;
        private int age=10;
        public void sayHello(){
            System.out.println("hi"+age);
        }
    }
}
