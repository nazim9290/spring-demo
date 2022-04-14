package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Order;

@Mapper
public interface OrderMapper {

	// get all
	@Select("select * from buy_history")
	List<Order> getAll();

	// get product by id
	@Select("select * from buy_history where id = #{id}")
	Order getOrderById(String id);

	// insert
	@Insert("insert into buy_history (order_id,user_id,product_id,count,price,buy_time) values(#{order_id},#{user_id},#{product_id},#{count},#{price},now())")
	void insert(Order order);
}
