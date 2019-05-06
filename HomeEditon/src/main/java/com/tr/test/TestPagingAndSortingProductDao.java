package com.tr.test;

import com.tr.dao.PageAndSortingDao;
import com.tr.dao.ProductDao;
import com.tr.domain.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestPagingAndSortingProductDao {

    @Autowired
    private PageAndSortingDao productDao;

    @Test  //test pageable
    public void test() {
        Page<ProductEntity> all = this.productDao.findAll(PageRequest.of(1, 2));
        List<ProductEntity> produts = all.getContent();
        for (ProductEntity p :
                produts) {
            System.out.println(p.getId());
        }
    }

    @Test //test sort
    public void test2() {
//        Page<ProductEntity> all = this.productDao.findAll(PageRequest.of(0, 2, Sort.by(Sort.Order.desc("id"))));
        Iterable<ProductEntity> all = this.productDao.findAll(Sort.by(Sort.Order.desc("price")));
        Iterator<ProductEntity> iterator = all.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getId());
        }
    }
}
