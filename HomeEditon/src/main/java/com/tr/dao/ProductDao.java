package com.tr.dao;

import com.tr.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<ProductEntity, Integer> {

}
