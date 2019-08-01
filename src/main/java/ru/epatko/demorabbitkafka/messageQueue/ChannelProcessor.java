package ru.epatko.demorabbitkafka.messageQueue;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ChannelProcessor {

    String INPUT = "inputChannel";

    @Input("inputChannel")
    SubscribableChannel inputChannel();

    @Output("outputChannel")
    MessageChannel outputChannel();
}
