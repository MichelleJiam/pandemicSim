package com.codam.javalab;

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

