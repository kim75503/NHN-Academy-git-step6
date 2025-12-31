# Step 6: Class separation (Main / Todo / Todo Service)
---

**사전 지식**:
- Step 4 내용: Todo 클래스, ArrayList 사용
- 메서드 호출 개념

**학습 목표**:
- 역할에 따른 클래스 분리
- 단일 책임 원칙 이해
- 메서드 추출 및 호출

**핵심 내용**:
- Main 클래스: 메뉴 UI, 사용자 입력 처리
- Todo 클래스: 데이터 모델 (필드 + 생성자)
- TodoService 클래스: 등록, 조회 로직
- `this` 키워드
- 패키지 구조 (`model`, `service`)

**실습 과제**:
1. Main에서 메뉴 처리만 담당
2. TodoService에 add(), getAll() 구현
3. Main에서 TodoService 호출

**산출물**:
- [ ] 패키지 구조 (model, service)
- [ ] TodoService.java
- [ ] Main에서 Service 호출 동작


## 학습 자료

<details>
<summary><strong>Abstract Data Type (ADT)</strong></summary>

**ADT란?**
- 데이터와 그 데이터에 대한 연산을 하나로 묶은 것
- **내부 구현을 숨기고** 연산(메서드)만 외부에 공개
- 사용자는 **"무엇을 할 수 있는가"** 만 알면 됨 (어떻게 구현되었는지는 몰라도 됨)

**ADT의 구성 요소**:
| 구성 요소  | 설명                      | 예시 (TodoService)              |
| ---------- | ------------------------- | ------------------------------- |
| **데이터** | 내부에 저장되는 값        | `List<Todo> todoList`           |
| **연산**   | 데이터를 조작하는 메서드  | `add()`, `delete()`, `getAll()` |
| **캡슐화** | 데이터를 private으로 숨김 | `private List<Todo> todoList`   |

**ADT 예시: TodoService**:
```java
public class TodoService {
    // 데이터 (외부에서 직접 접근 불가)
    private List<Todo> todoList = new ArrayList<>();

    // 연산 (외부에서 사용 가능)
    public void add(Todo todo) { ... }
    public void delete(int index) { ... }
    public List<Todo> getAll() { ... }
    public Todo get(int index) { ... }
}
```

**왜 ADT가 중요한가?**
```java
// 사용자(Main)는 내부 구현을 몰라도 됨
TodoService service = new TodoService();
service.add(new Todo("공부", 3, false));  // 어떻게 저장되는지 몰라도 OK
service.delete(0);                         // 어떻게 삭제되는지 몰라도 OK

// 나중에 ArrayList → LinkedList로 바꿔도 Main은 수정 불필요!
```

**ADT의 장점**:
- **정보 은닉**: 내부 구현 변경이 외부에 영향 없음
- **모듈화**: 각 ADT가 독립적으로 개발/테스트 가능
- **재사용**: 다른 프로젝트에서도 그대로 사용 가능

+ 빠른 기능 구현이 중요 세부적 디테일은 알 필요 없다.
</details>

<details>
<summary><strong>Design class with ADTs</strong></summary>

**3개 클래스의 ADT 역할**:
| 클래스        | ADT 관점         | 제공하는 연산                               |
| ------------- | ---------------- | ------------------------------------------- |
| `Todo`        | 데이터 타입 정의 | `getTitle()`, `setDone()` 등                |
| `TodoService` | TODO 목록 ADT    | `add()`, `delete()`, `getAll()`, `search()` |
| `Main`        | ADT 사용자       | Service의 연산만 호출                       |

**Main은 내부 구현을 모름**:
```java
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
                    service.add(new Todo(...));
                    break;
                case "2":
                    service.printAll();
                    break;
            }
        }
    }
}
```

**잘못된 설계 (ADT 위반)**:
```java
// ❌ 내부 데이터를 직접 노출
public class TodoService {
    public List<Todo> todoList = new ArrayList<>();  // public!
}

// Main에서 내부를 직접 조작 → 캡슐화 깨짐
service.todoList.add(todo);
service.todoList.remove(0);
```

**올바른 설계 (ADT 준수)**:
```java
// ✅ 데이터 숨기고 연산만 공개
public class TodoService {
    private List<Todo> todoList = new ArrayList<>();  // private!

    public void add(Todo todo) { todoList.add(todo); }
    public void delete(int index) { todoList.remove(index); }
}

// Main은 연산만 사용
service.add(todo);
service.delete(0);
```

</details>

<details>
<summary><strong>📘 패키지 구조 설계</strong></summary>

**권장 패키지 구조**:
```
src/main/java/
└── com/example/todo/
    ├── Main.java           # 진입점, UI
    ├── model/
    │   └── Todo.java       # 데이터 모델
    └── service/
        └── TodoService.java # 비즈니스 로직
```

