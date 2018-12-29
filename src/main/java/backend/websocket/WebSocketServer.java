package backend.websocket;

public interface WebSocketServer {

  void hello(String name);

  void disconnect();
}
