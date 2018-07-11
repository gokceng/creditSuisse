package roi.com.suisse.gokcen.demo.event.model;

import lombok.Data;
import roi.com.suisse.gokcen.demo.log.model.ApplicationLogEntry;
import roi.com.suisse.gokcen.demo.log.model.LogEntry;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
public class EventEntry {
  @Id
  private final String id;
  private final long duration;
  @Enumerated(value = EnumType.STRING)
  private final Type type;
  private final String host;
  private final boolean alert;

  public EventEntry(LogEntry entry, long duration) {
    this.id = entry.getId();
    this.duration = duration;
    this.alert = duration > 4;

    boolean isApplicationLogEntry = entry instanceof ApplicationLogEntry;
    this.type = isApplicationLogEntry ? Type.valueOf(((ApplicationLogEntry) entry).getType()) : null;
    this.host = isApplicationLogEntry ? ((ApplicationLogEntry) entry).getHost() : null;
  }
}
