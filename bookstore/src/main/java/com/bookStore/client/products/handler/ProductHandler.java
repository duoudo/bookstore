package com.bookStore.client.products.handler;

import com.bookStore.client.products.service.ProductServcie;
import com.bookStore.commons.beans.Products;
import com.bookStore.utils.PageModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/client/products")
public class ProductHandler {
    @Autowired
    private ProductServcie productServcie;
    @RequestMapping("/findProduct_listByCategory.do")
    public String findProduct_listByCategory(@RequestParam(defaultValue = "1") int pageIndex, String category, HttpSession session){
        PageModel pageModel = new PageModel();
        int count = productServcie.findCategoryNum(category);
        pageModel.setRecordCount(count);
        System.out.println("count="+count);
        pageModel.setPageIndex(pageIndex);
        List<Products> products =productServcie.findProduct_listByCategory(category,pageModel);
        session.setAttribute("products",products);
        if(category!=null)
            session.setAttribute("category",category);
        else{
            session.setAttribute("category","全部商品");
        }
        session.setAttribute("pageModel",pageModel);
        for(Products product:products){
            System.out.println(product);
        }
        return "/client/product_list.jsp";
    }
    @RequestMapping("/product_search_list.do")
    public String product_search_list(@RequestParam(defaultValue = "1") int pageIndex, String search, HttpSession session){
        PageModel pageModel = new PageModel();
        int count = productServcie.findProductByName(search);
        pageModel.setRecordCount(count);
        System.out.println("search="+search);
        System.out.println("count="+count);

        pageModel.setPageIndex(pageIndex);
        List<Products> products =productServcie.findProduct_listByName(search,pageModel);
        session.setAttribute("products",products);
        session.setAttribute("search",search);
        session.setAttribute("pageModel",pageModel);
        for(Products product:products){
            System.out.println(product);
        }
        return "/client/product_search_list.jsp";
    }

    @RequestMapping("/findProductById.do")
    public String findProductById(String id,HttpSession session){
        Products product=productServcie.findProductById(id);
        session.setAttribute("product_ById",product);
        return "/client/info.jsp";
    }

    @RequestMapping("/addCart.do")
    public String addCart(String id, HttpSession session){
        Products product=productServcie.findProductById(id);
        Map<Products,Integer> map=(Map<Products, Integer>) session.getAttribute("cart");
        if(map==null){
            map=new HashMap<>();
        }
        Integer count=map.put(product,1);
        if(count!=null&&count!=product.getPnum()){
            map.put(product,count+1);
        }
        if(count!=null&&count==product.getPnum()){
            map.put(product,count);
        }
        session.setAttribute("cart",map);
        return "/client/cart.jsp";
    }
    @RequestMapping("/changeCartCount.do")
    public String addCart(String id,Integer count,HttpSession session){
        Products product=productServcie.findProductById(id);
        Map<Products,Integer> map=(Map<Products, Integer>) session.getAttribute("cart");
        if(map==null){
            map=new HashMap<>();
        }
        if(count==0){
            map.remove(product);
        }else {
            map.put(product,count);
        }
        return "/client/cart.jsp";
    }
}
