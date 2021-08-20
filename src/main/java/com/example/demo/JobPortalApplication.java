package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entities.Authority;
import com.example.demo.entities.User;
import com.example.demo.repository.UserDetailsRepository;

@SpringBootApplication
public class JobPortalApplication {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(JobPortalApplication.class, args);
	}

	@PostConstruct
	protected void init() {
		
		List<Authority> authorityList=new ArrayList<>();
		
		authorityList.add(createAuthority("USER","User role"));
		authorityList.add(createAuthority("ADMIN","Admin role"));
		
		User user=new User();
		
		user.setUserName("admin");
		user.setFirstName("Pardeep");
		user.setLastName("K");
		
		user.setPassword(passwordEncoder.encode("password"));
		user.setEnabled(true);
		user.setAuthorities(authorityList);
		
		userDetailsRepository.save(user);
		
		
		
	}
	
	
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}
}
