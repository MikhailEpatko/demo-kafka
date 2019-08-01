package ru.epatko.demorabbitkafka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueMessage {

    private String requestId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String details;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
}
