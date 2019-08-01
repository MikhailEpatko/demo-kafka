package ru.epatko.demorabbitkafka.messageQueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.epatko.demorabbitkafka.model.QueueMessage;

import java.util.UUID;

@Slf4j
@Component
@EnableBinding(ChannelProcessor.class)
public class MessageListener {

    @StreamListener(ChannelProcessor.INPUT)
    public void listen(Message<QueueMessage> msg) {

        QueueMessage message = msg.getPayload();
        MessageHeaders headers = msg.getHeaders();
        String messageType = (String) headers.get(MessageDefinitions.TYPE);
        final String token = extractToken(headers);
        final String clientIP = extractClientIP(headers);
        log.info("Got message: {}, client IP: {}, token: {}, message type: {}.",
                message.toString(), clientIP, token, messageType);
    }

    private String extractToken(MessageHeaders headers) {
        return (String) headers.get(MessageDefinitions.SENDER_ID);
    }

    private String extractClientIP(MessageHeaders headers) {
        return (String) headers.get(MessageDefinitions.SENDER_NAME);
    }
}
