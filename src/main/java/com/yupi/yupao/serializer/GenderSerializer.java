package com.yupi.yupao.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == 1) {
            gen.writeString("男");
        } else if (value == 0) {
            gen.writeString("女");
        } else {
            gen.writeString("未知");
        }
    }
}
