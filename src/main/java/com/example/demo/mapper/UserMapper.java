package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.UserEntity;
import com.example.demo.form.UserForm;

@Mapper
public interface UserMapper {
	// get all
	@Select("select * from user")
	List<UserEntity> getAll();

	// get user by id
	@Select("select * from user where id = #{id}")
	UserEntity getUserById(String id);

	// get user by name (練習)
	@Select("select * from user where name Like CONCAT('%',#{name},'%')")
	List<UserEntity> getUserByName(String name);

	// insert
	@Insert("insert into user (id, name, sex) values (#{id},#{name},#{sex})")
	void insert(UserForm userForm);

	// update
	@Update("update user set id = #{id}, name = #{name}, sex = #{sex} where id = #{id}")
	void update(UserEntity user);

	// delete
	@Delete("delete from user where id = #{id}")
	void delete(Integer id);
}
