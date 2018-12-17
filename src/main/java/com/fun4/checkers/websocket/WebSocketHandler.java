package com.fun4.checkers.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fun4.checkers.websocket.encoding.DecodingException;
import com.fun4.checkers.websocket.encoding.MessageDecoder;
import com.fun4.checkers.websocket.encoding.MessageEncoder;
import com.fun4.checkers.websocket.encoding.WebSocketMessage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

  private final Map<WebSocketSession, MessageDecoder<WebSocketServer>> sessions = new HashMap<>();
  private final ObjectMapper objectMapper;

  @Autowired
  private WebSocketService webSocketService;

  public WebSocketHandler(ObjectMapper objectMapper) {
    log.debug("Websocket debug log enabled");
    this.objectMapper = objectMapper;
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    var client = MessageEncoder.create(WebSocketClient.class, m -> sendMessage(session, m));
    var connection = webSocketService.connect(client, 1L);
    var decoder = new MessageDecoder<>(WebSocketServer.class, connection, objectMapper);
    sessions.put(session, decoder);
    log.info("Client connected");
  }

  private void sendMessage(WebSocketSession session, WebSocketMessage message) {
    if (!session.isOpen()) {
      log.warn("WebSocket-message encoded, but session closed " + message);
      return;
    }
    try {
      var textMessage = new TextMessage(objectMapper.writeValueAsBytes(message));
      session.sendMessage(textMessage);
      log.debug("Send WebSocket-message: " + textMessage.getPayload());
    } catch (IOException e) {
      log.warn("Could not send WebSocket-message", e);
    }
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
    log.debug("Received WebSocket-message: " + message.getPayload());
    try {
      sessions.get(session).accept(message.asBytes());
    } catch (DecodingException e) {
      log.warn("DecodingException", e);
      log.warn("Closing WebSocket session: " + message.getPayload());
      session.close(CloseStatus.BAD_DATA.withReason(e.getMessage()));
    } catch (InvocationTargetException e) {
      log.warn("Closing WebSocket session: Server Error", e);
      session.close(CloseStatus.SERVER_ERROR.withReason(e.getMessage()));
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    var connection = sessions.remove(session);
    if (connection != null) {
      connection.getReceiver().disconnect();
    }
    log.info(String.format(
        "Disconnected, code: %d, reason: %s", status.getCode(), status.getReason()
    ));
  }
}
