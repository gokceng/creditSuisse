package roi.com.suisse.gokcen.demo.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import roi.com.suisse.gokcen.demo.log.model.ApplicationLogEntry;
import roi.com.suisse.gokcen.demo.log.model.LogEntry;

import java.io.IOException;

public class LogEntryDeserializer extends StdDeserializer<LogEntry> {
  LogEntryDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public LogEntry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);

    String id = node.get("id").asText();
    String state = node.get("state").asText();
    long timestamp = node.get("timestamp").asLong();

    boolean applicationLogEntry = node.has("type");
    if (!applicationLogEntry) {
      return new LogEntry(id, state, timestamp);
    }

    String type = node.get("type").asText();
    String host = node.get("host").asText();
    return new ApplicationLogEntry(id, state, timestamp, type, host);
  }
}
