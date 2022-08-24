package kr.or.ddit.basic.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class AstroJson2 {

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
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
		
//        JSONParser parser = new JSONParser();
//        
//        JSONObject obj = (JSONObject) parser.parse(sb.toString());
//        
//        JSONArray dataArr = (JSONArray) obj.get("data");
//        
//        System.out.println(obj.toJSONString());
        
	}

}
