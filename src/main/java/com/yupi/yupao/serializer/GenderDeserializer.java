package com.yupi.yupao.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class GenderDeserializer extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        if ("男".equals(value)) {
            return 1;
        } else if ("女".equals(value)) {
            return 0;
        }
        throw new IllegalArgumentException("无效的性别值：" + value);
    }
}
