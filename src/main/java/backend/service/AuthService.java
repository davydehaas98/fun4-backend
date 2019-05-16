package backend.service;

import backend.model.User;
import backend.repository.AuthRepository;
import backend.service.interfaces.IAuthService;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final AuthRepository repository;

    public AuthService(AuthRepository repository) {
        this.repository = repository;
    }

    public User register(User body) {
        if (body.getUsername().length() < 3 || body.getUsername().length() > 32 ||
            body.getPassword().length() < 6 || body.getPassword().length() > 32) {
            return null;
        }

        body.setPassword(BCrypt.hashpw(body.getPassword(), BCrypt.gensalt(10)));

        return repository.save(body);
    }

    public User login(User body) {
        User user = repository.findByUsername(body.getUsername());

        if (user == null || !BCrypt.checkpw(body.getPassword(), user.getPassword())) {
            return null;
        }

        user.setToken(UUID.randomUUID().toString());

        return repository.save(user);
    }

    public User checkToken(User body) {
        return repository.findUserByIdAndToken(body.getId(), body.getToken());
    }
}
