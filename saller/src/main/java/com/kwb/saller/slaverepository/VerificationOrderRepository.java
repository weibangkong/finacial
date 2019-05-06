package com.kwb.saller.slaverepository;

import com.kwb.entity.VerificationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VerificationOrderRepository extends JpaRepository<VerificationOrder, String>, JpaSpecificationExecutor<VerificationOrder> {
    /**
     * 查询某段时间{start,stop) 内的队长列表
     * @param chanId
     * @param start
     * @param stop
     * @return
     */
    @Query(value = "select concat_ws('|',order_id,outer_order_id,chan_id,chan_user_id,product_id,order_type,amount" +
            ",DATE_FORMAT(create_at,'%Y-%m-%d %H:%i:%s')) " +
            "from t_order where chan_id= ?1 and create_at>= ?2 and create_at<= ?3", nativeQuery = true)
    List<String> queryVerificationOrders(String chanId, Date start, Date stop);


    /**
     * 长款: 本库中记录中多余其他库中的
     * 漏单: 本库中记录中少于其他库中的
     * 不一致：本库与其他库中都存在，数据却不同的
     */


    /**
     * 查询不同的订单
     * @param chanId
     * @param start
     * @param stop
     * @return
     */
    @Query(value = "select t.order_id from t_order t left join verification_order v on t.chan_id = ?1 " +
            "and t.outer_order_id = v.order_id and concat_ws('|',t.order_id,t.outer_order_id,t.chan_id,t.chan_user_id" +
            ",t.product_id,t.order_type,t.amount,t.create_at) != concat_ws('|',v.order_id,v.outer_order_id,v.chan_id," +
            "v.chan_user_id,v.product_id,v.order_type,v.amount,v.create_at) and t.create_at >= ?2 and t.create_at <= ?3", nativeQuery = true)
    List<String> queryDifferentOrders(String chanId, Date start, Date stop);

    /**
     * 查询漏单
     * @param chanId
     * @param start
     * @param stop
     * @return
     */
    @Query(value = "select v.order_id from verification_order v left join t_order t on v.outer_order_id = t.order_id " +
            "and v.chan_id= ?1 where t.order_id is null and v.create_at >= ?2 and v.create_at <= ?3", nativeQuery = true)
    List<String> queryMissOrders(String chanId, Date start, Date stop);


    /**
     * 查询长款
     * @param chanId
     * @param start
     * @param stop
     * @return
     */
    @Query(value =  "select t.order_id from t_order t left join verification_order v on t.chan_id= ?1 and " +
            "t.outer_order_id = v.order_id where v.order_id is null and t.create_at>= ?2 and t.create_at<= ?3", nativeQuery = true)
    List<String> queryExecessOrders(String chanId, Date start, Date stop);
}
