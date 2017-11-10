package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entities.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;


public class OrderDaoImplTest {

    private OrderDaoImpl orderDao = OrderDaoImpl.getInstance();

    private Order testOrder;

    @Before
    public void setUp() throws Exception {
        testOrder = new Order();
        testOrder.setCustomerEmail("mail");
        testOrder.setDate(LocalDate.now());
        testOrder.setDescription("description");
        testOrder.setItemsCount(3);
        testOrder.setName("name");
        testOrder.setPaymentSign("sign");
        testOrder.setTotalPrice(100);
    }

    @After
    public void before() throws Exception {
        testOrder = null;
    }

    @Test
    public void add() throws Exception {
        orderDao.add(testOrder);
        Order orderFromDatabase = orderDao.getById(testOrder.getId());
        orderDao.delete(orderFromDatabase);
        assertNotNull(orderFromDatabase);
        assertEquals(testOrder, orderFromDatabase);
    }

    @Test
    public void update() throws Exception {
        orderDao.add(testOrder);
        Order newOrder = new Order();
        newOrder.setName("new order");
        newOrder.setId(testOrder.getId());
        orderDao.update(newOrder);
        Order updatableOrderFromDatabase = orderDao.getById(testOrder.getId());
        assertEquals("new order", updatableOrderFromDatabase.getName());
        orderDao.delete(testOrder.getId());
    }

    @Test
    public void delete() throws Exception {
        orderDao.add(testOrder);
        orderDao.delete(testOrder);
        Order deletedOrder = orderDao.getById(testOrder.getId());
        assertNull(deletedOrder);
    }


    @Test
    public void getAll() throws Exception {
        orderDao.add(testOrder);
        List<Order> orders = orderDao.getAll();
        assertNotNull(orders);
        assertFalse(orders.size() == 0);
        orderDao.delete(testOrder);
    }

    @Test
    public void getInstance() throws Exception {
        OrderDaoImpl firstInstance = OrderDaoImpl.getInstance();
        OrderDaoImpl secondInstance = OrderDaoImpl.getInstance();
        assertEquals(firstInstance.hashCode(), secondInstance.hashCode());
    }

}