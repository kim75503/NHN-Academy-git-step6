package com.nhnacademy.practice_problems_6;

import com.nhnacademy.practice_problems_1.model.Todo;
import com.nhnacademy.practice_problems_1.service.TodoService;

public class Main {
    public static void main(String[] args) {
        /*
        문제 6: TodoService를 사용하여 TODO 2개를 등록하고, 개수와 첫 번째 TODO의 제목을 출력하는 프로그램을 작성하세요.

        요구사항:

        TodoService 생성
        "Java" (2시간, 미완료), "Spring" (3시간, 미완료) 추가
        count() 메서드로 개수 출력
        getAll().get(0).getTitle()로 첫 번째 제목 출력
        출력 결과:

        2
        Java

         */

        TodoService service = new TodoService();
        Todo todo1 = new Todo("Java", 2, false);
        Todo todo2 = new Todo("Spring", 3, false);
        service.add(todo1);
        service.add(todo2);

        System.out.println(service.count());
        System.out.println(service.getAll().get(0).getTitle());


        
    }
}
