package com.bookStore.client.products.dao;

import com.bookStore.commons.beans.Products;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IProductDao {//非自定义对象使用动态sql需要@Param标记
    public List<Products> findProduct_listByCategory(Map map);

    int findCategoryNum(@Param("category")String category);

    List<Products> findProduct_listByName(Map map);

    int findProductByName(@Param("search")String search);

    Products findProductById(String id);
}
