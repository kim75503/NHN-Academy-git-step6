package com.nhnacademy.practice_problems_4;

public class Main {
    public static void main(String[] args) {
        /*
        문제 4: 역할에 따라 아래 메서드들을 올바른 클래스에 배치하세요.

        메서드 목록:

        printMenu() <- Main
        add(Todo todo) <- TodoService 
        getTitle() <- Todo
        getAll() <- TodoService
        readInput() <- Main
        setDone(boolean done) <- Todo

        Main	TodoService	Todo
        ?	     ?	         ?

        | Main          | TodoService      | Todo                    |
        | ------------- | ---------------- | ----------------------- |
        | `printMenu()` | `add(Todo todo)` | `getTitle()`            |
        | `readInput()` | `getAll()`       | `setDone(boolean done)` |

        **분류 기준**:
        - **Main**: UI 관련 (메뉴 출력, 입력 처리)
        - **TodoService**: 데이터 조작 (추가, 조회, 삭제)
        - **Todo**: 개별 항목의 상태 (필드 접근)
         */
    }
}
