package com.tr.BasicJava;

public class Josephu {

    final static int n = 11;

    public static void main(String[] args) {

        int[] numlist = new int[n];
        setNumber(numlist, n);



    }

    public static void setNumber(int[] numList, int maxNumber) {
        for (int i = 0; i < maxNumber; ) {
            numList[i] = ++i;
        }
    }
    public static int callMan(int[] list,int callNumber,int outNumber) {
//        if(list==1) return list[0];
        //list[callNumber-1]的开始报数为1，下一个增加直到为outNUMber
        int nextPerson = callNumber - 1;
        for (int i = 0; i < outNumber; i++) {
            nextPerson = (nextPerson+1)%list.length;
        }
        System.out.println(list[nextPerson]);
        //出队
//        for (int i=)
        //
//        callMan(list, nextPerson, outNumber);
        return -1;
    }
}

