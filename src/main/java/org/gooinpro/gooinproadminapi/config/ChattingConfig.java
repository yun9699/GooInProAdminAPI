package org.gooinpro.gooinproadminapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ChattingConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // stomp 접속 주소 url = ws://localhost:8080/ws, 프로토콜이 http가 아니다!
        registry.addEndpoint("/ws") // 연결될 엔드포인트
                .setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지를 구독(수신)하는 요청 엔드포인트
        registry.enableSimpleBroker("/sub"); // sub 경로로 시작하는 메시지는 브로커가 구독자에게 전달

        // 메시지를 발행(송신)하는 엔드포인트
        registry.setApplicationDestinationPrefixes("/pub"); // pub 경로로 시작하는 메시지는 애플리케이션에서 처리후 브로커로 전달
    }
}
