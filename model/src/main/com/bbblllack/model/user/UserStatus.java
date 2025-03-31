package com.bbblllack.model.user;

public enum UserStatus {
    ACTIVE(0), INACTIVE(1), ILLEGAL(-1);
    private int value;
    UserStatus(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}
