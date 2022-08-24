package kr.or.ddit.basic.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.simple.parser.ParseException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class AstroSAX {

	public static void main(String[] args) throws IOException, ParseException {
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/AstroEventInfoService/getAstroEventInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=tlkJ0%2BGNSZ3tibJ5IV7jCZpghwMWXWuwQEhsKGlp3singPDLV2bHnBgFLekiuWzPyBRMliAB2S%2BzLodvSyfAqw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode("2022", "UTF-8")); /*연*/
        urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode("08", "UTF-8")); /*월*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            new AstroSAX().saxRead(conn.getInputStream()); 
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());
        
        
//        new AstroSAX().saxRead(sb.toString()); 
        
        
         
	}
	
	
//	private void saxRead(String string) {
	private void saxRead(InputStream ins) {
		//File file = new File("d:/d_other/phoneBook.xml");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		
		try {
			SAXParser parser = factory.newSAXParser();
			PhoneBookSaxHandler handler = new PhoneBookSaxHandler();
//			parser.parse(string, handler);
			parser.parse(ins, handler);
			
			
			List<Astro> list = handler.getPersonList();
			System.out.println("실험 1 : " + list.get(0).getAstroTitle());
			System.out.println("실험 2 : " + list.size());
			
			for(Astro p : list) {
				System.out.println(p);
				p.getAstroTitle();
				p.getAstroEvent();
				p.getAstroTime();
				p.getLocdate();
				
			}
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


//이벤트 핸들러 클래스 설계
class PhoneBookSaxHandler extends DefaultHandler{
	//파싱한 객체를 넣을 리스트
	private List<Astro> pbList;
	
	//파싱한 객체
	private Astro pb;
	
	//character 메서드에서 저장할 문자열 변수
	private String str;
	
	public PhoneBookSaxHandler() {
		pbList = new ArrayList<>();
	}
	
	public void startElement(String uri, String localName, String name, Attributes att) {
		//시작 태그를 만났을 때 발생하는 이벤트
		if(name.equals("item")) {
			pb = new Astro();
			pbList.add(pb);
		}
	}
	public void endElement(String uri, String localName, String name) {
		//끝 태그를 만났을 때,
		if(name.equals("astroEvent")) {
			pb.setAstroEvent(str);
		}else if(name.equals("astroTime")) {
			pb.setAstroTime(str);
		}else if(name.equals("astroTitle")) {
			pb.setAstroTitle(str);
		}else if(name.equals("locdate")) {
			pb.setLocdate(str);
		}
	}
	public void characters(char[] ch, int start, int length) {
		//태그와 태그 사이의 내용을 처리
		str = new String(ch,start,length);
	}
    public List<Astro> getPersonList(){
		return pbList;
	}
	public void setPersonList(List<Astro> personList) {
		this.pbList=personList;
	}
	
}

//xml을 파싱하여 저장할 클래스
class Astro{
	private String locdate ; // 날짜
	private String astroTitle ; // 천문현상 명
	private String astroTime ; // 천문현상 시간
	private String astroEvent; // 천문현상
	
	public String getLocdate() {
		return locdate;
	}

	public void setLocdate(String locdate) {
		this.locdate = locdate;
	}

	public String getAstroTitle() {
		return astroTitle;
	}

	public void setAstroTitle(String astroTitle) {
		this.astroTitle = astroTitle;
	}

	public String getAstroTime() {
		return astroTime;
	}

	public void setAstroTime(String astroTime) {
		this.astroTime = astroTime;
	}

	public String getAstroEvent() {
		return astroEvent;
	}

	public void setAstroEvent(String astroEvent) {
		this.astroEvent = astroEvent;
	}

	@Override
	public String toString() {
		return "천문현상명 : " + astroTitle   + ", 천문현상 : " + astroEvent   + ", 천문현상 시간 : " + astroTime  + ", 날짜 : " + locdate ;
	}
	
}
