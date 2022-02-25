package org.d0ms0n.midgard.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.d0ms0n.midgard.arena.model.helper.SkillType;

import java.io.IOException;

public class SkillTypeSerializer extends JsonSerializer<SkillType> {

    @Override
    public void serialize(SkillType skillType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", skillType.toString());
        jsonGenerator.writeEndObject();
    }
}
