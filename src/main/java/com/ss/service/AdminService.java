package com.ss.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.model.Admin;
import com.ss.repository.AdminRepository;
@Service
//It is used to mark the class as a service provider. So overall @Service annotation is used with classes that provide some business functionalities. 
//Spring context will autodetect these classes when annotation-based configuration and classpath scanning is used.
public class AdminService {
	
	@Autowired
	//In the spring boot, @Autowired annotation is used for dependency injection.In spring boot application, all loaded beans are eligible for auto wiring to another bean.
	//The annotation @Autowired in spring boot is used to auto-wire a bean into another bean.
	private AdminRepository adminRepository;
	
	public Admin getAdmin(String username) {
		return adminRepository.findByUsername(username);
	}

	public boolean loginVerify(String username, String password) {
		Admin admin = adminRepository.findByUsername(username);
        if (admin!= null && admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            return true;
        }
        return false;
	}

	public void updatePassword(Admin admin) {
		adminRepository.save(admin);
		
	}

}
