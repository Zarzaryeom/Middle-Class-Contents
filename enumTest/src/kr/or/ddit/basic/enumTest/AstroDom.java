package kr.or.ddit.basic.enumTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AstroDom {

		public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

			//자신이 조회를 원하는 지역의 경도와 위도를 입력해주세요 
			int year = 2022; // 연도
			int month = 12; // 월
			
			// 서비스 인증키입니다. 공공데이터포털에서 제공해준 인증키를 넣어주시면 됩니다. 
			String serviceKey = "tlkJ0%2BGNSZ3tibJ5IV7jCZpghwMWXWuwQEhsKGlp3singPDLV2bHnBgFLekiuWzPyBRMliAB2S%2BzLodvSyfAqw%3D%3D"; 
			
			String enKey = "tlkJ0%2BGNSZ3tibJ5IV7jCZpghwMWXWuwQEhsKGlp3singPDLV2bHnBgFLekiuWzPyBRMliAB2S%2BzLodvSyfAqw%3D%3D";
			String deksy = "tlkJ0+GNSZ3tibJ5IV7jCZpghwMWXWuwQEhsKGlp3singPDLV2bHnBgFLekiuWzPyBRMliAB2S+zLodvSyfAqw==";
			
			// 정보를 모아서 URL정보를 만들면됩니다. 맨 마지막 "&_type=json"에 따라 반환 데이터의 형태가 정해집니다. 
			String urlStr = "http://apis.data.go.kr/B090041/openapi/service/AstroEventInfoService/getAstroEventInfo?solYear="+ year +"&solMonth="+ month +"&ServiceKey=" + serviceKey;
			System.out.println(urlStr);
			
			String ex = "https://apis.data.go.kr/B090041/openapi/service/AstroEventInfoService/getAstroEventInfo?solYear=2022&solMonth=12&ServiceKey=tlkJ0%2BGNSZ3tibJ5IV7jCZpghwMWXWuwQEhsKGlp3singPDLV2bHnBgFLekiuWzPyBRMliAB2S%2BzLodvSyfAqw%3D%3D";
			
			URL url = new URL(urlStr); 
			// 위 urlStr을 이용해서 URL 객체를 만들어줍니다. 
			
			BufferedReader bf; 
			String line = ""; 
			String result=""; 
			
			// 정보를 받아옵니다. 
			bf = new BufferedReader(new InputStreamReader(url.openStream())); 
			
			//버퍼에 있는 정보를 하나의 문자열로 변환. 
			while((line=bf.readLine())!=null){ 
			  result = result.concat(line); 
			  System.out.println(result); // 받아온 데이터를 확인해봅니다. 
			} 
			
			// XML 문서
			//File XmlFile = new File("D:/d_other/sample.xml");
			
			// XML 문서 파싱
			
			// 1. DocumentBuilderFactory : 파싱을 하는 파서 객체를 생성하기 위한 추상 클래스
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
			
			// 2. DocumentBuilder : parse() 메서드를 제공하는 추상 클래스, 이 클래스의 parse() 메서드를 호출하면 파싱을 실시한다.
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();  
			
			// 3. parse() 메서드 : 파싱 실시
			// Document : SAX와 다르게 파싱을 처리한 결과를 저장하는 클래스
			Document document = documentBuilder.parse(result);  

			
			// root 구하기
			Element root = document.getDocumentElement();  //문서의 첫 번째 Element인 루트를 구한다.
			
			// root의 속성
			//System.out.println(root.getNodeName() + " : " + root.getAttribute("name"));
			NodeList childeren = root.getChildNodes();  // root의 자식 노드 목록 get
			
			for (int i = 0; i < childeren.getLength(); i++) {
				Node node = childeren.item(i);  //item() ==> NodeList의 노드를 인덱스 값으로 반환
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {  // 해당 노드의 종류 판정 ==> 노드가 Element일 때
					Element ele = (Element) node;  // 엘리멘트 메서드를 사용하기 위해 엘리먼트로 형변환
					
					String nodeName = ele.getNodeName();  // 이름을 구하는 메서드
					System.out.println("\nnode name: " + nodeName);
					
					//구한 이름에 따라 계속
					if (nodeName.equals("body")) {  // 이름이 "teacher"일 경우
						//System.out.println("node attribute: " + ele.getAttribute("name"));  // 속성을 구하는 메서드, 파라미터에 속성의 이름을 넣어준다.
						
						// 이름이 "teacher"인 노드의 자식노드는 <name>abc</name> 형식 ==> getTextContent() 메서드 사용
						//System.out.println(ele.getElementsByTagName("tel").item(0).getTextContent());  // teacher는 자식노드가 1개이므로 for문 없이 item(0)으로 접근
						
					} else if (nodeName.equals("student")) {  // 이름이 "student"일 경우
						System.out.println("node attribute: " + ele.getAttribute("name"));  // 위와 같이 속성을 구하는 메서드
						
						// 이름이 "student"인 노드의 자식노드는 <name id = "abc"></name> 형식 ==> getAttribute()메서드 사용
						NodeList childeren2 = ele.getChildNodes();  
						
						for (int j = 0; j < childeren2.getLength(); j++) {
							Node node2 = childeren2.item(j);  // student는 자식노드가 여러개이므로 for문 사용하여 item(j)로 접근
							
							if (node2.getNodeType() == Node.ELEMENT_NODE) {  // 해당 노드의 종류 판정 ==> 노드가 Element일 때
								Element ele2 = (Element) node2; // 엘리멘트 메서드를 사용하기 위해 엘리먼트로 형변환
								
								String nodeName2 = ele2.getNodeName();  
								System.out.println(nodeName2 + " : " + ele2.getAttribute("id"));
							}
							
						}
						
					}
					
				}
				
			}
			
		}

	

}
