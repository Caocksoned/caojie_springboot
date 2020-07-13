package com.caojie.tmall.web;

import com.caojie.tmall.pojo.User;
import com.caojie.tmall.service.UserService;
import com.caojie.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public Page4Navigator<User> get(@RequestParam(value = "start", defaultValue = "0") int start,
                                    @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{
        start = start<0?0:start;
        return userService.list(start,size,5);
    }
    @PostMapping("/users")
    public Object add(User bean)throws Exception{
        userService.add(bean);
        return  bean;
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id)throws Exception{
        userService.delete(id);
        return null;
    }

    @PutMapping("/users")
    public Object update(@RequestBody User bean)throws Exception{
        userService.update(bean);
        return bean;
    }
}
