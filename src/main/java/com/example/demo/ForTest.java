package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForTest {
    @Autowired
    ResultRepository repository;
    public ResultEntity add(long a, int b){
        ResultEntity entity =new ResultEntity();
        entity.setValue(a+b);
          repository.save(entity);
          return  entity;
    }
    public  int subtract(int a, int b){
        return  a-b;
    }
}
