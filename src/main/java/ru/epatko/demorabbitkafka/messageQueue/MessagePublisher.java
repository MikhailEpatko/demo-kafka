package ru.epatko.demorabbitkafka.messageQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ru.epatko.demorabbitkafka.model.QueueMessage;

import java.util.UUID;

@Component
public class MessagePublisher {

    private ChannelProcessor channelProcessor;
    private String senderId = UUID.randomUUID().toString();
    private String senderName = "app name";

    @Autowired
    public MessagePublisher(ChannelProcessor channelProcessor) {
        this.channelProcessor = channelProcessor;
    }

    public void sendMessage(String messageType, String requestId, String details, String token) {
        QueueMessage data = new QueueMessage(requestId, details, token);
        Message<QueueMessage> message = MessageBuilder.withPayload(data)
                .setHeader(MessageDefinitions.SENDER_ID, this.senderId.getBytes())
                .setHeader(MessageDefinitions.SENDER_NAME, this.senderName.getBytes())
                .setHeader(MessageDefinitions.TYPE, messageType.getBytes())
                .setHeader(MessageDefinitions.MESSAGE_ID, UUID.randomUUID().toString().getBytes())
                .build();
        channelProcessor.outputChannel().send(message);
    }
}
