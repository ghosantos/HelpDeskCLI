package com.helpdesk.entities;

import com.helpdesk.enums.TypeUser;

public abstract class User {
    private String name;
    private String email;
    private TypeUser typeUser;

    public User(){}

    public User(String name, String email, TypeUser typeUser) {
        this.name = name;
        this.email = email;
        this.typeUser = typeUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }
}
