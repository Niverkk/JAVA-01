package info.niverkk.course14.work02.service.impl;

import info.niverkk.course14.work02.dao2.JOrderDao2;
import info.niverkk.course14.work02.dao.JOrderDao;
import info.niverkk.course14.work02.entity.JOrder;
import info.niverkk.course14.work02.service.JOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户订单表(JOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-03-07 13:51:40
 */
@Service("jOrderService")
public class JOrderServiceImpl implements JOrderService {
    @Resource
    private JOrderDao jOrderDao;

    @Resource
    private JOrderDao2 jOrderDao2;

    //@Autowired
    //private List<MyDao> list;


    //@Autowired(required = false)
    //private MyDao myDao;


    @Override
    public JOrder selectOne(String id) {
        return jOrderDao.queryById(id);
        //return list.get(0).queryById(id);
    }

    @Override
    public JOrder selectTwo(String id) {
        return jOrderDao2.queryById(id);
        //return list.get(0).queryById(id);
    }
}