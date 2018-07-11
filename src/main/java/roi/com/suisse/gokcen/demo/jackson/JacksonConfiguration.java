package roi.com.suisse.gokcen.demo.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import roi.com.suisse.gokcen.demo.log.model.LogEntry;

@Configuration
public class JacksonConfiguration {
  @Bean
  public Module logEntryHierarchyModule() {
    SimpleModule module = new SimpleModule("LogEntryHierarchyModule");
    module.addDeserializer(LogEntry.class, new LogEntryDeserializer(LogEntry.class));
    return module;
  }
}
