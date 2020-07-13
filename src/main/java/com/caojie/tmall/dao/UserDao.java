package com.caojie.tmall.dao;

import com.caojie.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
}
