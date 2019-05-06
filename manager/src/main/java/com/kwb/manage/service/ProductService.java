package com.kwb.manage.service;

import com.kwb.entity.Product;
import com.kwb.enums.ProductStatus;
import com.kwb.manage.error.ErrorEnum;
import com.kwb.manage.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product addPorduct(Product product){
        logger.debug("Create Product,param:",product);
        checkProduct(product);
        setDefault(product);
        Product result = productRepository.save(product);
        logger.debug("Create result: ",result);
        return result;
    }

    public Product getOneProduct(String id){
        if(null == id && "".equals(id)){
            logger.info("The id can't be null or a empty String id={}",id);
            return null;
        }else{
            Product product = productRepository.getOne(id);
            return null == product ? null : product;
        }
    }

    public Page<Product> listPageProduct(List<String> idList, BigDecimal minRewardRate, BigDecimal maxRewardRate,
                                         List<String> statusList, Pageable pageable) {
        logger.info("Start search product with condition ids={}，minRewardRate={},maxRewardRate={},statusList={}," +
                        "pagebale={}",
                idList,minRewardRate,maxRewardRate,statusList,pageable);
        //JPA复杂条件查询
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Expression<String> idCol = root.get("id");
                Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
                Expression<String> statusCol = root.get("status");
                List<Predicate> predicate = new ArrayList<>();
                if(null != idList &&idList.size() > 0) {
                    predicate.add(idCol.in(idList));
                }
                if(BigDecimal.ZERO.compareTo(minRewardRate) < 0) {
                    predicate.add(cb.ge(rewardRateCol, minRewardRate));
                }
                if(BigDecimal.ZERO.compareTo(maxRewardRate) < 0) {
                    predicate.add(cb.le(rewardRateCol, maxRewardRate));
                }
                if (null != statusList && statusList.size() > 0) {
                    predicate.add(statusCol.in(statusList));
                }

                query.where(predicate.toArray(new Predicate[0]));
                return null;
            }
        };
        //执行查询
        Page<Product> page = productRepository.findAll(specification,pageable);

        return page;
    }


    private void setDefault(Product product) {
         if(null == product.getCreateAt())
             product.setCreateAt(new Date());
         if(null == product.getUpdateAt())
             product.setUpdateAt(new Date());
         if(null == product.getStepAmount())
             product.setStepAmount(BigDecimal.ZERO);
         if(null == product.getLockTerm())
             product.setLockTerm(0);
         if(null == product.getStatus())
             product.setStatus(ProductStatus.AUDITING.name());
    }

    /**
     * 数据校验
     * 1.非空数据
     * 2.收益率0-30
     * 3.投资步长为整数
     * @param product
     */
    public void checkProduct(Product product){
         Assert.notNull(product.getId(),ErrorEnum.ID_NOT_NULL.getCode());
         Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate())<0 &&BigDecimal.valueOf(30).compareTo(product.getRewardRate())>=0,"The rewardRate must between 0% and 30%");
         Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) == 0, "The Step Amount must be a integer");
    }


}
