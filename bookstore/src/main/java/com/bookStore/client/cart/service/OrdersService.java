package com.bookStore.client.cart.service;

import com.bookStore.commons.beans.Orders;
import com.bookStore.commons.beans.Products;
import com.bookStore.commons.beans.User;

import java.util.List;
import java.util.Map;

public interface OrdersService {
    int createOrder(Orders orders, Map<Products,Integer> map)throws Exception;
    int changePaystate(String out_trade_no);

    List<Orders> findOrderByUser(User login_user);
}
