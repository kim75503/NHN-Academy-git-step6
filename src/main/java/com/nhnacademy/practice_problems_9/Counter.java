package com.nhnacademy.practice_problems_9;


public class Counter {

    private int count = 0;  // 외부 접근 차단

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

