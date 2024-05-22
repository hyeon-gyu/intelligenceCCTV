package CapstoneDesign.InteligenceCCTV.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Component
@Slf4j
public class CustomWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
    }

    public void broadcastToAllSessions1(byte[] data) {
        sessions.values().forEach(session -> {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new BinaryMessage(data));
                }
            } catch (IOException e) {
                // 예외 로깅 추가
                log.error("Error sending binary message: ", e);
            }
        });
    }

    public void broadcastToAllSessions2(String data) {
        sessions.values().forEach(session -> {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(data));
                }
            } catch (IOException e) {
                // 예외 로깅 추가
                log.error("Error sending text message: ", e);
            }
        });
    }
}

