package com.nhnacademy.practice_problems_8;

public class Main {
    public static void main(String[] args) {
        /*
        문제 8: this 키워드를 사용하여 Book 클래스의 생성자와 setter를 작성하세요.

        요구사항:

        필드: title (String), price (int)
        생성자: 두 필드를 초기화
        setPrice(int price): 가격 변경
        getInfo(): "제목 - 가격원" 형식 반환
        출력 결과:

        Java 입문 - 25000원
        Java 입문 - 30000원
         */

        Book book = new Book("Java 입문", 25000);
        
        System.out.println(book.getInfo());

        book.setPrice(30000);
        System.out.println(book.getInfo());
    }
} 
