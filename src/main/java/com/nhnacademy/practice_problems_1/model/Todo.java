package com.nhnacademy.practice_problems_1.model;

public class Todo {
    private String title;
    private int hour =0 ;
    private boolean isDone = false;
  
    // 생성자에서 this 사용
    public Todo(String title) {
        this.title = title;  // this.title = 필드, title = 매개변수
    }

    public Todo(String title, int hour) {
        this.title = title;  // this.title = 필드, title = 매개변수
        this.hour = hour;
    }

    public Todo(String title, int hour, boolean isDone) {
        this.title = title;  // this.title = 필드, title = 매개변수
        this.hour = hour;
        this.isDone = isDone;
    }

    // Setter에서 this 사용
    public void setTitle(String title) {
        this.title = title;
    }

     public String getTitle() {
        return this.title;
    }

    public int getHour() {
        return this.hour;
    }

    public boolean getisDone() {
        return this.isDone;
    }
}