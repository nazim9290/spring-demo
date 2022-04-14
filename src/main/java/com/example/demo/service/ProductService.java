package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;

@Service
public class ProductService {
	@Autowired
	ProductMapper productMapper;

	@Autowired
	OrderService orderService;

	public Product addProduct(Product product) {
		productMapper.insert(product);
		return product;
	}

	public void buyProduct(Order order) {
		orderService.addOrder(order);

	}
}
