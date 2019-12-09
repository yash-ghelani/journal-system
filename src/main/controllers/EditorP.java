package main.controllers;

public class EditorP {
    private String name;
    private String title;

    EditorP(String name,String title){
        this.name = name;
        this.title = title;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }
}

