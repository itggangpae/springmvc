package com.pk.db.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//Bean 생성을 자동으로 해주기 위한 어노테이션
@Component
//웹 소켓 채팅 서버 클래스
public class ChatHandler extends TextWebSocketHandler {
	//접속한 유저 목록을 가질 List를 생성
	private static List<WebSocketSession> users
		= new ArrayList<WebSocketSession>();
	
	//클라이언트가 접속했을 때 호출될 메소드
	//매개변수로 대입된 데이터가 접속한 클라이언트
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		//List에 추가
		users.add(session);
	}
	
	//클라이언트가 접속을 해제했을 때 호출될 메소드
	//매개변수로 대입된 데이터가 접속을 해제한 클라이언트
	@Override
	public void afterConnectionClosed(
		WebSocketSession session, CloseStatus status) {
		//List에서 제거
		users.remove(session);
	}
	
	//메시지가 전송되었을 때 호출되는 메소드
	@Override
	public void handleTextMessage(
		WebSocketSession session, TextMessage message) {
		//전송된 메시지를 출력
		System.out.println(message.getPayload() + "가 전송됨");
		//전송받은 메시지를 모든 유저에게 다시 전송
		for(WebSocketSession ses : users) {
			try {
				ses.sendMessage(new TextMessage(message.getPayload()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}











