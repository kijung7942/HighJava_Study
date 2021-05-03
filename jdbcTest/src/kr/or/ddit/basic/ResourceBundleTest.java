package kr.or.ddit.basic;

import java.util.ResourceBundle;

/*
 * 	ResourceBundle객체 ==> 파일의 확장자가 properties인 파일의 내용을 읽어와 
 * 	key값과 value값을 분리해서 분리된 정보를 갖고 있는 객체
 * 
 *  => 읽어올 파일의 내용은 'key값=value값' 형태로 되어 있어야 한다.
 */

public class ResourceBundleTest {

	public static void main(String[] args) {
		// ResourceBundle 객체를 이용하여 파일 읽어오기
		
		// ResourceBundle 객체 생성하기
		// ==> 읽어올 파일을 지정할 때 '파일명'만 지정하고 확장자는 지정하지 않는다.
		//     (이유 : 이 객체는 확장자가 properties인 파일만 읽어올 수 있기 때문
		ResourceBundle bundle = ResourceBundle.getBundle("dbinfo");
		//-----------------------------------------------------------------
		// 읽어온 내용 출력하기
		
		System.out.println(bundle.getString("driver"));
		System.out.println(bundle.getString("url"));
		System.out.println(bundle.getString("user"));
		System.out.println(bundle.getString("pass"));
	}

}
