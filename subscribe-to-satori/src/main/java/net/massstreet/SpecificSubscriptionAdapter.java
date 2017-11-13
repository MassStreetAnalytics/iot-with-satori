package net.massstreet;

import com.satori.rtm.*;
import com.satori.rtm.model.*;
import java.io.*;

public class SpecificSubscriptionAdapter extends SubscriptionAdapter {
	
	 public PrintWriter out;
	
     public SpecificSubscriptionAdapter(PrintWriter out){
    	 this.out = out; 	 
     }
	
	 @Override
     public void onSubscriptionData(SubscriptionData data) {
       for (AnyJson json : data.getMessages()) {
         out.println(json.toString());
       }
	 }
}
