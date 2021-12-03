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

class StringTest {
    String str = "";

    void    addToString(String s2) { str = str.concat(s2); }
    void    printString() { System.out.println("str is: " + str); }
}

public class Test {
    public static void main(String[] args) {
        IntegerTest intTest = new IntegerTest();

        if (args.length > 1 && args[0].contains("i")) {
            intTest.convertString(args[1]);
            intTest.printNum();
            intTest.incrementNum(5);
            intTest.printNum();
        }

        StringTest  strTest = new StringTest();

        if (args.length > 1 && args[0].contains("s")) {
            strTest.addToString("Hello");
            strTest.printString();
            strTest.addToString(" " + args[1]);
            strTest.printString();
        }
    }
}

