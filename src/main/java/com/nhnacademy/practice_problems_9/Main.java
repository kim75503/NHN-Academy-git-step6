package com.nhnacademy.practice_problems_9;

public class Main {
    public static void main(String[] args) {
        /*
        ###연습: 캡슐화 (private / public)
        ####문제 9: 캡슐화를 적용하여 Counter 클래스를 작성하세요.

        요구사항:

        count 필드 (외부 접근 차단)
        increment(): count를 1 증가
        getCount(): 현재 count 반환
        외부에서 counter.count = 100; 같은 직접 조작 불가능해야 함
        출력 결과:

        0
        1
        2
         */
        Counter counter = new Counter();

        System.out.println(counter.getCount()); 
        counter.increment();
        System.out.println(counter.getCount()); 
        counter.increment();
        System.out.println(counter.getCount()); 

    }
}

