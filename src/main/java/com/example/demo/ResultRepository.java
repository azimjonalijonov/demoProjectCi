package com.example.demo;

 import org.springframework.data.jpa.repository.JpaRepository;
 @org.springframework.stereotype.Repository
public interface ResultRepository extends JpaRepository<ResultEntity,Long> {
}
