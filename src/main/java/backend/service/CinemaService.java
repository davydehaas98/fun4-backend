package backend.service;

import backend.model.Cinema;
import backend.repository.CinemaRepository;
import backend.service.interfaces.ICinemaService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class CinemaService implements ICinemaService {

    private final CinemaRepository repository;

    public CinemaService(CinemaRepository repository) {
        this.repository = repository;
    }

    public Cinema findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Collection<Cinema> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public Cinema save(Cinema body) {
        return repository.save(body);
    }

    public Cinema edit(Long id, Cinema body) {
        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
