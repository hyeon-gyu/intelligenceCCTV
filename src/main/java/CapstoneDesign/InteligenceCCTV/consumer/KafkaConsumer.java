package CapstoneDesign.InteligenceCCTV.consumer;


import CapstoneDesign.InteligenceCCTV.config.CustomWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;


//@Service
//@RequiredArgsConstructor
//public class KafkaConsumer {
//
//
//    private final CustomWebSocketHandler webSocketHandler;
//
//    @KafkaListener(topics = "live", groupId = "video1")
//    public void consume(String message) throws IOException {
//        System.out.println(String.format("Consumed message : %s", message));
//
//        // WebSocket을 통해 모든 클라이언트 세션에 메시지 브로드캐스트
//        webSocketHandler.broadcastToAllSessions(message);
//    }
//}
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CustomWebSocketHandler webSocketHandler;

    @KafkaListener(topics = "live", groupId = "video1")
    public void consume(byte[] message) {
        System.out.println("Received a message of size: " + message.length);

        // WebSocket을 통해 모든 클라이언트 세션에 바이너리 메시지 브로드캐스트
        webSocketHandler.broadcastToAllSessions(message);
    }
}