package com.boot.muldatasource.mapper.one;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.muldatasource.entity.one.Order;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * 操作数据库接口
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/28
 **/
@Repository
public interface OrderMapper extends BaseMapper<Order> {

	/**
	 * 查询所有订单
	 *
	 * @return 订单列表
	 */
	List<Order> getAll();
}
