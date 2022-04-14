package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;

@Service
public class OrderService {

	@Autowired
	OrderMapper orderMapper;

	public Order addOrder(Order order) {
		orderMapper.insert(order);
		return order;
	}

}
