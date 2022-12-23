package edu.cis.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order {
    private String itemID;
    private String type;
    private String orderID;

    private static ArrayList<String> existingIds = new ArrayList<>();

    public Order(String itemID, String type, String  orderID) throws Exception {
        setOrderID(orderID);
        setType(type);
        setItemID(itemID);
    }

    @Override
    public String toString() {
        return "\n\nOrder{" +
                "itemID='" + itemID + '\'' +
                ", type='" + type + '\'' +
                ", orderID='" + orderID + '\'' +
                '}';
    }

    public String getItemID() {return itemID;}
    public void setItemID(String s) {itemID = s;}

    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String s) throws Exception {
        if (existingIds.contains(s)){
            throw new Exception(CISConstants.DUP_ORDER_ERR);
        }

        if (orderID.isEmpty()){
            orderID = s;
            existingIds.add(s);
        } else if (existingIds.contains(orderID)){
            existingIds.remove(orderID);
            existingIds.add(s);
            orderID = s;
        }
        else {
            existingIds.add(s);
            orderID = s;
        }
    }

    public void orderFulfilled(){
        existingIds.remove(orderID);
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
