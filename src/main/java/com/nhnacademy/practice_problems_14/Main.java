package com.nhnacademy.practice_problems_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.nhnacademy.practice_problems_1.model.Todo;
import com.nhnacademy.practice_problems_1.service.TodoService;

public class Main {
    public static void main(String[] args) throws IOException{
        /*
        ####문제 14: Main에서 TodoService를 사용하는 메뉴 프로그램을 작성하세요.

        요구사항:

        TodoService 오브젝트 생성
        메뉴: 1.등록 2.조회 0.종료
        등록: 할 일과 시간을 입력받아 Todo 생성 후 service에 추가
        조회: service의 printAll() 호출
        종료: 프로그램 종료
        실행 예:

        1. 등록  2. 조회  0. 종료
        선택 > 1
        할 일: Java 공부
        시간: 3
        등록 완료: Java 공부

        1. 등록  2. 조회  0. 종료
        선택 > 2
        === TODO 목록 ===
        1. [미완료] Java 공부 (3시간)

        1. 등록  2. 조회  0. 종료
        선택 > 0
        종료합니다.
         */

        TodoService service = new TodoService();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean stopped =true, isDoen;

        String task, doit;
        int hour;

        while(stopped){
            System.out.println("메뉴: 1.등록 2.조회 0. 종료");
            System.out.print("선택: ");
            int sel = Integer.parseInt(reader.readLine());
            
            switch(sel){
                case 1 : {
                    
                    System.out.print("할 일: ");
                     task = reader.readLine();
                    System.out.print("시간: ");
                     hour = Integer.parseInt(reader.readLine());
                     Todo todo = new Todo(task,hour);
                     service.add(todo);
                     System.out.println("등록 완료: " + task);
                     break;}
                case 2 : {
                    service.printAll();
                    break;
                }
                case 0 : {
                    System.out.println("종료합니다.");
                    stopped = false;
                    break;
                }
                    
            }
            System.out.println();
        }
    }
}
