package com.codam.javalab;

public class IntegerTest {
    Integer num = 0;

    void    convertString(String s) {
        num = Integer.decode(s);
    }
    void    incrementNum(int n) {
        num += Integer.valueOf(n);
    }
    void    printNum() {
        System.out.println("num is: " + num);
    }
}
