package com.tr.BasicJava;

public class SwitchCase {
    public static void main(String[] args) {
//        String num = "time";
//        switch (num) {
//            case "time":
//                System.out.println("11.1");
//                break;
//        }
        //only support int byte char enum
        printPramid(5);
    }

    public static void printPramid(int layerNumer){
        for (int i = 1; i <= layerNumer; i++){
            for (int k = 0; k < (layerNumer - i); k++) {
                System.out.print(" ");
            }
            for (int j = 0; j < (i-1)*2+1; j++) {
                if(j==0||j==(i-1)*2||i==layerNumer)
                    System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }
}
