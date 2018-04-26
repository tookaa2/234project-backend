package camt.se234.project;
import camt.se234.project.dao.OrderDao;
import camt.se234.project.entity.Product;
import camt.se234.project.entity.SaleOrder;
import camt.se234.project.entity.SaleTransaction;
import camt.se234.project.service.SaleOrderService;
import camt.se234.project.service.SaleOrderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SalesOrderServiceTest {
    SaleOrderServiceImpl salesOrderService;
    OrderDao orderDao;
    @Before
    public void setup(){
        orderDao = mock(OrderDao.class);
    salesOrderService = new SaleOrderServiceImpl();
    salesOrderService.setOrderDao(orderDao);
    }
    @Test
    public void getSaleOrders(){
        List<SaleOrder> orders = new ArrayList<>();
        SaleOrder sale1= new SaleOrder(1234L,"E12R78E",new ArrayList<>());
        ///Product list
        Product pepsi=new Product(114578L,"pepsi578","pepsi","Kind of drink from pepsi company","./img/114578.png",20.00);
        Product coke=new Product(114878L,"coke878","coke","Kind of drink from coca cola company","./img/114878.png",25.00);
        Product carrot=new Product(114432L,"carrotkg","carrot","Vegtable imported from USA","./img/114432.png",35.00);
        ///Transactions
        SaleTransaction transaction1= new SaleTransaction(0001L,"7DR94E6",sale1,pepsi,5);
        SaleTransaction transaction2= new SaleTransaction(0002L,"8E9S5F4",sale1,coke,1);
        SaleTransaction transaction3= new SaleTransaction(0003L,"7E88RQ9",sale1,carrot,3);
        ///Adding all the transactions into sale1
        sale1.getTransactions().add(transaction1);
        sale1.getTransactions().add(transaction2);
        sale1.getTransactions().add(transaction3);

        orders.add(sale1);

        when(orderDao.getOrders()).thenReturn(orders);
        assertThat(salesOrderService.getSaleOrders(),is(orders));
    }

}
