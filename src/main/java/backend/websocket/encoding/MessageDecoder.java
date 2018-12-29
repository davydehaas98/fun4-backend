package backend.websocket.encoding;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * Decodes JSON, and calls the method on an interface.
 *
 * For example, this JSON would be decoded to `receiver.doStuff("abc", 123)`
 * <pre>
 * {
 *   "type": "doStuff",
 *   "args": [
 *      "abc",
 *      123
 *   ]
 * }
 * </pre>
 */

public class MessageDecoder<T> {

  private final T receiver;
  private final ObjectMapper mapper;
  private final Map<String, Method> types;

  public MessageDecoder(Class<T> clazz, T receiver, ObjectMapper mapper) {
    this.receiver = Objects.requireNonNull(receiver, "receiver must not be null");
    this.mapper = Objects.requireNonNull(mapper, "ObjectMapper must not be null");
    types = Arrays.stream(clazz.getDeclaredMethods())
        .peek(m -> {
          if (!m.getReturnType().equals(Void.TYPE)) {
            throw new IllegalArgumentException("interface methods must not have return type");
          }
        })
        .collect(Collectors.toMap(
            Method::getName,
            Function.identity(),
            (a, b) -> {
              throw new IllegalArgumentException("interface methods must not have overloads");
            }
        ));
  }

  public void accept(byte[] bytes) throws DecodingException, InvocationTargetException {
    try {
      // We cannot decode the arguments yet, because we don't now the types
      // So just parse it to a tree
      JsonNode message = mapper.readTree(bytes);
      JsonNode jsonType = message.get("type");
      if (jsonType == null || !jsonType.isTextual()) {
        throw new DecodingException("no type provided");
      }
      String type = jsonType.asText();
      JsonNode jsonArguments = message.get("args");
      // Find the method with name specified in type
      Method method = types.get(type);
      if (method == null) {
        throw new DecodingException("type not found");
      }
      // Parse the arguments to the right types
      Object[] arguments = parseArguments(jsonArguments, method.getParameters());
      // Call the method
      method.setAccessible(true);
      method.invoke(receiver, arguments);
    } catch (IOException e) {
      throw new DecodingException("could not read json", e);
    } catch (IllegalAccessException e) {
      throw new IllegalStateException("method is inaccessible", e);
    }
  }

  private Object[] parseArguments(JsonNode json, Parameter[] parameters) throws DecodingException {
    if (parameters.length == 0) {
      if (json.size() > 0) {
        throw new DecodingException("not expected arguments");
      }
      return new Object[0];
    }
    if (json == null || !json.isArray()) {
      throw new DecodingException("no arguments provided");
    }
    if (json.size() != parameters.length) {
      throw new DecodingException("invalid argument count");
    }
    Object[] arguments = new Object[parameters.length];
    for (int i = 0; i < parameters.length; i++) {
      // Use getParameterizedType() to support generics, like List<MyObject>
      Type parameterType = parameters[i].getParameterizedType();
      JavaType javaType = mapper.getTypeFactory().constructType(parameterType);
      try {
        arguments[i] = mapper.readValue(mapper.treeAsTokens(json.get(i)), javaType);
      } catch (IOException e) {
        throw new DecodingException("unable to parse argument");
      }
    }
    return arguments;
  }

  public T getReceiver() {
    return receiver;
  }
}
