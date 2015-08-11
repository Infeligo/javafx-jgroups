package sample;

import java.util.function.Consumer;

public interface Messenger {

    boolean isReady();

    void send(String message);

    void setListener(Consumer<String> listener);

}
