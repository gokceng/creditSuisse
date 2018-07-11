package roi.com.suisse.gokcen.demo.event.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import roi.com.suisse.gokcen.demo.event.model.EventEntry;
import roi.com.suisse.gokcen.demo.log.model.LogEntry;
import roi.com.suisse.gokcen.demo.log.service.LogScanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {
  @Mock
  private EventRepository repository;
  @Mock
  private LogScanner logScanner;

  private EventService eventService;

  @Before
  public void setUp() {
    eventService = new EventService(repository, logScanner);
  }

  @Test
  public void parseEvents() {
    String somePath = "somePath";
    eventService.parseEvents(somePath);

    ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
    verify(logScanner, times(1)).read(pathCaptor.capture());
    String capturedEvent = pathCaptor.getValue();
    assertSame(somePath, capturedEvent);
  }

  @Test
  public void saveEvent() {
    EventEntry entry = new EventEntry(new LogEntry("id", "state", 1L), 1L);
    eventService.saveEvent(entry);

    ArgumentCaptor<EventEntry> pathCaptor = ArgumentCaptor.forClass(EventEntry.class);
    verify(repository, times(1)).save(pathCaptor.capture());
    EventEntry capturedEvent = pathCaptor.getValue();
    assertSame(entry, capturedEvent);
  }
}