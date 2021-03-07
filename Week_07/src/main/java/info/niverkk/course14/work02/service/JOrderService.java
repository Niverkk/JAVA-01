package info.niverkk.course14.work02.service;

import info.niverkk.course14.work02.entity.JOrder;

/**
 * 用户订单表(JOrder)表服务接口
 *
 * @author makejava
 * @since 2021-03-07 13:51:39
 */
public interface JOrderService {

    JOrder selectOne(String id);

    JOrder selectTwo(String id);

}