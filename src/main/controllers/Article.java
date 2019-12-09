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
    private String verdict;

    public Article(String name){
        this.name=name;
        this.verdict=verdict;

        this.checkbox = new CheckBox();
    }


    public String getName(){
        return name;
    }

    public CheckBox getCheckbox(){
        return checkbox;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getVerdict(){
        return name;
    }

    public void setVerdict(String verdict){
        this.name = verdict;
    }

    public void setCheckbox(CheckBox checkbox){
        this.checkbox = checkbox;
    }


}
