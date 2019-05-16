package backend.service.interfaces;

import backend.model.User;

public interface IAuthService {

    User register(User body);

    User login(User body);

    User checkToken(User body);
}
