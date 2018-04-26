package camt.se234.project;


import camt.se234.project.dao.ProductDao;
import camt.se234.project.entity.Product;
import camt.se234.project.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    ProductDao productDao;
    ProductServiceImpl productService;


    @Before
    public void setup(){
    productDao = mock(ProductDao.class);
    productService = new ProductServiceImpl();
    productService.setProductDao(productDao);
    }

    @Test
    public void testGetAllProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(114578L,"pepsi578","pepsi","Kind of drink","./img/114578.png",20.50));
        products.add(new Product(114780L,"chickenKG","Chicken meat","Fresh chiken meat,no skin","./img/114780.png",80.00));
        products.add(new Product(114798L,"bakingP98","Lotus Baking Powder 98gram","Kind of drink","./img/114798.png",58.75));

        when(productDao.getProducts()).thenReturn(products);
        assertThat(productService.getAllProducts(),hasItems(
                new Product(114578L,"pepsi578","pepsi","Kind of drink","./img/114578.png",20.50),
                new Product(114780L,"chickenKG","Chicken meat","Fresh chiken meat,no skin","./img/114780.png",80.00),
                new Product(114798L,"bakingP98","Lotus Baking Powder 98gram","Kind of drink","./img/114798.png",58.75)
                ));


    }

    @Test
    public void testGetAvailableProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(114578L,"pepsi578","pepsi","Kind of drink","./img/114578.png",20.50));
        products.add(new Product(114780L,"chickenKG","Chicken meat","Fresh chiken meat,no skin","./img/114780.png",-1.00));
        products.add(new Product(114798L,"bakingP98","Lotus Baking Powder 98gram","Kind of drink","./img/114798.png",0.00));

        when(productDao.getProducts()).thenReturn(products);
        assertThat(productService.getAvailableProducts(),hasItem(
                new Product(114578L,"pepsi578","pepsi","Kind of drink","./img/114578.png",20.50)
        ));


    }

    @Test
    public void testGetUnavailableProductSize(){

    }
}
