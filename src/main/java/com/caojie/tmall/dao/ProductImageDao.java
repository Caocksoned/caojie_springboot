package com.caojie.tmall.dao;

import com.caojie.tmall.pojo.Product;
import com.caojie.tmall.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageDao  extends JpaRepository<ProductImage,Integer> {
     List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);
}
