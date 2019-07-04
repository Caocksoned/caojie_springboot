package com.caojie.tmall.dao;

import com.caojie.tmall.pojo.Category;
import com.caojie.tmall.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyDao extends JpaRepository<Property, Integer> {

    Page<Property> findByCategory(Category category, Pageable pageable);
}
