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


import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
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
    public void getSaleOrdersTest(){
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
        ///Adding all the transactions into temporary transaction variable
        List<SaleTransaction> transactionsList = new ArrayList<>();
        transactionsList.add(transaction1);
        transactionsList.add(transaction2);
        transactionsList.add(transaction3);

        /// set the sale's transactionList
        sale1.setTransactions(transactionsList);

        /// add sale 1 to the orders
        orders.add(sale1);

        when(orderDao.getOrders()).thenReturn(orders);
        assertThat(salesOrderService.getSaleOrders(),hasItem(
                (new SaleOrder(1234L,"E12R78E",transactionsList))
        ));
    }

    @Test
    public void getAverageSaleOrderPriceTest(){
        List<SaleOrder> orders = new ArrayList<>();
        SaleOrder sale1= new SaleOrder(1234L,"E12R78E",new ArrayList<>());
        SaleOrder sale2= new SaleOrder(7564L,"4D8W9F4",new ArrayList<>());

        ///Product list
        Product pepsi=new Product(114578L,"pepsi578","pepsi","Kind of drink from pepsi company","./img/114578.png",20.00);
        Product coke=new Product(114878L,"coke878","coke","Kind of drink from coca cola company","./img/114878.png",25.00);
        Product carrot=new Product(114432L,"carrotkg","carrot","Vegtable imported from USA","./img/114432.png",35.00);
        ///Transactions
        SaleTransaction transaction1= new SaleTransaction(0001L,"7DR94E6",sale1,pepsi,5);
        SaleTransaction transaction2= new SaleTransaction(0002L,"8E9S5F4",sale1,coke,1);
        SaleTransaction transaction3= new SaleTransaction(0003L,"7E88RQ9",sale1,carrot,3);
        //------------------------------
        SaleTransaction transaction4= new SaleTransaction(0004L,"ASD4AS6",sale2,pepsi,20);
        SaleTransaction transaction5= new SaleTransaction(0005L,"8D8G4E5",sale2,coke,10);
        SaleTransaction transaction6= new SaleTransaction(0006L,"66W8F8D",sale2,carrot,11);
        ///Adding all the transactions into sale1
        sale1.getTransactions().add(transaction1);
        sale1.getTransactions().add(transaction2);
        sale1.getTransactions().add(transaction3);

        sale2.getTransactions().add(transaction4);
        sale2.getTransactions().add(transaction5);
        sale2.getTransactions().add(transaction6);

        orders.add(sale1);
        orders.add(sale2);

        when(orderDao.getOrders()).thenReturn(orders);
        assertThat(salesOrderService.getAverageSaleOrderPrice(),is(632.5));


    }

}
