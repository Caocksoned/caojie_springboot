package com.caojie.tmall.web;


import com.caojie.tmall.pojo.Product;
import com.caojie.tmall.pojo.PropertyValue;
import com.caojie.tmall.service.ProductService;
import com.caojie.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyValueController {
    @Autowired PropertyValueService propertyValueService;
    @Autowired ProductService productService;

    @GetMapping("products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int pid)throws Exception{
        Product product = productService.get(pid);
        propertyValueService.init(product);
        return propertyValueService.list(productService.get(pid));
    }

    @PutMapping("propertyValues")
    public Object update(@RequestBody PropertyValue bean)throws Exception{
        propertyValueService.update(bean);
        return bean;
    }

}
