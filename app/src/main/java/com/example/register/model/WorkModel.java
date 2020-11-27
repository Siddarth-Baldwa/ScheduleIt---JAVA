package com.example.register.model;

public class WorkModel {

    String work_name;
    Integer id;
    Integer imageurl;

    public WorkModel(String work_name ,Integer id, Integer imageurl) {
        this.id = id;
        this.imageurl = imageurl;
        this.work_name = work_name;
    }
    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
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
