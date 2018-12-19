package com.fun4.checkers.websocket;

import com.fun4.checkers.repository.UserRepository;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WebSocketService {

  private final Map<Long, Set<WebSocketClient>> clients = new HashMap<>();

  private final UserRepository userRepository;

  public WebSocketService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public WebSocketServer connect(WebSocketClient client, Long userId) {
    clients.computeIfAbsent(userId, u -> new HashSet<>())
        .add(client);
    return new WebSocketConnection(userId);
  }

  private class WebSocketConnection implements WebSocketServer {

    private final long userId;
    @Autowired
    private WebSocketClient client;

    WebSocketConnection(long userId) {
      this.userId = userId;
      client.hello("Hello Client");
      // Send data to client
    }

    @Override
    public void hello(String name) {
      log.info("client said {}", name);
    }

    @Override
    public void disconnect() {
      clients.get(userId).remove(client);
    }
  }
}
