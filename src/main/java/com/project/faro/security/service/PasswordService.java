package com.project.faro.security.service;

public interface PasswordService {
	boolean checkPasswod(String password, String userName, String password1);

	String generateDefaultPassword(String username);

	String generatePassword(String pass, String username);
}
