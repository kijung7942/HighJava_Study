package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest03 {

	public static void main(String[] args) {
		FileTest03 test = new FileTest03();
		
		File file = new File("c:/Users/admin");
		test.displayFileList(file);
		
	}

	//디렉토리를 매개변수로 받아서 해당 디렉토리에 있는 모든 파일과 디렉토리 목록을 출력하는 메서드
	public void displayFileList(File dir) {
		if(!dir.isDirectory()) {
			System.out.println(" 디렉토리(폴더)만 사용가능 합니다.");
			System.out.println(" 작업을 종료합니다.");
			return;
		}
		else {
			System.out.println("["+dir.getAbsolutePath()+"] 디렉토리 내용");
			
			// 목록 가져오기
			
			File[] files = dir.listFiles();
//			String[] strFiles = dir.list(); // 이 클래스에서는 파일들의 정보를 꺼내와야하므로 File객체로 받아주는 것이 나음.
			
			// 날짜 출력 형식 만들기
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
			
			// 가져온 파일과 디렉토리 목록 개수만큼 반복 처리
			
			for(int i = 0 ; i < files.length; i++) {
				String fileName = files[i].getName();
				String attr = "";	// 파일의 속성 (읽기, 쓰기, 히든, 디렉토리 여부)
				String size = "";	// 파일 크기를 문자열로 저장할 변수

				if(files[i].isDirectory()) {
					attr = "<DIR>";
				}else {
					size = String.valueOf(files[i].length()); // 숫자를 문자열로 변환
					attr = files[i].canRead()?"R":"";
					attr = files[i].canWrite()?"W":"";
					attr = files[i].isHidden()?"H":"";
				}
				/* printf => ("출력할 내용 포맷",데이터1,데이터2,데이터3...)
				*		  	-> %s : 문자열	%50s -> 50자까지 작성해주고 데이터가 짧으면 데이터 + 공란으로 채움. 
				*			-> %d : 정수형
				*			-> %f : 실수형    %3.2f  -> 소수점 앞에 3자리 소수점 뒤에 2자리
				*/
				System.out.printf("%s %5s %12s %s\n",df.format(new Date(files[i].lastModified())),attr,size,fileName);
			}
			
			
			
			
			
		}
	}


}
 