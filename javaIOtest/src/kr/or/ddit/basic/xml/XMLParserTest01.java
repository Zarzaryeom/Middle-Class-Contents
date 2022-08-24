package kr.or.ddit.basic.xml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.jar.Attributes;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.helpers.DefaultHandler;

import util.ScanUtil;

public class XMLParserTest01 {

	// 응용예제 만들기
	// Dom으로 XML파일(전화번호부)을 만들고 SAX로 읽는 코드 작성
	
	private HashMap<String, Phone3> phoneBookMap;
	private String fileName = "d:/d_other/phoneData2.dat";

	// 데이터가 변경되었는지 여부를 나타내는 변수 선언
	// 데이터가 변경되면 이 변수값이 true가 된다.
	private boolean dataChange;

	// 생성자
	public XMLParserTest01() {
		// 파일 내용을 읽어와 Map에 저장한다.
		phoneBookMap = load();

		// 파일이 없거나 잘못되었을 때...
		if (phoneBookMap == null) {
			phoneBookMap = new HashMap<String, Phone3>();
		}
	}

	public static void main(String[] args) {
		new XMLParserTest01().phoneStart();
	}

	// 프로그램을 시작하는 메서드
	public void phoneStart() {
		System.out.println();
		System.out.println("**************************");
		System.out.println("    전화번호 관리 프로그램");
		System.out.println("**************************");
		System.out.println();

		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1:	insert();	break; // 등록
			case 2:	update();	break; // 수정
			case 3:	delete();	break; // 삭제
			case 4:	search();	break; // 검색
			case 5:	displayAll();	break; // 전체출력
			case 6:	save();	break; // 저장
			case 7: domsave(); break; // domparser를 활용하여 XML로 저장
			case 8: saxRead(); break; // saxparser를 활용하여 XML읽기
			case 0:	if (dataChange == true) save();
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 선택하세요.");
			}
		}
	}
	
	private void saxRead() {
		File file = new File("d:/d_other/phonBook.xml");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			SAXParser parser = factory.newSAXParser();
			MyHandler1 handler = new MyHandler1();
			parser.parse(file, handler);
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// DomParser를 활용하여 XML로 저장
	private void domsave() {
		try {
			// 모든 key값 가져오기
			Set<String> phoneKeySet = phoneBookMap.keySet();

			if (phoneKeySet.size() == 0) {
				System.out.println("등록할 전화번호 정보가 하나도 없습니다.");
			} else {

				// XML 문서 파싱
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				// 새로운 XML 생성
				// 새로운 Document 객체 생성
				Document doc = docBuilder.newDocument();

				// phoneBook 생성
				Element phoneBook = doc.createElement("phoneBook");
				doc.appendChild(phoneBook);
				// 각 속성값 채우기
				int i = 1; // 번호 출력용 변수
				for (String member : phoneKeySet) {
					Phone3 p = phoneBookMap.get(member);

				// bookMark 생성(구분)
				String num = i + ""; //bookMark 번호
				Element bookMark = doc.createElement("bookMark");
				phoneBook.appendChild(bookMark);
				// bookMark 속성
				bookMark.setAttribute("id", num);
				i++;

				// name 생성
				Element name = doc.createElement("name");
				bookMark.appendChild(name);
				// name 속성
				name.appendChild(doc.createTextNode(member));

				// phoneNum 생성
				Element phoneNum = doc.createElement("phoneNum");
				bookMark.appendChild(phoneNum);
				// phoneNum 속성
				phoneNum.appendChild(doc.createTextNode(p.getTel()));

				// addr 생성
				Element addr = doc.createElement("addr");
				bookMark.appendChild(addr);
				// addr 속성
				addr.appendChild(doc.createTextNode(p.getAddr()));
				}
				
				// XML 파일로 쓰기
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				
				// 정렬 스페이스 4칸
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				// 인코딩 형식
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				// 들여쓰기
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(
										new FileOutputStream(
											new File("d:/d_other/phonBook.xml")));
				transformer.transform(source, result);
				
				System.out.println("xml 생성 완료");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 파일로 저장된 전화번호 정보를 읽어오는 메서드
	// 반환값 : 파일에서 읽어온 데이터(Map객체)
	private HashMap<String, Phone3> load() {
		// 읽어온 데이터가 저장될 변수 선언
		HashMap<String, Phone3> pMap = null;

		File file = new File(fileName);
		if (!file.exists()) { // 저장된 파일이 없으면..
			return null;
		}

		ObjectInputStream ois = null;

		try {
			// 입력용 스트림 객체 생성
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));

			// 파일 내용을 읽어서 변수에저장한다.
			pMap = (HashMap<String, Phone3>) ois.readObject();

		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} finally {
			// 사용했던 스트림 닫기
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
				}
				;
			}
		}

		return pMap;
	}

	// 전화번호 정보를 파일로 저장하는 메서드
	private void save() {
		ObjectOutputStream oos = null;
		try {
			// 객체 출력용 스트림 객체 생성
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));

			// Map객체를 파일로 저장한다.
			oos.writeObject(phoneBookMap);

			System.out.println("저장이 완료되었습니다.");
			dataChange = false;
		} catch (IOException e) {
			System.out.println("저장 실패");
			e.printStackTrace();
		} finally {
			// 사용했던 스트림 객체 닫기
			if (oos != null)
				try {
					oos.close();
				} catch (IOException e2) {
				}
		}

	}

	// 전화번호 정보를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요");
		System.out.println("이름 >>");
		String name = ScanUtil.nextLine();

		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}

		Phone3 p = phoneBookMap.get(name);
		System.out.println(name + "씨의 전화번호 정보");
		System.out.println("=============================");
		System.out.println(" 이름 : " + p.getName());
		System.out.println(" 전화번호 : " + p.getTel());
		System.out.println(" 주손 : " + p.getAddr());
		System.out.println("=============================");
	}

	// 전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >>");
		String name = ScanUtil.nextLine();

		// 입력한 사람의 이름이 전화번호에 없으면
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}

		phoneBookMap.remove(name);

		System.out.println(name + "씨 전화번호 정보 삭제 완료");
		dataChange = true;
	}

	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >>");
		String name = ScanUtil.nextLine();

		// 입력한 사람의 이름이 전화번호정보에 없으면..
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		System.out.println("새로운 전화번호 >>");
		String newTel = ScanUtil.nextLine();

		System.out.println("새로운 주소 >>");
		String newAddr = ScanUtil.nextLine();

		phoneBookMap.put(name, new Phone3(name, newAddr, newTel));
		System.out.println(name + "씨의 전화번호 벙보 수정완료!!");
		dataChange = true;
	}

	// 전화번호 정보 전체 자료를 출력하는 메서드
	private void displayAll() {
		System.out.println();

		// 모든 key값 가져오기
		Set<String> phoneKeySet = phoneBookMap.keySet();

		System.out.println("-----------------------------------");
		System.out.println(" 번호     이름     전화번호      주소");
		if (phoneKeySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
		} else {
			int cnt = 0; // 번호 출력용 변수
			for (String name : phoneKeySet) {
				cnt++;
				Phone3 p = phoneBookMap.get(name);
				System.out.println(cnt + "  " + name + "  " + p.getTel() + "  " + p.getAddr());
			}
		}
		System.out.println("-----------------------------------");
		System.out.println("출력 끝...");
	}

	// 새로운 전화번호 정보를 등록하는 메서드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >>");
		String name = ScanUtil.nextLine();

		if (phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}

		System.out.println("전화번호 >>");
		String tel = ScanUtil.nextLine();

		System.out.println("주소 >>");
		String addr = ScanUtil.nextLine();

		// Map에 추가 방법1
		Phone3 p = new Phone3(name, addr, tel);
		phoneBookMap.put(name, p);

		dataChange = true;
		// 추가 방법2
		// phoneBookMap.put(name, new Phone2(name, addr, tel));
	}

	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println("--------------------------");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 저장");
		System.out.println("7. 전화번호 xml로 저장");
		System.out.println("8. xml 전화번호 출력 ");
		System.out.println();
		System.out.println("0. 프로그램 종료");
		System.out.println("--------------------------");
		System.out.println("번호 입력 >>");
		int num = ScanUtil.nextInt();
		return num;
	}
}

