package com.helpdesk.model;

import com.helpdesk.model.enums.TypeUser;

public class Client extends User{

    public Client() {
        super();
    }

    public Client(String name, String email) {
        super(name, email, TypeUser.CLIENTE);
    }
}
