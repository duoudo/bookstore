package com.bookStore.client.products.service.imp;

import com.bookStore.client.products.dao.IProductDao;
import com.bookStore.client.products.service.ProductServcie;
import com.bookStore.commons.beans.Products;
import com.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp implements ProductServcie {

    @Resource
    private IProductDao iProductDao;
    public List<Products> findProduct_listByCategory(String category, PageModel pageModel) {
        Map map = new HashMap<>();
        map.put("category",category);
        map.put("pageModel",pageModel);
        return iProductDao.findProduct_listByCategory(map);
    }

    @Override
    public int findCategoryNum(String category) {
        return iProductDao.findCategoryNum(category);
    }

    @Override
    public List<Products> findProduct_listByName(String search, PageModel pageModel) {
        Map map = new HashMap();
        map.put("search",search);
        map.put("pageModel",pageModel);
        return iProductDao.findProduct_listByName(map);
    }

    @Override
    public int findProductByName(String search) {
        return iProductDao.findProductByName(search);
    }

    @Override
    public Products findProductById(String id) {
        return iProductDao.findProductById(id);
    }
}
