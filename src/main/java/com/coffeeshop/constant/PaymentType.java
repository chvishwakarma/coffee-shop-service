package com.coffeeshop.constant;

/**
 * Status type constant
 * @author Chandan Vishwakarma
 */
public enum PaymentType {

    CASH(0),
    CREDIT_CARD(1),
    DEBIT_CARD(2);

    private int value;

    PaymentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PaymentType parse(int id){
        PaymentType type = null;
        for (PaymentType item : PaymentType.values()){
            if(item.getValue() == id){
                type = item;
                break;
            }
        }
        return type;
    }
}
