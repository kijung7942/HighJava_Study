package kr.or.ddit.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {

	// 단방향 암호화
	// 매개변수에 있는 문자열을 SHA-512방식으로 암호화하는 메서드
	public static String sha512(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		MessageDigest md = MessageDigest.getInstance("SHA-256");
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(str.getBytes("UTF-8"));
		
		return Base64.getEncoder().encodeToString(md.digest());
		
	}
	
	// 양방향 암호화
	// AES 256방식으로 암호화하는 메서드
	// 매개변수는 암호화할 문자열과 key값으로 사용할 문자열로 총 2개를 받는다.
	// key값으로 사용할 문자열은 16글자 이상이어야한다.
	public static String encryptAES256(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String iv = key.substring(0, 16);
		
		//key값으로 사용할 데이터가 저장될 byte배열 생성
		byte[] keyBytes = new byte[16];
		System.arraycopy(key.getBytes("UTF-8"), 0, keyBytes, 0, keyBytes.length);
		
		// 비밀키 생성
		SecretKeySpec keyspec = new SecretKeySpec(keyBytes, "AES");
		
		/*
		 * - Cipher 생성 및 초기화 ==> 알고리즘명/모드/패딩 
		 * - CBC : Cipher Block Chaining Mode : 동일한 평문 블록과 암호문 블록의 쌍이 발생하지 않도록
		 * 			이전 단계의 암복호화한 결과가 현 단계에 영향을 주는 운영 모드를 말한다.
		 * - Padding => 마지막 블록이 블록의 길이와 항상 딱 맞아 떨어지지 않기 때문에 부족한 길이만큼 '0'으로 채우거나
		 * 			임의의 비트로 채워 넣는 것을 의미한다. 
		 */
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		/*
		 *  초기화 벡터에 사용할 데이터가 저장될 byte형 배열 생성
		 *   초기화 벡터(Initial Vector, IV) - 암호문이 패턴화 되지 않도록 사용하는 데이터를 말한다.
		 *   								첫 블록을 암호화 할 때 사용하는 데이터를 말한다.
		 */
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes("UTF-8"), 0, ivBytes, 0, ivBytes.length);
		
		// 암호를 옵션 종류에 맞게 초기화 한다.
		// 옵션 종류 : ENCRYPT_MODE(암호화 모드), DECRYPT_MODE(복호화 모드)
		c.init(Cipher.ENCRYPT_MODE, keyspec, new IvParameterSpec(ivBytes));
		
		// 암호문 생성 => byte형 배열로 생성된다.
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		
		// byte 배열로 만들어진 암호문을 문자열형태로 변환한다.
		String enStr = Base64.getEncoder().encodeToString(encrypted);
		
		return enStr;
		
	}

	
	
	// AES 256 방식으로 복호화하는 메서드
	// 매개변수로는 암호화 된 문자열과 key값으로 사용할 문자열 2개를 받는다.
	// key값으로 사용할 문자열은 16글자 이상이어야 한다.
	public static String decryptAES256(String str,String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String iv = key.substring(0, 16);
		byte[] keyBytes = new byte[16];
		System.arraycopy(key.getBytes("UTF-8"), 0, keyBytes, 0, keyBytes.length);
		
		SecretKeySpec keyspec = new SecretKeySpec(keyBytes, "AES");
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes("UTF-8"), 0, ivBytes, 0, ivBytes.length);
		
		//여기까진 암호화 하는 메서드와 같음.
		
		c.init(Cipher.DECRYPT_MODE, keyspec, new IvParameterSpec(ivBytes));
		
		
		// 매개변수로 받은 암호화된 데이터를 Base64로 디코딩한다.
		byte[] byteStr = Base64.getDecoder().decode(str);
		
		// 디코딩된 데이터를 원래의 데이터로 변환 후 문자열로 만들어 반환한다.
		return new String(c.doFinal(byteStr),"UTF-8");
	}
}

