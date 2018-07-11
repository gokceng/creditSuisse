package roi.com.suisse.gokcen.demo.event.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import roi.com.suisse.gokcen.demo.event.model.EventEntry;
import roi.com.suisse.gokcen.demo.log.service.LogScanner;

@Service
public class EventService {
  private final EventRepository repository;
  private final LogScanner logScanner;

  public EventService(EventRepository repository, LogScanner logScanner) {
    this.repository = repository;
    this.logScanner = logScanner;
  }

  public void parseEvents(String path) {
    logScanner.read(path);
  }

  @EventListener
  public void saveEvent(EventEntry entry) {
    repository.save(entry);
  }
}
