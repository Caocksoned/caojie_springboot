package com.caojie.tmall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestTmall {
    public  static  void main(String[] args){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_springboot?" +
                "useUnicode=true&characterEncodeing=utf8", "root", "admin");
                Statement s = c.createStatement()
        ){
            for (int i = 1; i <= 10 ; i++){
                String sqlFormat = "insert into category values （null,'测试分类%d'）";
                String sql = String.format(sqlFormat,i);
                s.execute(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
