package sample;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

import java.util.function.Consumer;

public class JGroupsMessenger implements Messenger {

    protected static final String CLUSTER_NAME = "CHAT-CLUSTER";

    protected JChannel channel;
    protected Consumer<String> listener;

    public void setChannel(JChannel channel) throws Exception {
        this.channel = channel;

        channel.setReceiver(new ReceiverAdapter() {
            public void receive(Message msg) {
                System.out.println("Received msg from " + msg.getSrc() + ": " + msg.getObject());
                if (listener != null) {
                    listener.accept((String) msg.getObject());
                }
            }
        });
        channel.connect(CLUSTER_NAME);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void send(String message) {
        try {
            channel.send(new Message(null, message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setListener(Consumer<String> listener) {
        this.listener = listener;
    }
}
