package com.tr.dao;

import com.tr.domain.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageAndSortingDao extends PagingAndSortingRepository<ProductEntity, Integer> {

}
