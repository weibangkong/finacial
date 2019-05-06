package com.kwb.saller.repository;


import com.kwb.entity.Order;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/*
* 简单操作继承JpaRepository
* 复杂查询继承JpaSpecificationExecutor
* */
//@EntityScan("com.kwb.entity.Order")
@Repository
public interface OrderRepository extends JpaRepository<Order,String>, JpaSpecificationExecutor<Order> {
}
