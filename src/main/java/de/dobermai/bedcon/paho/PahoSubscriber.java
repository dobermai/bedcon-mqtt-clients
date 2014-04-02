package de.dobermai.bedcon.paho;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author Dominik Obermaier
 */
public class PahoSubscriber {


    public static void main(String[] args) throws Exception {
        new PahoSubscriber().start();
    }

    private void start() throws Exception {

        final MqttClient client = new MqttClient("tcp://localhost:1883", "subscriber", new MemoryPersistence());

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("Message arrived on topic " + topic + " : " + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        client.connect();

        client.subscribe("#");
    }
}
