package com.bookStore.client.products.service;

import com.bookStore.commons.beans.Products;
import com.bookStore.utils.PageModel;

import java.util.List;

public interface ProductServcie {
    public List<Products> findProduct_listByCategory(String category, PageModel pageModel);

    int findCategoryNum(String category);

    List<Products> findProduct_listByName(String search, PageModel pageModel);

    int findProductByName(String search);

    Products findProductById(String id);
}
