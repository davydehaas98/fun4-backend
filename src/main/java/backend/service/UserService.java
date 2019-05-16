package backend.service;

import backend.model.User;
import backend.repository.UserRepository;
import backend.service.interfaces.IUserService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Collection<User> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public User save(User body) {
        return repository.save(body);
    }

    public User edit(Long id, User body) {
        User item = repository.findById(id).orElseThrow();

        item.setUsername(body.getUsername());
        item.setPassword(body.getUsername());

        return repository.save(item);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
