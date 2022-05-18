package com.ss.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.model.Admin;
import com.ss.service.AdminService;

@Controller
//@Controller annotation indicates that the annotated class is a controller. It is a specialization of @Component and is autodetected through classpath scanning.
//It is typically used in combination with annotated handler methods based on the @RequestMapping annotation.
public class AdminController {
	
	@Autowired
	//In the spring boot, @Autowired annotation is used for dependency injection.In spring boot application, all loaded beans are eligible for auto wiring to another bean. 
	//The annotation @Autowired in spring boot is used to auto-wire a bean into another bean.
	private AdminService adminService;
	
	@PostMapping("/verifyLogin")
	//The @PostMapping is specialized version of @RequestMapping annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. POST) . 
	//The @PostMapping annotated methods in the @Controller annotated classes handle the HTTP POST requests matched with given URI expression
	//In Spring MVC, the @RequestParam annotation is used to read the form data and bind it automatically to the parameter present in the provided method.
	//So, it ignores the requirement of HttpServletRequest object to read the provided data.
	public String verifyLogin(@RequestParam(name="username") String username,@RequestParam(name="password") String password,HttpSession session,Model model) {
		if(!username.isEmpty() || !password.isEmpty()) {
			if(adminService.loginVerify(username,password)) {
				session.setAttribute("uname", username);
				return "adminDashboard";
			}
			else {
				model.addAttribute("action","Username or password wrong");
				return "admin_login";
			}
		}else {
			model.addAttribute("action", "Fields must not be empty");
			return "admin_login";
		}
		
	}
	
	@GetMapping("/getDashboard")
	//The @GetMapping annotation is a specialized version of @RequestMapping annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. GET) .
	//The @GetMapping annotated methods in the @Controller annotated classes handle the HTTP GET requests matched with given URI expression
	public String getDashboard() {
		return "adminDashboard";
	}
	
	@GetMapping("/changePassword")
	public String changeAdminPassword(HttpSession session, Model model)	{
		String username=(String) session.getAttribute("uname");
		Admin admin = adminService.getAdmin(username);
		model.addAttribute("admin", admin);
		return "change_password";
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam(name="oldPassword") String oldPassword,@RequestParam(name="newPassword") String newPassword,HttpSession session,Model model) {
		String username=(String) session.getAttribute("uname");
		Admin admin = adminService.getAdmin(username);
		if(oldPassword.equals(admin.getPassword())) {
			admin.setPassword(newPassword);
			adminService.updatePassword(admin);
			model.addAttribute("action", "Password changed Successfully");
			return "adminDashboard";
		}else {
			model.addAttribute("action", "Old Password not matching");
			return "change_password";
		}
		
	}
	
	@GetMapping("/logout")
	public String adminLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
