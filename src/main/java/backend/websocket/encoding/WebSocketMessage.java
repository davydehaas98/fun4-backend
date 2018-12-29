package backend.websocket.encoding;

import java.util.Arrays;
import java.util.Objects;

public class WebSocketMessage {

  private final String type;
  private final Object[] args;

  WebSocketMessage(String type, Object[] args) {
    this.type = type;
    this.args = args;
  }

  public String getType() {
    return type;
  }

  public Object[] getArgs() {
    return args;
  }

  @Override
  public String toString() {
    return String.format("WebSocketMessage{type='%s', args=%s}", type, Arrays.toString(args));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WebSocketMessage)) {
      return false;
    }
    WebSocketMessage that = (WebSocketMessage) o;
    return Objects.equals(type, that.type) &&
        Arrays.equals(args, that.args);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(type);
    result = 31 * result + Arrays.hashCode(args);
    return result;
  }
}
