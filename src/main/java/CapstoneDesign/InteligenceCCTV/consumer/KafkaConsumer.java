package CapstoneDesign.InteligenceCCTV.consumer;


import CapstoneDesign.InteligenceCCTV.config.CustomWebSocketHandler;
import CapstoneDesign.InteligenceCCTV.entity.CctvRecord;
import CapstoneDesign.InteligenceCCTV.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CustomWebSocketHandler webSocketHandler;
    private final RecordRepository recordRepository;

    @KafkaListener(topics = "live", groupId = "video1")
    public void consume1(byte[] message1) {
        log.info("Received video message of size: {}", message1.length);
        System.out.println("Message content: " + Arrays.toString(Arrays.copyOf(message1, 20)));
        // WebSocket을 통해 모든 클라이언트 세션에 바이너리 메시지 브로드캐스트
        webSocketHandler.broadcastToAllSessions1(message1);
    }

    @KafkaListener(topics = "blip", groupId = "blip1")
    public void consume2(String message2)
    {
        log.info("Received blip message: {}", message2);
        webSocketHandler.broadcastToAllSessions2(message2);
//        recordRepository.save(new CctvRecord(message2));
    }

//    @KafkaListener(topics = "blip", groupId = "blip1")
//    public void consume2(String message2) {
//        log.info("Received blip message: {}", message2);
//        // WebSocket을 통해 모든 클라이언트 세션에 텍스트 메시지 브로드캐스트
//        webSocketHandler.broadcastToAllSessions(message2);

//        // 최근 5분 내에 동일한 위험 상황이 발생했는지 확인
//        LocalDateTime fiveMinutesAgo = now.minusMinutes(5);
//
//        if (!recordRepository.existsByDangerAndCreatedAtAfter(message2, fiveMinutesAgo)) {
//            recordRepository.save(new CctvRecord(message2));
//        } else {
//            log.info("Duplicate blip message within 5 minutes, not saved: {}", message2);
//        }
//    }
}