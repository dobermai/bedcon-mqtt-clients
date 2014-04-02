package de.dobermai.bedcon.paho;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author Dominik Obermaier
 */
public class PahoPublisher {


    public static void main(String[] args) throws Exception {

        new PahoPublisher().start();

    }

    private void start() throws Exception {
        final MqttClient client = new MqttClient("tcp://localhost:1883", "publisher", new MemoryPersistence());

        client.connect();

        client.publish("the/topic", "message".getBytes(), 1, false);

        client.disconnect();
    }
}
