package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Product;

@Mapper
public interface ProductMapper {

	// get all
	@Select("select * from product")
	List<Product> getAll();

	// get product by id
	@Select("select * from product where id = #{id}")
	Product getProductById(String id);

	// insert
	@Insert("insert into product (product_id, product_name,stock,price,created_time,update_time) values (#{product_id},#{product_name},#{stock},#{price},now(),now())")
	void insert(Product product);

}
