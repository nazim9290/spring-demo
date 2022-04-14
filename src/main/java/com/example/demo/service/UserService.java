package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.form.UserForm;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	// ユーザー一覧取得
	public List<UserEntity> getUserList() {
		return userMapper.getAll();
	}

	// IDによるユーザー取得
	public UserEntity getUserByID(String id) {
		return userMapper.getUserById(id);
	}

	// IDによるユーザー取得,UserForm戻す
	public UserForm getUserById(String id) {

		UserForm userForm = new UserForm();
		UserEntity user = userMapper.getUserById(id);

		userForm.setId(user.getId());
		userForm.setName(user.getName());
		userForm.setSex(user.getSex());
		userForm.setUpdate_time(user.getUpdate_time());

		return userForm;
	}

	// 名前によるユーザー取得
	public List<UserEntity> getUserByName(String username) {
		return userMapper.getUserByName(username);
	}

	public UserEntity getUserEntityByName(String username) {
		return userMapper.getUserEntityByName(username);
	}

	// ユーザー登録
	public void insertUser(UserForm userForm) {
		// password を暗号化する
//		userForm.setPassword(bcryptEncoder.encode(userForm.getPassword()));

		userMapper.insert(userForm);
	}

	// ユーザー更新
	public void updateUser(UserForm user) throws Exception {
		// 排他制御のチェック
		// 更新時間ー致しない場合は、例外投げる
		UserEntity userEntity1 = userMapper.getUserById(user.getId());
		if (user.getUpdate_time().equalsIgnoreCase(userEntity1.getUpdate_time()) == false) {
			throw new Exception("更新時間がデータ取得時のとー致しないため、更新できないです。");
		}

		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setName(user.getName());
		userEntity.setSex(user.getSex());
		userMapper.update(userEntity);
	}

	// ユーザー削除
	public void deleteUser(String id) {
		userMapper.delete(Integer.parseInt(id));
	}

}
