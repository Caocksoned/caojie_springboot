package com.caojie.tmall.web;

import com.caojie.tmall.pojo.Property;
import com.caojie.tmall.service.PropertyService;
import com.caojie.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PropertyController {
    @Autowired PropertyService propertyService;

    @GetMapping("/categories/{cid}/properties")
    public Page4Navigator<Property> list(@PathVariable int cid,@RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start<0?0:start;
        Page4Navigator<Property> page =propertyService.list(cid,size, start, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        return page;
    }
    @GetMapping("/properties/{id}")
    public Property get(@PathVariable int id){
        Property bean = propertyService.get(id);
        return bean;
    }

    @PostMapping("/properties")
    public Property add(@RequestBody Property bean){
        propertyService.add(bean);
        return bean;
    }


    @PutMapping("/properties")
    public Property update(@RequestBody Property bean){
        propertyService.update(bean);
        return bean;
    }

    @DeleteMapping("properties/{id}")
    public String delete(@PathVariable("id") int id){
        propertyService.delete(id);
        return null;
    }

}
