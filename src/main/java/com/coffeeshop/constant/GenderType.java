package com.coffeeshop.constant;

/**
 * Gender type constant
 * @author Chandan Vishwakarma
 */
public enum GenderType {

    MALE(0),
    FEMALE(1),
    OTHERS(2);

    private int value;

    GenderType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static GenderType parse(int id){
        GenderType type = null;
        for (GenderType item : GenderType.values()){
            if(item.getValue() == id){
                type = item;
                break;
            }
        }
        return type;
    }
}
