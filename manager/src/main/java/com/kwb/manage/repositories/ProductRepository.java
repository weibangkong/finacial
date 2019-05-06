package com.kwb.manage.repositories;

import com.kwb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 继承JpaRepository 实现简单的增删改查
 * 继承JpaSpecificationExecutor 实现复杂的持久化操作
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,String>,JpaSpecificationExecutor<Product> {
}
