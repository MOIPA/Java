package com.tr.dao;

import com.tr.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * 非常重要的接口之一：JpaSpecificationExecutor
 * 这个接口要配合其他接口使用，这个接口主要实现了多条件查询
 *
 */
public interface JpaSpecificationDao extends JpaSpecificationExecutor<ProductEntity>,JpaRepository<ProductEntity,Integer> {


}
