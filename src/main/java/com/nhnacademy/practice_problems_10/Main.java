package com.nhnacademy.practice_problems_10;

import com.nhnacademy.practice_problems_1.model.Todo;
import com.nhnacademy.practice_problems_1.service.TodoService;

public class Main {
    public static void main(String[] args) {
        /*
        ####문제 10: 캡슐화 원칙에 따라 TodoService 클래스를 작성하세요.

        요구사항:

        Todo 목록을 저장하는 필드 (외부 접근 차단)
        add(Todo todo) 메서드 (외부에서 호출 가능)
        getAll() 메서드 (외부에서 호출 가능)
        테스트 코드:

        TodoService service = new TodoService();
        service.add(new Todo("공부", 2, false));
        System.out.println(service.getAll().size());  // 1
        // service.todoList.clear();  // 컴파일 에러여야 함!
         */

        TodoService service = new TodoService();
        service.add(new Todo("공부", 2, false));
        System.out.println(service.getAll().size());  
        // service.todoList.clear();  // 컴파일 에러여야 함!
    }
}
