<%@page import="kr.or.ddit.lprod.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.ibatis.config.SqlMapClientFactory"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SqlMapClient smc = SqlMapClientFactory.getSqlMapClient();

	List<LprodVO> list = smc.queryForList("lprod.selectAll");
%>

[
<%
	for(int i = 0; i < list.size(); i++){
		LprodVO vo = list.get(i);
		if(i > 0) out.print(",");
%>
		{
			"id" : "<%= vo.getLprod_id() %>",
			"gu" : "<%= vo.getLprod_gu() %>",
			"nm" : "<%= vo.getLprod_nm() %>"
		}
		
<%
	}
%>
]