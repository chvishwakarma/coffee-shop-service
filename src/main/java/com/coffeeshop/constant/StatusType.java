package com.coffeeshop.constant;

/**
 * Status type constant
 * @author Chandan Vishwakarma
 */
public enum StatusType {

    ACTIVE(0),
    INACTIVE(1),
    BLOCKED(2);

    private int value;

    StatusType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StatusType parse(int id){
        StatusType type = null;
        for (StatusType item : StatusType.values()){
            if(item.getValue() == id){
                type = item;
                break;
            }
        }
        return type;
    }
}
