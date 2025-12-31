package com.nhnacademy.Summary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Main.java - TodoService가 ArrayList인지 LinkedList인지 모름
public class Main {
    public static void main(String[] args) throws IOException {
        TodoService service = new TodoService();  // ADT 생성
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            printMenu();
            String choice = reader.readLine();

            switch (choice) {
                case "1":
                    // ADT의 연산 호출 (내부 구현 몰라도 됨)
                    //service.add(new Todo(...));
                    break;
                case "2":
                    service.printAll();
                    break;
            }
        }
    }

    private static void printMenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

class TodoService {
    private List<Todo> todoList = new ArrayList<>();

    // 등록
    public void add(Todo todo) {
        todoList.add(todo);
        System.out.println("등록 완료: " + todo.getTitle());
    }

    // 전체 조회
    public List<Todo> getAll() {
        return todoList;
    }

    // 목록 출력
    public void printAll() {
        if (todoList.isEmpty()) {
            System.out.println("등록된 TODO가 없습니다.");
            return;
        }

        System.out.println("=== TODO 목록 ===");
        for (int i = 0; i < todoList.size(); i++) {
            Todo todo = todoList.get(i);
            //String status = todo.isDone() ? "[완료]" : "[미완료]";
            System.out.printf("%d. %s %s (%d시간)%n");
             //   i + 1, status, todo.getTitle(), todo.getHours());
        }
    }

    // 개수 조회
    public int count() {
        return todoList.size();
    }
}

class Todo {
    private String title;
  
    // 생성자에서 this 사용
    public Todo(String title) {
        this.title = title;  // this.title = 필드, title = 매개변수
    }

    // Setter에서 this 사용
    public void setTitle(String title) {
        this.title = title;
    }

     public String getTitle() {
        return this.title;
    }
}
