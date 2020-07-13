package com.caojie.tmall.dao;
 
import java.util.List;

import com.caojie.tmall.pojo.Product;
import com.caojie.tmall.pojo.Property;
import com.caojie.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer>{

	List<PropertyValue> findByProductOrderByIdDesc(Product product);
	PropertyValue getByPropertyAndProduct(Property property, Product product);

}
