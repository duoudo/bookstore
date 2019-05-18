package com.bookStore.client.cart.handler;

import com.alipay.api.internal.util.AlipaySignature;
import com.bookStore.client.cart.service.OrdersService;
import com.bookStore.commons.beans.Orders;
import com.bookStore.commons.beans.Products;
import com.bookStore.commons.beans.User;
import com.bookStore.utils.ActiveCodeUtils;
import com.bookStore.utils.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/client/cart")
public class CartHandler {
    @Autowired
    private OrdersService ordersService;
    @RequestMapping("/createOrder.do")
    public String createOrder(Orders orders, HttpSession session)throws Exception{
        User login_user=(User)session.getAttribute("login_user");
        Map<Products,Integer> map=(Map<Products, Integer>) session.getAttribute("cart");
        String oders_id=ActiveCodeUtils.createActiveCode();
        orders.setId(oders_id);
        orders.setUser(login_user);
        int row = ordersService.createOrder(orders,map);
        if(row<=0){
            System.out.println("插入订单失败");
            return null;
        }
        session.setAttribute("oders_id",oders_id);
        session.setAttribute("orders",orders);
        return "/client/confirm.jsp";
    }
    @RequestMapping("/paysuccess.do")
    public String paysuccess(Orders orders, HttpSession session, HttpServletRequest request, HttpServletResponse response)throws Exception{
        /* *
         * 功能：支付宝服务器同步通知页面
         * 日期：2017-03-30
         * 说明：
         * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
         * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。


         *************************页面功能说明*************************
         * 该页面仅做页面展示，业务逻辑处理请勿在该页面执行
         */

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            ordersService.changePaystate(out_trade_no);
            session.setAttribute("out_trade_no",out_trade_no);
            session.setAttribute("trade_no",trade_no);
            session.setAttribute("total_amount",total_amount);
            return "/client/paysuccess.jsp";
        }else {
            response.getWriter().println("验签失败");
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
        return "";
    }
    //查询订单信息
    @RequestMapping("/findOrderByUser.do")
    public String findOrderByUser(HttpSession session){
        User login_user=(User)session.getAttribute("login_user");
        List<Orders> orders=ordersService.findOrderByUser(login_user);
        System.out.println("orders orders"+orders);
        session.setAttribute("orders",orders);
        return "/client/orderlist.jsp";
    }
}
