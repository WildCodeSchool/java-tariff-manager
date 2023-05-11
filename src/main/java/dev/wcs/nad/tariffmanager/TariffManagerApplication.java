package dev.wcs.nad.tariffmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TariffManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TariffManagerApplication.class, args);
	}

	//@Autowired
	DBInitializer dbInitializer;

	@EventListener 
	public void onAppStarted(ApplicationStartedEvent event) {
		System.out.println("started app - init data");
		// generate some data for customers and contracts
	//	dbInitializer.setupDatabaseIfNotDoneYet();
	}

}
