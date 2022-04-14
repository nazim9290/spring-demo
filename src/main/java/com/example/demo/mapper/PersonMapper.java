package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.User;

@Mapper
public interface PersonMapper {

	// get all
	@Select("select * from user")
	List<User> getAll();

	// get user by id
	@Select("select * from user1 where id = #{id}")
	User getUserById(String id);

	// get user by name (練習)
	@Select("select * from user1 where user_name Like CONCAT('%',#{user_name},'%')")
	List<User> getUserByName(String name);

	// insert
	@Insert("insert into user1 (id, user_name,email, sex,created_time,update_time) values (#{id},#{name},#{email},#{sex},now(),now())")
	void insert(User user);

	// update
	@Update("update user1 set id = #{id}, user_name = #{user_name},email = #{email}, sex = #{sex} where id = #{id}")
	void update(User user);

	// delete
	@Delete("delete from user1 where id = #{id}")
	void delete(Integer id);
}
