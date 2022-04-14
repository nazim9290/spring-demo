package com.example.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Product;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

public class ProductTest {

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Test
	public void testAddNewProduct() {
		Product product = new Product();
		product.setProduct_id("1");
		product.setProduct_name("洋服");
		product.setStock("100");
		product.setPrice("2000");

		Product saveproduct = productService.addProduct(product);

	}

}
