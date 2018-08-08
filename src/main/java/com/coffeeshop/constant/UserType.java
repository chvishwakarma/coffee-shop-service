package com.coffeeshop.constant;

/**
 * User type constant
 * @author Chandan Vishwakarma
 */
public enum UserType {

    ADMIN(0),
    STAFF(1);

    private int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserType parse(int id){
        UserType type = null;
        for (UserType item : UserType.values()){
            if(item.getValue() == id){
                type = item;
                break;
            }
        }
        return type;
    }
}
