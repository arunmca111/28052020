package com.aadpHome.solutions.ws.iot;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.aadpHome.solutions.ws.iot.sampleUtil.SampleUtil;
import com.aadpHome.solutions.ws.iot.sampleUtil.SampleUtil.KeyStorePasswordPair;
import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;

public class IotMqttConnect {
	
	public void sendMessage(String payLoad) throws InterruptedException, AWSIotException {
	
		String clientEndpoint = "a3tcwegdowav96.iot.ap-south-1.amazonaws.com";       // replace <prefix> and <region> with your own
		String clientId = "esp8266HomeThings";                              // replace with your own client ID. Use unique client IDs for concurrent connections.
		String certificateFile = "012dcf2d07-certificate.pem.crt";                       // X.509 based certificate file
		String privateKeyFile = "012dcf2d07-private.pem.key";                        // PKCS#1 or PKCS#8 PEM encoded private key file

		// SampleUtil.java and its dependency PrivateKeyReader.java can be copied from the sample source code.
		// Alternatively, you could load key store directly from a file - see the example included in this README.
		KeyStorePasswordPair pair = SampleUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile);
		AWSIotMqttClient client = new AWSIotMqttClient(clientEndpoint, clientId, pair.keyStore, pair.keyPassword);
		try {
		// optional parameters can be set before connect()
		
			client.connect();
			
			String topicName = "topic1";
			AWSIotQos qos = AWSIotQos.QOS0;

			MyTopic topic = new MyTopic(topicName, qos);
			client.subscribe(topic);

			Thread.sleep(2000);
			
			//String payload = new String("Payload from spring boot");
			 MqttMessage messagePayload = new MqttMessage(payLoad.getBytes());
			client.publish(topicName, qos, messagePayload.toString());
			
			client.disconnect();
			//client.publish(topic);
			
		} catch (AWSIotException e) {
			client.disconnect();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

 class MyTopic extends AWSIotTopic {
    public MyTopic(String topic, AWSIotQos qos) {
        super(topic, qos);
    }

    @Override
    public void onMessage(AWSIotMessage message) {
        System.out.println("Message recived "+message.getStringPayload());
    }
}
