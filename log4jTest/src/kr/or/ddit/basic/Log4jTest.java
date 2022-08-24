package kr.or.ddit.basic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {
	// log기록을 남기는 파일
	// 1. Logger객체 생성
	static Logger logger = Logger.getLogger(Log4jTest.class);

	public static void main(String[] args) {
		// 2. 로그 기록 남기기
		// 실행하면 info 이후의 정보들만 출력된다.
		// 형식1) logger.레벨명("출력할 메세지");
		logger.trace("이것은 log4j의 Trace모드 입니다.");
		logger.debug("이것은 log4j의 Debug모드 입니다.");
		logger.info("이것은 log4j의 Info모드 입니다.");
		logger.warn("이것은 log4j의 Warn모드 입니다.");
		logger.error("이것은 log4j의 Error모드 입니다.");
		logger.fatal("이것은 log4j의 Fatal드 입니다.");
		
		// 형식2) logger.log(level.레벨명, "출력할 메세지");
		logger.log(Level.TRACE, "Trace모드의 출력형식 2 입니다.");
		logger.log(Level.INFO, "Info모드의 출력형식 2 입니다.");
		logger.log(Level.ERROR, "Error모드의 출력형식 2 입니다.");
		
		
		
	}

}
