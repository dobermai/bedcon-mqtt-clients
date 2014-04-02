package de.dobermai.bedcon.fusesource;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.*;

/**
 * @author Dominik Obermaier
 */
public class FusesourceSubscriber {

    public static void main(String[] args) throws Exception {
        new FusesourceSubscriber().start();
    }

    private void start() throws Exception {
        MQTT mqtt = new MQTT();
        final CallbackConnection connection = mqtt.callbackConnection();
        connection.listener(new Listener() {

            public void onDisconnected() {
            }

            public void onConnected() {
            }

            public void onPublish(UTF8Buffer topic, Buffer payload, Runnable ack) {
                System.out.println("Received on" + topic.toString());
                ack.run();
            }

            public void onFailure(Throwable value) {
            }
        });

        connection.connect(new Callback<Void>() {
            public void onFailure(Throwable value) {
            }

            // Once we connect..
            public void onSuccess(Void v) {

                // Subscribe to a topic
                Topic[] topics = {new Topic("#", QoS.AT_LEAST_ONCE)};
                connection.subscribe(topics, new Callback<byte[]>() {
                    public void onSuccess(byte[] qoses) {
                    }

                    public void onFailure(Throwable value) {
                    }
                });
            }
        });
        Thread.sleep(10000);
    }
}
