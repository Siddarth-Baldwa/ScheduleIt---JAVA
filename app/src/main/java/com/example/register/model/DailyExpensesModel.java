package com.example.register.model;


public class DailyExpensesModel {

    String expenses_name;
    Integer id;
    Integer imageurl;

    public DailyExpensesModel(String expenses_name ,Integer id, Integer imageurl) {
        this.expenses_name = expenses_name;
        this.id = id;
        this.imageurl = imageurl;
    }
    public String getExpenses_name() {
        return expenses_name;
    }

    public void setExpenses_name(String expenses_name) {
        this.expenses_name = expenses_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImageurl() {
        return imageurl;
    }

    public void setImageurl(Integer imageurl) {
        this.imageurl = imageurl;
    }

}
