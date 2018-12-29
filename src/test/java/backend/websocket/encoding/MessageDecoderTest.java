package backend.websocket.encoding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MessageDecoderTest {

  private static final ObjectMapper mapper = new ObjectMapper();
  private static final MyInterface mock = mock(MyInterface.class);

  static Stream<ThrowingSupplier<MessageDecoder>> nullShouldThrowException() {
    return Stream.of(
        () -> new MessageDecoder<>(null, new Object(), mapper),
        () -> new MessageDecoder<>(MyInterface.class, null, mapper),
        () -> new MessageDecoder<>(MyInterface.class, mock, null)
    );
  }

  @ParameterizedTest
  @MethodSource
  void nullShouldThrowException(ThrowingSupplier<MessageDecoder> supplier) {
    Throwable throwable = catchThrowable(supplier::get);
    assertThat(throwable)
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void overloadedMethod() {
    assertThatThrownBy(() -> constructMessageDecoder(HasOverloadedMethod.class))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("must not have overloads");
  }

  @Test
  void methodsWithReturnType() {
    assertThatThrownBy(() -> constructMessageDecoder(HasMethodWithReturnType.class))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("must not have return type");
  }

  @Test
  void genericMethod() {
    // TODO: handle generic methods
  }

  @Test
  void defaultMethod() {
    // TODO: handle generic methods
  }

  private <T> void constructMessageDecoder(Class<T> clazz) {
    new MessageDecoder<T>(clazz, mock(clazz), mapper);
  }

  interface MyInterface {

    void doStuff();
  }

  interface HasMethodWithReturnType {

    int helloWorld();

    void anotherOne();
  }

  interface HasOverloadedMethod {

    void hello(int x, int y);

    void hello(int x, int y, int z);
  }
}
