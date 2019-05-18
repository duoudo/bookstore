package com.bookStore.commons.beans;

public class Orderitem {
    private Orders order_id;
    private Products product_id;
    private int buynum;

    public Orders getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Orders order_id) {
        this.order_id = order_id;
    }

    public Products getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Products product_id) {
        this.product_id = product_id;
    }

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }

    @Override
    public String toString() {
        return "Orderitem{" +
                "order_id=" + order_id +
                ", product_id=" + product_id +
                ", buynum=" + buynum +
                '}';
    }
}
