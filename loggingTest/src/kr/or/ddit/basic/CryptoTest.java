package kr.or.ddit.basic;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		String msg = "Hello, world 안녕하세요 1235 &*()_+";
		String key = "12@!#214124!@#!@#23"; // 암호화에 사용할 키 값
		
		
		System.out.println("원본 데이터: " + msg);
		String result = CryptoUtil.sha512(msg);
		System.out.println("단방향 암호화 : " + result + " - " + result.length());
		
		System.out.println();
		
		String encryptStr = CryptoUtil.encryptAES256(msg, key);
		System.out.println("양방향(AES) 암호화 : " + encryptStr + "-" + encryptStr.length());

		
		System.out.println();
		
		String decryptStr = CryptoUtil.decryptAES256(encryptStr, key);
		System.out.println("양방향(AES) 복호화 : " + decryptStr);
	}

}
