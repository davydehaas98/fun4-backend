package backend.websocket.encoding;

public class DecodingException extends Exception {

  DecodingException(String message) {
    super(message);
  }

  DecodingException(String message, Throwable cause) {
    super(message, cause);
  }
}
