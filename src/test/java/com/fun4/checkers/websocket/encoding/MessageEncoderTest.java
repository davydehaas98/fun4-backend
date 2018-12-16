package com.fun4.checkers.websocket.encoding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.mockito.Mockito;

class MessageEncoderTest {

  private final ObjectMapper objectMapper;
  private final MyInterface mock;
  private final MyInterface encoder;
  private final MessageDecoder decoder;
  private MyInterface anotherEncoder;

  MessageEncoderTest() {
    objectMapper = new ObjectMapper();
    mock = Mockito.mock(MyInterface.class);
    decoder = new MessageDecoder<>(MyInterface.class, mock, objectMapper);
    encoder = createEncoder(bytes -> {
      System.out.println(new String(bytes));
      decoder.accept(bytes);
    });
    anotherEncoder = createEncoder(bytes -> System.out.println("another" + Arrays.toString(bytes)));
  }

  private static <T> Consumer<T> emptyConsumer() {
    return x -> {
    };
  }

  @BeforeEach
  void setUp() {
    Mockito.reset(mock);
  }

  @Test
  void transferSimpleMessage() {
    encoder.doStuff();
    verify(mock).doStuff();
  }

  @Test
  void myObject() {
    MyObject myObject = new MyObject(1, 2);
    encoder.doSomething(myObject);
    verify(mock).doSomething(myObject);
  }

  @Test
  void genericParameter() {
    List<MyObject> list = List.of(new MyObject(1, 2), new MyObject(3, 4));
    encoder.genericStuff(list);
    verify(mock).genericStuff(list);
  }

  @Test
  void multipleArguments() {
    encoder.point(2, 3, 4, 5);
    verify(mock).point(2, 3, 4, 5);
  }

  @Test
  void passException() {
    RuntimeException exception = new RuntimeException("test");
    doThrow(exception).when(mock).doStuff();
    createEncoderForMethod(MyInterface::doStuff, bytes -> {
      assertThatThrownBy(() -> decoder.accept(bytes))
          .isInstanceOf(InvocationTargetException.class)
          .hasCause(exception);
    });
  }

  @Test
  void getReceiver() {
    assertEquals(mock, decoder.getReceiver());
  }

  @Test
  void callToString() {
    System.out.println("encoder.toString(): " + encoder.toString());
    assertThat(encoder.toString())
        .startsWith(MyInterface.class.getName());
  }

  @Test
  void callHashCode() {
    assertThat(encoder.hashCode()).isGreaterThan(0);
  }

  @Test
  void compareHashCode() {
    assertNotEquals(encoder.hashCode(), anotherEncoder.hashCode());
  }

  @Test
  void callEquals() {
    assertEquals(encoder, encoder);
  }

  @Test
  void notEqual() {
    assertNotEquals(encoder, anotherEncoder);
  }

  private void createEncoderForMethod(Consumer<MyInterface> method, Consumer<byte[]> consumer) {
    var encoder = createEncoder(consumer::accept);
    method.accept(encoder);
  }

  private MyInterface createEncoder(ThrowingConsumer<byte[]> consumer) {
    return MessageEncoder.create(MyInterface.class, message -> {
      try {
        var bytes = objectMapper.writeValueAsBytes(message);
        consumer.accept(bytes);
      } catch (Throwable e) {
        e.printStackTrace();
      }
    });
  }

  interface MyInterface {

    void doStuff();

    void doSomething(MyObject str);

    void point(int x, int y, int... he);

    void genericStuff(List<MyObject> list);
  }

  interface HasMethodWithReturnType {

    int helloWorld();

    void anotherOne();
  }

  interface HasOverloadedMethod {

    void hello(int x, int y);

    void hello(int x, int y, int z);
  }

  static class MyObject {

    final int x;
    final int y;

    @JsonCreator
    public MyObject(@JsonProperty("x") int x, @JsonProperty("y") int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    @Override
    public String toString() {
      return String.format("MyObject{x=%d, y=%d}", x, y);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MyObject)) {
        return false;
      }
      MyObject myObject = (MyObject) o;
      return x == myObject.x &&
          y == myObject.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  @Nested
  @DisplayName("Bad Flow for MessageDecoder")
  class InvalidJson {

    @Test
    void brokenJson() {
      assertJson("this is not json")
          .hasMessage("could not read json");
    }

    @Test
    void jsonArray() {
      assertJson("[1, 2, 3]");
    }

    @Test
    void jsonNull() {
      assertJson("null");
    }

    @Test
    void nullType() {
      assertJson("{\"type\": null}");
    }

    @Test
    void arrayType() {
      assertJson("{\"type\": []}");
    }

    @Test
    void emptyObject() {
      assertJson("{}");
    }

    @Test
    void typeNotExist() {
      assertMessage("not existing type")
          .hasMessageContaining("type not found");
    }

    @Test
    void argumentOverflow() {
      assertMessage("doSomething", 1, 2)
          .hasMessageContaining("argument count");
    }

    @Test
    void anotherArgumentOverflow() {
      assertMessage("doStuff", "overflow");
    }

    @Test
    void argumentUnderflow() {
      assertMessage("doSomething")
          .hasMessageContaining("argument count");
    }

    @Test
    void anotherArgumentUnderflow() {
      assertMessage("point", 2)
          .hasMessageContaining("argument count");
    }

    @Test
    void argumentTypeMismatch() {
      assertMessage("doSomething", 1)
          .hasMessageContaining("parse argument");
    }

    private AbstractThrowableAssert assertMessage(String type, Object... args) {
      try {
        return assertJson(objectMapper.writeValueAsString(new WebSocketMessage(type, args)));
      } catch (JsonProcessingException e) {
        throw new IllegalArgumentException(e);
      }
    }

    private AbstractThrowableAssert assertJson(String json) {
      System.out.println(json);
      var exception = Assertions.catchThrowable(() ->
          decoder.accept(json.getBytes()));
      System.out.println("exception = " + exception);
      return assertThat(exception)
          .withFailMessage("no exception was thrown").isNotNull()
          .isInstanceOf(DecodingException.class);
    }
  }

  @Nested
  class MessageEncoderConstructing {

    @Test
    void onlyAcceptsInterfaceClass() {
      class MyClass {

      }
      assertThatThrownBy(() -> MessageEncoder.create(MyClass.class, emptyConsumer()))
          .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void nullClass() {
      assertThatThrownBy(() -> MessageEncoder.create(null, emptyConsumer()))
          .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void nullConsumer() {
      assertThatThrownBy(() -> MessageEncoder.create(MyInterface.class, null))
          .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void methodsWithReturnType() {
      assertThatThrownBy(
          () -> MessageEncoder.create(HasMethodWithReturnType.class, emptyConsumer()))
          .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void overloadedMethod() {
      assertThatThrownBy(() -> MessageEncoder.create(HasOverloadedMethod.class, emptyConsumer()))
          .isInstanceOf(IllegalArgumentException.class);
    }
  }
}
