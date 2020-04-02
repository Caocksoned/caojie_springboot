package com.caojie.tmall.service;

import com.caojie.tmall.dao.ProductImageDao;
import com.caojie.tmall.pojo.Product;
import com.caojie.tmall.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductImageService {
    public static final String type_single = "single";
    public static final String type_detail = "detail";

    @Autowired ProductImageDao productImageDao;

    public void add(ProductImage bean){
        productImageDao.save(bean);
    }

    public void delete(int id){
        productImageDao.delete(id);
    }

    //关于图片的查询问题困扰了很久...很多？？？
    public ProductImage get(int id) {
        return productImageDao.findOne(id);
    }

    public List<ProductImage> listSingleProductImages(Product product) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_single);
    }
    public List<ProductImage> listDetailProductImages(Product product) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_detail);
    }



    public void setFirstProductImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product);
        if(!singleImages.isEmpty())
            product.setFirstProductImage(singleImages.get(0));
        else
            product.setFirstProductImage(new ProductImage()); //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。

    }
    public void setFirstProductImages(List<Product> products) {
        for (Product product : products)
            setFirstProductImage(product);
    }

}
