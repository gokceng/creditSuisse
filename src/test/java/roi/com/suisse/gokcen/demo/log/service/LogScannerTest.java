package roi.com.suisse.gokcen.demo.log.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogScannerTest {
  @Mock
  private ApplicationEventPublisher eventPublisher;
  private LogScanner logScanner;

  @Before
  public void setUp() {
    logScanner = new LogScanner(eventPublisher);
  }

  @Test
  public void read() {
    String path = ClassLoader.getSystemResource("testInput.txt").getPath();
    logScanner.read(path);

    ArgumentCaptor<String> eventCaptor = ArgumentCaptor.forClass(String.class);
    verify(eventPublisher, times(6)).publishEvent(eventCaptor.capture());

    List<String> capturedEvents = eventCaptor.getAllValues();
    assertEquals("{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212}", capturedEvents.get(0));
    assertEquals("{\"id\":\"scsmbstgrb\", \"state\":\"STARTED\", \"timestamp\":1491377495213}", capturedEvents.get(1));
    assertEquals("{\"id\":\"scsmbstgrc\", \"state\":\"FINISHED\", \"timestamp\":1491377495218}", capturedEvents.get(2));
    assertEquals("{\"id\":\"scsmbstgra\", \"state\":\"FINISHED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495217}", capturedEvents.get(3));
    assertEquals("{\"id\":\"scsmbstgrc\", \"state\":\"STARTED\", \"timestamp\":1491377495210}", capturedEvents.get(4));
    assertEquals("{\"id\":\"scsmbstgrb\", \"state\":\"FINISHED\", \"timestamp\":1491377495216}", capturedEvents.get(5));
  }
}