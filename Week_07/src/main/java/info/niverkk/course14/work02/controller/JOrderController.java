package info.niverkk.course14.work02.controller;

import info.niverkk.course14.work02.entity.JOrder;
import info.niverkk.course14.work02.service.JOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户订单表(JOrder)表控制层
 *
 * @author makejava
 * @since 2021-03-07 13:51:40
 */
@RestController
@RequestMapping("jOrder")
public class JOrderController {
    /**
     * 服务对象
     */
    @Resource
    private JOrderService jOrderService;


    @GetMapping("selectOne")
    public JOrder selectOne(String id) {
        return this.jOrderService.selectOne(id);
    }

    /**
     */
    @GetMapping("selectTwo")
    public JOrder selectTwo(String id) {
        return this.jOrderService.selectTwo(id);
    }



}