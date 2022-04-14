package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.UserEntity;
import com.example.demo.form.LoginForm;
import com.example.demo.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String toLogin(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping("/doLogin")
	public String doLogin(HttpSession session, @ModelAttribute LoginForm loginForm, Model model) {

		UserEntity userEntity = userService.getUserEntityByName(loginForm.getName());
		// loginパスワードのチェック処理
		if ((userEntity != null) && loginForm.getPassword().equals(userEntity.getPassword())) {
			session.setAttribute("loginUser", userEntity);
			return "redirect:/user/list";
		} else {
			model.addAttribute("errorMsg", "ログイン失敗しました。");
			return "login";
		}

	}

	@GetMapping("/logOut")
	public String doOut(HttpSession session, Model model) {
		model.addAttribute("loginForm", new LoginForm());
		session.removeAttribute("loginUser");
		return "login";
	}
}
