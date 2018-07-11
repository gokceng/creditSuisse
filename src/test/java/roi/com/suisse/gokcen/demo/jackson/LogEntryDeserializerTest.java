package roi.com.suisse.gokcen.demo.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import roi.com.suisse.gokcen.demo.log.model.ApplicationLogEntry;
import roi.com.suisse.gokcen.demo.log.model.LogEntry;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogEntryDeserializerTest {
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private JsonParser jsonParser;
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private JsonNode jsonNode;
  private LogEntryDeserializer deserializer;

  @Before
  public void setUp() throws IOException {
    deserializer = new LogEntryDeserializer(LogEntry.class);

    when(jsonParser.getCodec().readTree(jsonParser)).thenReturn(jsonNode);

    when(jsonNode.get("id").asText()).thenReturn("id");
    when(jsonNode.get("state").asText()).thenReturn("state");
    when(jsonNode.get("timestamp").asLong()).thenReturn(1L);
  }

  @Test
  public void deserializeLogEntry() throws IOException {
    when(jsonNode.has("type")).thenReturn(false);

    LogEntry logEntry = deserializer.deserialize(jsonParser, null);

    assertFalse(logEntry instanceof ApplicationLogEntry);
    assertEquals("id", logEntry.getId());
    assertEquals("state", logEntry.getState());
    assertEquals(1L, logEntry.getTimestamp());
  }

  @Test
  public void deserializeApplicationLogEntry() throws IOException {
    when(jsonNode.has("type")).thenReturn(true);
    when(jsonNode.get("type").asText()).thenReturn("type");
    when(jsonNode.get("host").asText()).thenReturn("host");

    LogEntry logEntry = deserializer.deserialize(jsonParser, null);

    assertTrue(logEntry instanceof ApplicationLogEntry);
    ApplicationLogEntry applicationLogEntry = (ApplicationLogEntry) logEntry;
    assertEquals("id", applicationLogEntry.getId());
    assertEquals("state", applicationLogEntry.getState());
    assertEquals(1L, applicationLogEntry.getTimestamp());
    assertEquals("type", applicationLogEntry.getType());
    assertEquals("host", applicationLogEntry.getHost());
  }
}