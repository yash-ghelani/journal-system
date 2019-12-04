package main.controllers;


import java.io.File;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class Article {

    private String name;
    private String code;
    private CheckBox checkbox;

    public Article(String name,String code){
        this.name=name;
        this.code = code;
        this.checkbox = new CheckBox();
    }


    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }

    public CheckBox getCheckbox(){
        return checkbox;
    }


    public void setName(String name){
        this.name = name;
    }

    public void setCode(String code){
        this.code = code;
    }

    public void setCheckbox(CheckBox checkbox){
        this.checkbox = checkbox;
    }


}
