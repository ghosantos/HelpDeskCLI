package com.helpdesk.model;

import com.helpdesk.model.enums.TypeUser;

public class Technical extends User{

    private Integer countCall;

    public Technical() {
        super();
    }

    public Technical(String name, String email) {
        super(name, email, TypeUser.TECNICO);
        this.countCall = 0;
    }

    public void IncrementCallCount(){
        countCall ++;
    }

    public void DecrementCallCount(){
        if (countCall > 0){
            countCall --;
        }
    }

    public Integer getCountCall() {
        return countCall;
    }

}
