package com.example.nabila.nabila_1202150253_modul5;

public class ListData {
    String todo, desc, prio;

    public ListData(String todo, String desc, String prio){
        this.todo = todo;
        this.desc = desc;
        this.prio = prio;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }
}

