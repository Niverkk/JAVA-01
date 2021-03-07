package info.niverkk.course14.work02.dao2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import info.niverkk.course14.work02.entity.JOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户订单表(JOrder)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-07 13:51:38
 */
@Mapper
public interface JOrderDao2 {

    //@Override
    @Select("SELECT * from j_order limit 1")
    JOrder queryById(@Param("id") String id);
}