**패키지 선언**:
```java
// Todo.java
package com.example.todo.model;

public class Todo {
    // ...
}
```

```java
// TodoService.java
package com.example.todo.service;

import com.example.todo.model.Todo;
import java.util.ArrayList;

public class TodoService {
    // ...
}
```

```java
// Main.java
package com.example.todo;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;

public class Main {
    // ...
}
```

</details>

<details>
<summary><strong>📘 TodoService 클래스 구현</strong></summary>

**TodoService.java**:
```java
package com.example.todo.service;

import com.example.todo.model.Todo;
import java.util.ArrayList;
import java.util.List;

public class TodoService {
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
            String status = todo.isDone() ? "[완료]" : "[미완료]";
            System.out.printf("%d. %s %s (%d시간)%n",
                i + 1, status, todo.getTitle(), todo.getHours());
        }
    }

    // 개수 조회
    public int count() {
        return todoList.size();
    }
}
```

</details>

<details>
<summary><strong>📘 this 키워드</strong></summary>

**this란?**
- 현재 오브젝트 자신을 가리키는 참조
- 필드와 매개변수 이름이 같을 때 구분에 사용

**사용 예시**:
```java
public class Todo {
    private String title;

    // 생성자에서 this 사용
    public Todo(String title) {
        this.title = title;  // this.title = 필드, title = 매개변수
    }

    // Setter에서 this 사용
    public void setTitle(String title) {
        this.title = title;
    }
}
```

**this 없이 작성하면?**:
```java
public void setTitle(String title) {
    title = title;  // 매개변수에 매개변수를 대입 (의미 없음!)
}
```

</details>

---

## 연습 문제

### 연습: 패키지와 import

**문제 1**: 다음 패키지 구조에 맞게 TodoService 클래스의 패키지 선언과 import 문을 작성하세요.

**패키지 구조**:
```
src/main/java/
└── com/example/todo/
    ├── Main.java
    ├── model/
    │   └── Todo.java
    └── service/
        └── TodoService.java  ← 이 파일 작성
```

**요구사항**:
1. TodoService는 `com.example.todo.service` 패키지에 속함
2. Todo 클래스와 ArrayList를 사용함

<details>
<summary><strong>정답 보기</strong></summary>

```java
package com.example.todo.service;

import com.example.todo.model.Todo;
import java.util.ArrayList;

public class TodoService {
    private ArrayList<Todo> todoList = new ArrayList<>();
}
```

**설명**:
- `package`: 현재 클래스가 속한 패키지 선언 (파일 최상단, 반드시 첫 줄)
- `import`: 다른 패키지의 클래스 사용 선언 (package 선언 다음)
- 같은 패키지 내 클래스는 import 없이 사용 가능

</details>

---

**문제 2**: 다음 패키지 구조에서 Main.java가 Todo와 TodoService를 사용하려면 어떤 import 문이 필요한지 작성하세요.

```
com/example/todo/
├── Main.java
├── model/
│   └── Todo.java
└── service/
    └── TodoService.java
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
package com.example.todo;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;

public class Main {
    // ...
}
```

</details>

---

### 연습: 클래스 역할 분리

**문제 3**: 다음 코드에서 각 클래스의 역할에 맞지 않는 부분을 찾으세요.

```java
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
```

<details>
<summary><strong>정답 보기</strong></summary>

**문제점**:
- **(A)** `ArrayList<Todo>`는 Main이 아닌 **TodoService**에 있어야 함
- **(B)** `printMenu()`는 UI 역할이므로 **Main**에 있어야 함

**올바른 분리**:
| 클래스      | 역할          | 가져야 할 것                           |
| ----------- | ------------- | -------------------------------------- |
| Main        | UI, 입력 처리 | `printMenu()`, BufferedReader          |
| TodoService | 비즈니스 로직 | `ArrayList<Todo>`, `add()`, `getAll()` |
| Todo        | 데이터 모델   | 필드, getter/setter                    |

</details>

---

**문제 4**: 역할에 따라 아래 메서드들을 올바른 클래스에 배치하세요.

메서드 목록:
- `printMenu()`
- `add(Todo todo)`
- `getTitle()`
- `getAll()`
- `readInput()`
- `setDone(boolean done)`

| Main | TodoService | Todo |
| ---- | ----------- | ---- |
| ?    | ?           | ?    |

<details>
<summary><strong>정답 보기</strong></summary>

| Main          | TodoService      | Todo                    |
| ------------- | ---------------- | ----------------------- |
| `printMenu()` | `add(Todo todo)` | `getTitle()`            |
| `readInput()` | `getAll()`       | `setDone(boolean done)` |

