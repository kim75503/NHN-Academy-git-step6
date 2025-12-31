package com.nhnacademy.practice_problems_5;

import java.util.List;

import com.nhnacademy.practice_problems_1.model.Todo;
import com.nhnacademy.practice_problems_1.service.TodoService;

public class Main {
    public static void main(String[] args) {
        /*
        ###연습: 메서드 호출
        ####문제 5: Main에서 TodoService를 사용하여 TODO를 등록하고 개수를 출력하는 프로그램을 작성하세요.

        요구사항:

        TodoService 오브젝트 생성
        "공부" (3시간, 미완료) Todo 생성 후 service에 추가
        등록된 TODO 개수 출력
        
        출력 결과:

        등록된 TODO: 1개
         */
        TodoService service = new TodoService();

        Todo todo = new Todo("공부", 3, false);
        service.add(todo);

        List<Todo> list = service.getAll();
        System.out.println("등록된 TODO: " + list.size() +"개");


    }
}
