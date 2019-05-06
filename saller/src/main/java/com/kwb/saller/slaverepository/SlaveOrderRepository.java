package com.kwb.saller.slaverepository;

import com.kwb.saller.repository.OrderRepository;

//根据多数据源配置因为该包下的库为读库，实现
public interface SlaveOrderRepository extends OrderRepository {
}
