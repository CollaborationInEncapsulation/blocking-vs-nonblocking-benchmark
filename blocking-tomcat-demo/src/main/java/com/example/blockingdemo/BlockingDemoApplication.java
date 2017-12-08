package com.example.blockingdemo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BlockingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockingDemoApplication.class, args);
	}

	@RestController
	public static class TestController {

		@GetMapping("/small")
		public String get() throws InterruptedException {
			Thread.sleep(1000);

			return "Hello";
		}

		@GetMapping("/large")
		public List<Container> getLarge() throws InterruptedException {
			Thread.sleep(1000);

			return Stream.generate(() -> new Container(UUID.randomUUID()))
			             .limit(50)
			             .collect(Collectors.toList());
		}

		@PostMapping("/post")
		public void post(List<Container> input) {
			input.forEach(Object::toString);
		}

		@PostMapping("/post-reply")
		public List<Container> postAndReplay(List<Container> input) {
			return input;
		}
	}

	public static class Container {

		private String uuid;

		public Container(UUID uuid) {
			this.uuid = uuid.toString();
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
	}
}
