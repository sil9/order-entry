package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entities.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoImplTest {

    @Autowired
    private OrderDao OrderDao;

    private Order testOrder;

    @Before
    public void setUp() throws Exception {
        testOrder = new Order();
        testOrder.setName("name");
        OrderDao.add(testOrder);
    }

    @After
    public void tearDown() throws Exception {
        OrderDao.delete(testOrder.getId());
    }

    @Test
    public void add() throws Exception {
        int id = testOrder.getId();
        assertNotNull(id);
    }

    @Test
    public void update() throws Exception {
        testOrder.setName("Changed Name");
        OrderDao.update(testOrder);
        Order orderFromDatabase = OrderDao.getById(testOrder.getId());
        assertNotNull(orderFromDatabase);
        assertEquals("Changed Name", testOrder.getName());
    }

    @Test
    public void delete() throws Exception {
        Order Order = new Order();
        OrderDao.add(Order);
        OrderDao.delete(Order.getId());
        Order orderFromDatabase = OrderDao.getById(Order.getId());
        assertNull(orderFromDatabase);
    }

    @Test
    public void getAll() throws Exception {
        List<Order> categories = OrderDao.getAll();
        assertNotNull(categories);
        assertTrue(categories.size() == 1);
    }

}