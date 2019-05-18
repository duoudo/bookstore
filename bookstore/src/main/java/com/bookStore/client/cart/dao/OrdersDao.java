package com.bookStore.client.cart.dao;

import com.bookStore.commons.beans.Orderitem;
import com.bookStore.commons.beans.Orders;
import com.bookStore.commons.beans.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersDao {
    public int createOrder(Orders orders);
    int changePaystate(String out_trade_no);
    void createOrderitem(Orderitem orderitem);
    void changeProductsPnum(@Param("id") String id, @Param("num") int num);
    List<Orders> findOrderByUser(User login_user);
}
