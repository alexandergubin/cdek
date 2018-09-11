package com.gubin.cdek.form;

public class UserForm {

    private String name;

    public UserForm() {
    }

    public UserForm(long id, String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
