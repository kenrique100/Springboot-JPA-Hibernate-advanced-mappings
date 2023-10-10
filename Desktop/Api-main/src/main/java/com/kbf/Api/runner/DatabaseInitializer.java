package com.kbf.Api.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kbf.Api.model.User;
import com.kbf.Api.security.WebSecurityConfig;
import com.kbf.Api.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	

	@Override
	public void run(String... args) {
		
		if (!userService.getUsers().isEmpty()) {
			return;
		}
		USERS.forEach(user -> {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userService.saveUser(user);
		});
		log.info("Database initialized");

	}

	private static final List<User> USERS = Arrays.asList(
			new User("demosez", "Fai@1969Inne", "Demosez", "karimbetm@gmail.com", WebSecurityConfig.ADMIN),
			new User("boni", "Boni@Bug99/Kombe", "Nangfack Boniface", "nangfackboniface@yahoo.fr",
					WebSecurityConfig.USER),
			new User("ptata", "Paul@KombeF89tsf", "Paul Tata", "kbf@gmail.com",
					WebSecurityConfig.USER),
			new User("kombe", "Kombe@5108", "Awah Anyere", "ngwakenri2016@gmail.com", WebSecurityConfig.USER));
}
