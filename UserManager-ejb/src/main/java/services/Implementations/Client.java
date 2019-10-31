package tn.client;

import tn.greeting.GreetingPortype;
import tn.greeting.GreetingService;

public class Client {

	public static void main (String[] args) {
		
		GreetingService proxy = new GreetingService();					//Service
		GreetingPortype service = proxy.getGreetingPort();  			//Interface
		System.out.println(service.greetingOperation());				//methode
		System.out.println(service.greetingToOperation("Ben Foulen"));  //methode
		
	}
}
