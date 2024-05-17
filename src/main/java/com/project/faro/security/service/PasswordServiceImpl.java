package com.project.faro.security.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class PasswordServiceImpl implements PasswordService {

	@Override
	public boolean checkPasswod(String password, String username, String passwordBase64) {
		boolean result = false;
		try {

			// Encripto y Codifico a BASE64 el password plano.
			String passwordEncode = cipher(password, username, Cipher.ENCRYPT_MODE);
			String passwordCipherBase64 = Base64.getEncoder().encodeToString(passwordEncode.getBytes());
			if (passwordCipherBase64 != null && passwordCipherBase64.equals(passwordBase64)) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
		//return true;
	}

	@Override
	public String generateDefaultPassword(String username) {
		String passwordEncode = cipher("1234", username, Cipher.ENCRYPT_MODE);
		String passwordCipherBase64 = Base64.getEncoder().encodeToString(passwordEncode.getBytes());
		return passwordCipherBase64;
	}
	
	@Override
	public String generatePassword(String pass, String username) {
		String passwordEncode = cipher(pass, username, Cipher.ENCRYPT_MODE);
		String passwordCipherBase64 = Base64.getEncoder().encodeToString(passwordEncode.getBytes());
		return passwordCipherBase64;
	}

	private String cipher(String str, String username, int mode) {
		String result = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(mode, generateSecretKey(username));
			result = new String(cipher.doFinal(str.getBytes()));
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return result;
	}

	private SecretKeySpec generateSecretKey(String myKey) {
		SecretKeySpec result = null;
		MessageDigest sha = null;
		byte[] key;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			result = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
