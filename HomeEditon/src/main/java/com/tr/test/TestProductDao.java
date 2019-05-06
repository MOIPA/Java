package com.tr.test;

import com.tr.dao.ProductDao;
import com.tr.domain.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestProductDao {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testInsert() {
        this.productDao.save(new ProductEntity(4, "pro1", 1.1));
    }

}
