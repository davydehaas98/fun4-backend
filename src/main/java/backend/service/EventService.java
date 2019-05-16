package backend.service;

import backend.model.Event;
import backend.repository.EventRepository;
import backend.service.interfaces.IEventService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Collection<Event> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public Event save(Event body) {
        return repository.save(body);
    }

    public Event edit(Long id, Event body) {
        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
