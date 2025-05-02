package vn.iotstar.TheLaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "vn.iotstar.TheLaApp")
public class TheLaApp {
	public static void main(String[] args) {
		SpringApplication.run(TheLaApp.class, args);
	}
}
