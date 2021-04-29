package kr.or.ddit.basic;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class DialogTest {

	//파일을 선택하는 gui 구현
	
	public static void main(String[] args) {

		JFileChooser fileChooser = new JFileChooser(); // 파일 선택 객체

		// 파일 선택 창에 나타날 첫 폴더 선택
		fileChooser.setCurrentDirectory(new File("e:/daedoek/d_other"));
		
		
//		int result = fileChooser.showOpenDialog(new JPanel()); // 열기 창(파라미터값은 어디서 열지 정해주는 값)
		int result = fileChooser.showSaveDialog(new JPanel()); // 저장 창
		
		// 파일 선택창에서 '선택한 파일 정보' 가져오기
		
		if(result == JFileChooser.APPROVE_OPTION) {   //'열기 버튼 또는 저장 버튼' 을 클릭하면 숫자 0을 반환함.
			File selectedFile = fileChooser.getSelectedFile();
			
			System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath() );
		}
		
		
		
		
	}

}
