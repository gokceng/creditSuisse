package roi.com.suisse.gokcen.demo.log.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import roi.com.suisse.gokcen.demo.log.model.LogEntry;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogParserTest {
  @Mock
  private ObjectMapper objectMapper;
  private LogParser logParser;

  @Before
  public void setUp() throws Exception {
    logParser = new LogParser(objectMapper);
  }

  @Test
  public void parseLogLineSuccessfully() throws IOException {
    String logLine = "success";
    LogEntry toReturn = new LogEntry("id", "state", 1L);
    when(objectMapper.readValue(logLine, LogEntry.class)).thenReturn(toReturn);

    LogEntry logEntry = logParser.parseLogLine(logLine);
    assertSame(toReturn, logEntry);
  }

  @Test
  public void parseLogLineWithError() throws IOException {
    String logLine = "error";
    when(objectMapper.readValue(logLine, LogEntry.class)).thenThrow(new IOException("On purpose error"));

    LogEntry logEntry = logParser.parseLogLine(logLine);
    assertNull(logEntry);
  }
}