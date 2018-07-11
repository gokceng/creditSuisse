package roi.com.suisse.gokcen.demo.event.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import roi.com.suisse.gokcen.demo.event.model.EventEntry;

@Repository
public interface EventRepository extends CrudRepository<EventEntry, String> {
}
