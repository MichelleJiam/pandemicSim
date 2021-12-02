package com.codam.javalab;

class IntegerTest {
    Integer num = 0;

    void    convertString(String s) {
        num = num.decode(s);
    }
    void    incrementNum(Integer n) {
        num += n;
    }
    void    printNum() {
        System.out.println("num is: " + num);
    }
}

public class Test {
    public static void main(String[] args) {
        IntegerTest intTest = new IntegerTest();

        if (args.length > 0) {
            intTest.convertString(args[0]);
            intTest.printNum();
            intTest.incrementNum(5);
            intTest.printNum();
        }
    }
}

