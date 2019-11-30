package day02.mapper;

import day02.model.Orders;
import day02.model.OrdersExt;
import day02.model.User;

import java.util.List;

public interface OrderMapper {
    public OrdersExt findOrderById(int id);

    public Orders findOrderById2(int id);

    public Orders findOrderById3(int id);

    /**
     * 懒加载订单的用户数据
     *
     * @return
     */
    public List<Orders> findOrderAndUserByLazyloading();


}
