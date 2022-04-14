package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.UserEntity;
import com.example.demo.form.SearchForm;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userService;

	// 一覧表示画面に遷移する
	@GetMapping("/list")
	public String userList(Model model) {

		List<UserEntity> userList = new ArrayList<>();
		userList = userService.getUserList();

		model.addAttribute("userList", userList);
		return "list";
	}

	// 登録画面に遷移する
	@GetMapping("/register")
	public String userRegister(Model model) {

		model.addAttribute("userForm", new UserForm());

		return "register";
	}

	// 編集画面に遷移する
	@GetMapping("/update/{userID}")
	public String userUpdate(@PathVariable("userID") String userID, Model model) {
		UserForm userForm = userService.getUserById(userID);
		model.addAttribute("userForm", userForm);
		model.addAttribute("errorMessage", new HashMap<String, String>());

		return "update";
	}

	// 更新処理
	@PostMapping("/update")
	public String update(@ModelAttribute @Valid UserForm userForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "update";
		}
		try {
			userService.updateUser(userForm);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "update";
		}

		// 更新処理

		return "redirect:/user/complete";
	}

	// 検索画面に遷移する（練習）
	@GetMapping("/toSearch")
	public String userUpdate(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "search";
	}

	// 完了画面に遷移する
	@GetMapping("/complete")
	public String userComplete() {
		return "complete";
	}

	// 登録処理
	@PostMapping("/insert")
	public String register(@ModelAttribute @Valid UserForm userForm, BindingResult bindingResult) {

		// エラーあるか判断する
		if (bindingResult.hasErrors()) {
			// 登録画面へ戻る
			return "register";
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(userForm.getPassword());
			userForm.setPassword(encodedPassword);
		}

		// 登録処理
		userService.insertUser(userForm);
		return "complete";
	}

	// 削除処理
	@GetMapping("/delete/{userID}")
	public String delete(@PathVariable("userID") String userID) {
		// 削除処理
		userService.deleteUser(userID);
		return "redirect:/user/complete";
	}

	// 検索処理(練習)
	@PostMapping("/search")
	public String search(@ModelAttribute UserForm userForm, Model model) {

		List<UserEntity> userList = userService.getUserByName(userForm.getName());
		model.addAttribute("userList", userList);

		return "search_result";
	}

	@GetMapping("/doSearchJson")
	@ResponseBody
	public List<UserEntity> output(@RequestParam("jsonData") String data)
			throws JsonMappingException, JsonProcessingException {
		// json to object
		ObjectMapper mapper = new ObjectMapper();

		SearchForm searchForm = mapper.readValue(data, SearchForm.class);

		return userService.getUserByName(searchForm.getName());
	}

}