// 하나의 전화번호 정보가 저장될 class 작성
class Phone3 implements Serializable {
	private String name;
	private String addr;
	private String tel;

	// 생성자
	public Phone3(String name, String addr, String tel) {
		super();
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}

// 이벤트 핸들러 클래스 설계
class MyHandler1 extends DefaultHandler{

	private StringBuilder currentValue = new StringBuilder();

//	  @Override
//	  public void startDocument() {
//	      System.out.println("Start Document");
//	  }
//
//	  @Override
//	  public void endDocument() {
//	      System.out.println("End Document");
//	  }

	  public void startElement(String uri, String localName, String qName,
	          Attributes attributes) {

	      // reset the tag value
	      currentValue.setLength(0);

	      System.out.printf("Start Element : %s", qName);

	      if (qName.equalsIgnoreCase("bookMark")) {
	          // get tag's attribute by name
	          String id = attributes.getValue("id");
	          System.out.printf("bookMark id : %s", id);
	      }	 
	  }

	  @Override
	  public void endElement(String uri, String localName, String qName) {

//	      System.out.printf("End Element : %s", qName);
//		  System.out.println("end 반복 횟수?");

	      if (qName.equals("name")) {
//	          System.out.printf("Name : %s", currentValue.toString());
	      }else if (qName.equals("addr")) {
	          System.out.printf("addr : %s", currentValue.toString());
//	          System.out.println(currentValue.toString());
	      }else if (qName.equals("phoneNum")) {
//	          System.out.printf("phoneNum : %s", currentValue.toString());
	      }
//	      System.out.println(currentValue);
	      System.out.println();

	  }

	  @Override
	  public void characters(char ch[], int start, int length) {

	      currentValue.append(ch, start, length);
	  }
	}