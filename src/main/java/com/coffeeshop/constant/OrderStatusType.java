package com.coffeeshop.constant;

/**
 * Order Status type constant
 * @author Chandan Vishwakarma
 */
public enum OrderStatusType {

    RECEIVED(0),
    BEING_COOKED(1),
    SERVED(2),
    CANCELLED(2);

    private int value;

    OrderStatusType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderStatusType parse(int id){
        OrderStatusType type = null;
        for (OrderStatusType item : OrderStatusType.values()){
            if(item.getValue() == id){
                type = item;
                break;
            }
        }
        return type;
    }
}
