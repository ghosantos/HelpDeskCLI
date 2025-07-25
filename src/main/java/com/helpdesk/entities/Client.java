package com.helpdesk.entities;

import com.helpdesk.enums.TypeUser;

public class Client extends User{

    public Client() {
        super();
    }

    public Client(String name, String email) {
        super(name, email, TypeUser.CLIENTE);
    }
}
