package backend.websocket.encoding;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Translates a call on an interface to a JSON-message.
 *
 * For example, the call `doStuff("abc", 123)` will be encoded to this JSON:
 * <pre>
 * {
 *   "type": "doStuff",
 *   "arguments": [
 *      "abc",
 *      123
 *   ]
 * }
 * </pre>
 */
public class MessageEncoder {

    private final Object proxy;

    private MessageEncoder(Consumer<WebSocketMessage> consumer, Class clazz) {
        proxy = Proxy
            .newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, (obj, method, args) -> {
                // Implement Object methods (hashCode(), equals() and toString())
                if (method.equals(ObjectMethods.HASH_CODE)) {
                    return hashCode();
                }
                if (method.equals(ObjectMethods.EQUALS)) {
                    return args[0] == obj;
                }
                if (method.equals(ObjectMethods.TO_STRING)) {
                    return clazz.getName() + "@" + Integer.toHexString(hashCode());
                }
                // Pass WebSocketMessage to consumer
                WebSocketMessage message = new WebSocketMessage(method.getName(), args);
                consumer.accept(message);
                return null;
            });
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> clazz, Consumer<WebSocketMessage> consumer) {
        if (clazz == null || !clazz.isInterface()) {
            throw new IllegalArgumentException("clazz must represent an interface type");
        }
        if (consumer == null) {
            throw new IllegalArgumentException("consumer must not be null");
        }
        List<Method> methods = Arrays.asList(clazz.getMethods());
        methods.stream()
            .filter(m -> !m.getReturnType().equals(Void.TYPE))
            .forEach(m -> {
                throw new IllegalArgumentException("method has return type " + m.getName());
            });
        methods.stream()
            .map(Method::getName)
            .filter(a -> methods.stream().filter(b -> b.getName().equals(a)).count() != 1)
            .forEach(m -> {
                throw new IllegalArgumentException("method has overloads " + m);
            });
        return (T) new MessageEncoder(consumer, clazz).proxy;
    }

    private static class ObjectMethods {

        private static final Method EQUALS = getObjectMethod("equals", Object.class);
        private static final Method HASH_CODE = getObjectMethod("hashCode");
        private static final Method TO_STRING = getObjectMethod("toString");

        private static Method getObjectMethod(String name, Class... types) {
            try {
                return Object.class.getMethod(name, types);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
