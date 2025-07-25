package com.helpdesk.entities;

import com.helpdesk.enums.TypeUser;

public class Technical extends User{

    public Technical() {
        super();
    }

    public Technical(String name, String email) {
        super(name, email, TypeUser.TECNICO);
    }
}