**분류 기준**:
- **Main**: UI 관련 (메뉴 출력, 입력 처리)
- **TodoService**: 데이터 조작 (추가, 조회, 삭제)
- **Todo**: 개별 항목의 상태 (필드 접근)

</details>

---

### 연습: 메서드 호출

**문제 5**: Main에서 TodoService를 사용하여 TODO를 등록하고 개수를 출력하는 프로그램을 작성하세요.

**요구사항**:
1. TodoService 오브젝트 생성
2. "공부" (3시간, 미완료) Todo 생성 후 service에 추가
3. 등록된 TODO 개수 출력

**출력 결과**:
```
등록된 TODO: 1개
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TodoService service = new TodoService();

        Todo todo = new Todo("공부", 3, false);
        service.add(todo);

        List<Todo> list = service.getAll();
        System.out.println("등록된 TODO: " + list.size() + "개");
    }
}
```

**설명**:
- `new TodoService()`: Service 오브젝트 생성
- `service.add(todo)`: Service의 add 메서드로 Todo 등록
- `service.getAll()`: 등록된 모든 Todo 목록 반환

</details>

---

**문제 6**: TodoService를 사용하여 TODO 2개를 등록하고, 개수와 첫 번째 TODO의 제목을 출력하는 프로그램을 작성하세요.

**요구사항**:
1. TodoService 생성
2. "Java" (2시간, 미완료), "Spring" (3시간, 미완료) 추가
3. `count()` 메서드로 개수 출력
4. `getAll().get(0).getTitle()`로 첫 번째 제목 출력

**출력 결과**:
```
2
Java
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class Main {
    public static void main(String[] args) {
        TodoService service = new TodoService();
        service.add(new Todo("Java", 2, false));
        service.add(new Todo("Spring", 3, false));

        System.out.println(service.count());
        System.out.println(service.getAll().get(0).getTitle());
    }
}
```

**설명**:
- `count()`: 등록된 TODO 개수 반환
- `getAll()`: List<Todo> 반환 → `get(0)`으로 첫 번째 요소 → `getTitle()`로 제목

</details>

---

### 연습: this 키워드

**문제 7**: 다음 코드에서 `this`가 필요한 곳을 찾아 수정하세요.

```java
public class Book {
    private String title;
    private int price;

    public Book(String title, int price) {
        title = title;
        price = price;
    }

    public void setPrice(int price) {
        price = price;
    }
}
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class Book {
    private String title;
    private int price;

    public Book(String title, int price) {
        this.title = title;   // this 추가
        this.price = price;   // this 추가
    }

    public void setPrice(int price) {
        this.price = price;   // this 추가
    }
}
```

**문제점**: 매개변수와 필드 이름이 같을 때 `this` 없이 사용하면 매개변수를 자기 자신에게 대입하는 것이 됨

</details>

---

**문제 8**: `this` 키워드를 사용하여 Book 클래스의 생성자와 setter를 작성하세요.

**요구사항**:
1. 필드: `title` (String), `price` (int)
2. 생성자: 두 필드를 초기화
3. `setPrice(int price)`: 가격 변경
4. `getInfo()`: "제목 - 가격원" 형식 반환

**출력 결과**:
```
Java 입문 - 25000원
Java 입문 - 30000원
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class Book {
    private String title;
    private int price;

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInfo() {
        return this.title + " - " + this.price + "원";
    }

    public static void main(String[] args) {
        Book book = new Book("Java 입문", 25000);
        System.out.println(book.getInfo());

        book.setPrice(30000);
        System.out.println(book.getInfo());
    }
}
```

**설명**:
- `this.title = title`: 필드 `title`에 매개변수 `title` 값 대입
- `this`가 없으면 매개변수를 자기 자신에게 대입하는 무의미한 코드가 됨
- `getInfo()`에서 `this`는 생략 가능하지만, 필드임을 명확히 표현

</details>

---

### 연습: 캡슐화 (private / public)

**문제 9**: 캡슐화를 적용하여 Counter 클래스를 작성하세요.

**요구사항**:
1. `count` 필드 (외부 접근 차단)
2. `increment()`: count를 1 증가
3. `getCount()`: 현재 count 반환
4. 외부에서 `counter.count = 100;` 같은 직접 조작 불가능해야 함

**출력 결과**:
```
0
1
2
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class Counter {
    private int count = 0;  // private으로 외부 접근 차단

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        System.out.println(counter.getCount());  // 0

        counter.increment();
        System.out.println(counter.getCount());  // 1

        counter.increment();
        System.out.println(counter.getCount());  // 2

        // counter.count = 100;  // 컴파일 에러! (private)
    }
}
```

**캡슐화의 장점**:
- 외부에서 `count`를 직접 수정할 수 없음
- 값 변경은 반드시 `increment()` 메서드를 통해서만 가능
- 데이터 무결성 보장

