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

@Service
public class PropertyService {

    @Autowired
    PropertyDao propertyDao;

    @Autowired
    CategoryService categoryService;
    public void add(Property property) {
        propertyDao.save(property);
    }

    public void delete(int pid){
        propertyDao.delete(pid);
    }

    public void update(Property property){
        propertyDao.save(property);
    }

    public Property get(int id){
       return propertyDao.getOne(id);
    }

    //分页查询所有
    public Page4Navigator<Property>  list(int cid,int start,int size,int navigatePages ){
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page pageFromJPA = propertyDao.findByCategory(category,pageable);
        return new Page4Navigator<Property>(pageFromJPA,navigatePages);
    }

}
