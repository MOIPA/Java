package com.tr.test;

import com.tr.dao.JpaSpecificationDao;
import com.tr.dao.PageAndSortingDao;
import com.tr.domain.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestJPASpecificationProductDao {

    @Autowired
    private JpaSpecificationDao productDao;


    @Test //test sort
    public void test1() {
        List<ProductEntity> list = this.productDao.findAll((root, query, cb) -> {
            return cb.and(cb.equal(root.get("name"), "pro1"), cb.greaterThan(root.get("price"), 1));
        });
        for (ProductEntity productEntity : list) {
            System.out.println(productEntity.getId()+"**************");
        }
    }
}
