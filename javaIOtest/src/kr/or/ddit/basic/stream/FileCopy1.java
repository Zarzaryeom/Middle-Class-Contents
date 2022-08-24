package kr.or.ddit.basic.stream;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;

public class FileCopy1 {

	public static void main(String[] args) {
		FileCopy1 fc = new FileCopy1();

//		File file = new File("d:/d_other/펭귄.jpg");
		File file = fc.getSelectFile("OPEN");
		if (file == null) {
			System.out.println("선택한 원본 파일이 없습니다.");
			System.out.println("복사작업 끝...");
			return;
		}

		// 원본 파일이 있는지 확인하는 과정
//		if (!file.exists()) {
//			System.out.println(file.getName() + "파일이 없습니다.");
//			System.out.println("복사작업을 중단합니다.");
//			return;
//		}

		File targetFile = fc.getSelectFile("SAVE");
		if (targetFile == null) {
			System.out.println("선택한 대상 파일이 없습니다.");
			System.out.println("복사작업 끝...");
			return;
		}

		try {
			// 복사할 파일 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream bin = new BufferedInputStream(fin);

			// 복사될 파일 스트림 객체 생성
//			FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/펭귄_복사.jpg");
			FileOutputStream fout = new FileOutputStream(targetFile);
			BufferedOutputStream bout = new BufferedOutputStream(fout);

			System.out.println("복사 시작..");

			int data;

			// 버퍼 사용시
			while ((data = bin.read()) != -1) {
				bout.write(data);
			}
			bout.flush();

			// 스트림 닫기
			bin.close();
			bout.close();

			System.out.println("복사 작업 끝...");

			// 버퍼 미 사용시
//			while((data = fin.read()) != -1){
//				fout.write(data);
//			}
//			fout.flush();
//			
//			System.out.println("복사 작업 끝...");
//			
//			//스트림 닫기
//			fin.close();
//			fout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 파일을 선택하여 선택한 파일을 반환하는 메서드
	public File getSelectFile(String option) {
		// SWING의 파일 '열기창', '저장창' 연습

		JFileChooser chooser = new JFileChooser();

		// 선택할 파일의 확장자 설정
//				FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word", "docx", "doc");
//				FileNameExtensionFilter img = new FileNameExtensionFilter("images", new String[] {"png", "jpg", "gif"});// 가변형 인수를 배열을 생성해서 쓰는 방법
//				FileNameExtensionFilter txt = new FileNameExtensionFilter("Text파일", "txt");
//				
//				// 구성한 확장자들을 추가한다.
//				chooser.addChoosableFileFilter(doc);
//				chooser.addChoosableFileFilter(img);
//				chooser.addChoosableFileFilter(txt);
//				
//				chooser.setFileFilter(img); // 확장자 목록 중에 처음부터 선택될 확장자 지정

		// 확장자 목록 중에 '모든 파일' 목록의 표시 여부를 설정하기
		// (true : 모든파일목록 보이기, false : 해제)
		chooser.setAcceptAllFileFilterUsed(true);

		// Dialog 창이 나타낼 기본 경로 설정
		// 예1) 'D:/d_other"폴더
		chooser.setCurrentDirectory(new File("D:/D_other"));

		// 예2) '바탕화면'으로 설정하기
//				chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/desktop"));

		int result;
		if ("OPEN".equals(option)) {
			// 열기용 창 보여주기
			result = chooser.showOpenDialog(new Panel());
		} else if ("SAVE".equals(option)) {
			// 저장용 창 보여주기
			result = chooser.showSaveDialog(new Panel());
		} else {
			System.out.println("option이 잘못되었습니다.");
			return null;
		}

		File selectedFile = null;
		// 창에서 '저장' 또는 '열기' 버튼을 클릭했을 때 처리
		if (result == JFileChooser.APPROVE_OPTION) {
			// 현재 선택한 파일 정보를 가져올 수 있다.
			selectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
		}

		return selectedFile;
	}

}
