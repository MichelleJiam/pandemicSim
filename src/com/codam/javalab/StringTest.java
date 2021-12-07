package com.codam.javalab;

public class StringTest {
    String str = "";

    void    addToString(String s2) {
        str = str.concat(s2);
    }
    void    printString() {
        System.out.println("str is: " + str);
    }
}
