package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

@SpringBootTest
class Demo3ApplicationTests {

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	Product product;

	@Test
	void contextLoads() {
	}

	@Test
	public void addOrder() {
		Order order = new Order();
		order.setOrder_id("1");
		order.setUser_id("1");
		order.setProduct_id("1");
		order.setCount("2");
		order.setPrice("200");
		Order saveOrder = orderService.addOrder(order);

		if (order.getProduct_id() == product.getProduct_id()) {
			product.setStock(order.getCount());
		}
	}

	/*
	 * @Test public void testAddNewProduct() { Product product = new Product();
	 * product.setProduct_id("3"); product.setProduct_name("本");
	 * product.setStock("100"); product.setPrice("2000");
	 * 
	 * Product saveproduct = productService.addProduct(product);
	 * 
	 * }
	 */
}
