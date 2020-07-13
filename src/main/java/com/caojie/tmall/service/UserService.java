package com.caojie.tmall.service;

import com.caojie.tmall.dao.UserDao;
import com.caojie.tmall.pojo.User;
import com.caojie.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired UserDao userDao;

    /*提供用户的分页查询*/
    public Page4Navigator<User> list(int start, int size, int navigatePages){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page pageFromJPA = userDao.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public void add(User bean){
        userDao.save(bean);
    }

    public void delete(int id){
        userDao.delete(id);
    }

    public void update(User bean){
        userDao.save(bean);
    }

}
