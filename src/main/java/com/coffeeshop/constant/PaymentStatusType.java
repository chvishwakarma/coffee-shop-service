package com.coffeeshop.constant;

/**
 * Status type constant
 * @author Chandan Vishwakarma
 */
public enum PaymentStatusType {

    PAID(0),
    UNPAID(1);

    private int value;

    PaymentStatusType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PaymentStatusType parse(int id){
        PaymentStatusType type = null;
        for (PaymentStatusType item : PaymentStatusType.values()){
            if(item.getValue() == id){
                type = item;
                break;
            }
        }
        return type;
    }
}