</details>

---

**문제 10**: 캡슐화 원칙에 따라 TodoService 클래스를 작성하세요.

**요구사항**:
1. Todo 목록을 저장하는 필드 (외부 접근 차단)
2. `add(Todo todo)` 메서드 (외부에서 호출 가능)
3. `getAll()` 메서드 (외부에서 호출 가능)

**테스트 코드**:
```java
TodoService service = new TodoService();
service.add(new Todo("공부", 2, false));
System.out.println(service.getAll().size());  // 1
// service.todoList.clear();  // 컴파일 에러여야 함!
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
import java.util.ArrayList;
import java.util.List;

public class TodoService {
    private List<Todo> todoList = new ArrayList<>();

    public void add(Todo todo) {
        todoList.add(todo);
    }

    public List<Todo> getAll() {
        return todoList;
    }
}
```

**캡슐화 규칙**:
- **필드**: `private` (외부에서 직접 접근 차단)
- **메서드**: `public` (외부에서 사용할 수 있도록 공개)

**왜 필드를 private으로?**
- 외부에서 `service.todoList.clear()` 같은 위험한 조작 방지
- 데이터 변경은 반드시 메서드를 통해서만 가능

</details>

---

### 연습: TodoService 구현

**문제 11**: TodoService에 `delete(int index)` 메서드를 추가하세요.

```java
public class TodoService {
    private List<Todo> todoList = new ArrayList<>();

    public void add(Todo todo) {
        todoList.add(todo);
    }

    // 여기에 delete 메서드 작성
}
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class TodoService {
    private List<Todo> todoList = new ArrayList<>();

    public void add(Todo todo) {
        todoList.add(todo);
    }

    public void delete(int index) {
        todoList.remove(index);
    }
}
```

**사용 예**:
```java
service.add(new Todo("공부", 2, false));
service.add(new Todo("운동", 1, false));
service.delete(0);  // 첫 번째 항목 삭제
```

</details>

---

**문제 12**: TodoService에 특정 인덱스의 Todo를 반환하는 `get(int index)` 메서드를 추가하세요.

<details>
<summary><strong>정답 보기</strong></summary>

```java
public Todo get(int index) {
    return todoList.get(index);
}
```

**사용 예**:
```java
service.add(new Todo("Java", 3, false));
Todo todo = service.get(0);
System.out.println(todo.getTitle());  // "Java"
```

</details>

---

### 종합 연습

**문제 13**: 다음 요구사항에 맞게 ProductService 클래스를 작성하세요.

요구사항:
1. `Product` 목록을 저장하는 private 필드
2. `add(Product product)` 메서드
3. `getAll()` 메서드
4. `count()` 메서드

```java
// Product 클래스는 주어짐
public class Product {
    private String name;
    private int price;
    // 생성자, getter 생략
}
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> productList = new ArrayList<>();

    public void add(Product product) {
        productList.add(product);
    }

    public List<Product> getAll() {
        return productList;
    }

    public int count() {
        return productList.size();
    }
}
```

**사용 예**:
```java
ProductService service = new ProductService();
service.add(new Product("키보드", 50000));
service.add(new Product("마우스", 30000));
System.out.println("상품 수: " + service.count());  // 2
```

</details>

---

**문제 14**: Main에서 TodoService를 사용하는 메뉴 프로그램을 작성하세요.

**요구사항**:
1. TodoService 오브젝트 생성
2. 메뉴: 1.등록 2.조회 0.종료
3. 등록: 할 일과 시간을 입력받아 Todo 생성 후 service에 추가
4. 조회: service의 printAll() 호출
5. 종료: 프로그램 종료

**실행 예**:
```
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
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TodoService service = new TodoService();

        while (true) {
            System.out.println("1. 등록  2. 조회  0. 종료");
            System.out.print("선택 > ");
            String choice = reader.readLine();

            switch (choice) {
                case "1":
                    System.out.print("할 일: ");
                    String title = reader.readLine();
                    System.out.print("시간: ");
                    int hours = Integer.parseInt(reader.readLine());
                    service.add(new Todo(title, hours, false));
                    break;

                case "2":
                    service.printAll();
                    break;

                case "0":
                    System.out.println("종료합니다.");
                    return;
            }
        }
    }
}
```

**설명**:
- `while (true)`: 메뉴를 반복 표시
- `switch`: 사용자 선택에 따라 분기
- `service.add(new Todo(...))`: Todo 생성과 동시에 service에 추가
- `return`: main 메서드 종료 → 프로그램 종료

</details>

---

[← 이전: Step 5](step_05.md) | [목차](00.overview.md) | [다음: Step 7 →](step_07.md)