package net.massstreet;

import com.satori.rtm.*;
import java.net.*;
import java.io.*;

public class SubscribeToWeatherChannel {
  static final String endpoint = "wss://open-data.api.satori.com";
  static final String appkey = "CDDCd00a45FC9BaBCb17A3eC93EB6C87"; //sign up for a Satori account to get a permanent key.
  static final String channel = "full-weather";
  

  public static void main(String[] args) throws InterruptedException {
	  
	int port = Integer.parseInt(args[0]);
	
    final RtmClient client = new RtmClientBuilder(endpoint, appkey)
        .setListener(new RtmClientAdapter() {
          @Override
          public void onEnterConnected(RtmClient client) {
            System.out.println("Connected to Satori RTM!");
          }
        })
        .build();
    
    try{
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("Waiting for clients...");
    
    Socket socket = serverSocket.accept();
    
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

    SpecificSubscriptionAdapter listener = new SpecificSubscriptionAdapter(out); 

    client.createSubscription(channel, SubscriptionMode.SIMPLE, listener);

    client.start();
    
    }catch (Exception e)
    {
        System.out.println(e.toString());
    }
    
  }
}
