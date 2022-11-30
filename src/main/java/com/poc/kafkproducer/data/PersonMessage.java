package com.poc.kafkproducer.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonMessage {

    private Key key;
    private Value value;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Key {
        private Integer id;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Value {
        private String content;
    }

}
