package com.nhnacademy.practice_problems_1.service;

import java.util.ArrayList;

import com.nhnacademy.practice_problems_1.model.Todo;

public class TodoService {

    private ArrayList<Todo> todoList = new ArrayList<>();


    // Todo 추가
    public void add(Todo todo) {
        todoList.add(todo);
    }

    // 모든 Todo 조회
    public ArrayList<Todo> getAll() {
        return todoList;
    }

    public int count(){
        return todoList.size();
    }

    public void delete(int index){
        todoList.remove(index);
    }

    public Todo get(int index){
        return todoList.get(index);
    }

    public void printAll(){
        boolean isDoen;
        String doit;
         if(todoList.isEmpty()){
            System.out.println("대기열이 비어있습니다");
            return;
        } 
                    
        System.out.println("=== TODO 목록 ===");
            for(int i = 0 ; i< todoList.size(); i++){
                isDoen = todoList.get(i).getisDone();
                if(isDoen)
                    doit = "완료";
                else
                    doit = "미완료";
            System.out.println(i+1 + ". [" + doit+"] "+todoList.get(i).getTitle() + "("+todoList.get(i).getHour()+"시간)");
        }
    }   
}

