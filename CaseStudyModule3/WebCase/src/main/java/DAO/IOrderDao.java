package DAO;

import Model.Order;

import java.util.List;

public interface IOrderDao {
    void InsertOrder(Order order);
    List<Order> showOrderList();
    Order selectOrderByID(int ID);
    void deleteOrderByID(int ID);
}
