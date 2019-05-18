package com.bookStore.client.cart.service.impl;

import com.bookStore.client.cart.dao.OrdersDao;
import com.bookStore.client.cart.service.OrdersService;
import com.bookStore.commons.beans.Orderitem;
import com.bookStore.commons.beans.Orders;
import com.bookStore.commons.beans.Products;
import com.bookStore.commons.beans.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersDao ordersDao;


    public int createOrder(Orders orders, Map<Products,Integer> map)throws Exception {
        Orderitem orderitem = new Orderitem();
        Products products = new Products();
        System.out.println("12312312312");
        for (Map.Entry<Products, Integer> entry : map.entrySet()) {
            orderitem.setOrder_id(orders);
            orderitem.setProduct_id(entry.getKey());
            orderitem.setBuynum(entry.getValue());
            ordersDao.createOrderitem(orderitem);//往orderitem表添加信息
            ordersDao.changeProductsPnum(entry.getKey().getId(),entry.getValue());//修改库存
         }
//        if(1==1){
//            throw new Exception("我们的异常");
//        }
        return ordersDao.createOrder(orders);
    }

    @Override
    public int changePaystate(String out_trade_no) {
        return ordersDao.changePaystate(out_trade_no);
    }

    @Override
    public List<Orders> findOrderByUser(User login_user) {
        return ordersDao.findOrderByUser(login_user);
    }
}
