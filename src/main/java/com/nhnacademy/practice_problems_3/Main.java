package com.nhnacademy.practice_problems_3;

public class Main {
    public static void main(String[] args) {
        /*
        ###연습: 클래스 역할 분리
        ####문제 3: 다음 코드에서 각 클래스의 역할에 맞지 않는 부분을 찾으세요.

        // Main.java
        public class Main {
            private ArrayList<Todo> todoList = new ArrayList<>();  // (A)

            public static void main(String[] args) {
                printMenu();
                String choice = reader.readLine();
                // ...
            }
        }

        // TodoService.java
        public class TodoService {
            public void printMenu() {  // (B)
                System.out.println("1. 등록");
                System.out.println("2. 조회");
            }
        }

        정답 : (A),(B)
        해설 :
        - (A) ArrayList<Todo>는 Main이 아닌 TodoService에 있어야 함
        - (B) printMenu()는 UI 역할이므로 Main에 있어야 함

        **올바른 분리**:
        | 클래스      | 역할          | 가져야 할 것                           |
        | ----------- | ------------- | -------------------------------------- |
        | Main        | UI, 입력 처리 | `printMenu()`, BufferedReader          |
        | TodoService | 비즈니스 로직 | `ArrayList<Todo>`, `add()`, `getAll()` |
        | Todo        | 데이터 모델   | 필드, getter/setter                    |

         */
    }
}
