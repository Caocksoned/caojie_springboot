package com.caojie.tmall.service;

import com.caojie.tmall.dao.PropertyDao;
import com.caojie.tmall.pojo.Category;
import com.caojie.tmall.pojo.Property;
import com.caojie.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    PropertyDao propertyDAO;

    @Autowired
    CategoryService categoryService;

    public void add(Property bean) {
        propertyDAO.save(bean);
    }

    public void delete(int id) {
        propertyDAO.delete(id);
    }

    public Property get(int id) {
        return propertyDAO.findOne(id);
    }

    public void update(Property bean) {
        propertyDAO.save(bean);
    }

    public List<Property> get(Category category){
       return propertyDAO.findByCategory(category);
    }

    /*通过分类获取所有属性结合*/
    public  List<Property> list(Category category){
        return  propertyDAO.findByCategory(category);
    }

    public Page4Navigator<Property> list(int cid, int start, int size,int navigatePages) {
        Category category = categoryService.get(cid);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);

        Page<Property> pageFromJPA =propertyDAO.findByCategory(category,pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }
}
