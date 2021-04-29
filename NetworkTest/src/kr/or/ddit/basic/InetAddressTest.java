package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 -> IP주소를 다루기 위한 클래스
		
		//www.naver.com의 IP 정보 가져오기.
		
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		
		System.out.println("Host Name : " + naverIp.getHostName());
		System.out.println("Host Address : " + naverIp.getHostAddress());
		System.out.println("Host toString : " + naverIp.toString());
		
		System.out.println("--------------------------------------------------");
		System.out.println();
		
		// 내가 쓰고 있는 IP 주소 가져오기
		
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴 Host Name : " + localIp.getHostName());
		System.out.println("내 컴 Host Address : " + localIp.getHostAddress());
		System.out.println("내 컴 Host toString : " + localIp.toString());
		
		System.out.println("--------------------------------------------------");
		System.out.println();

		InetAddress[] ipArr = InetAddress.getAllByName("www.google.com");
		for(InetAddress i:ipArr) {
			System.out.println("Host toString : " + i.toString());
		}
		
		
		
		
				
	}
}
