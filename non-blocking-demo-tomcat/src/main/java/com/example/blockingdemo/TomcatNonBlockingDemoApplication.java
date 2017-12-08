package com.example.blockingdemo;

import java.time.Duration;
import java.util.UUID;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TomcatNonBlockingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TomcatNonBlockingDemoApplication.class, args);
	}

	@RestController
	public static class TestController {

		@GetMapping("/small")
		public Mono<String> get() {
			return Mono.just("Hello")
			           .delaySubscription(Duration.ofSeconds(1));
		}

		@GetMapping("/large")
		public Flux<Container> getLarge() {

			return Flux.range(0, 50)
			           .map(i -> new Container(UUID.randomUUID()))
			           .delaySubscription(Duration.ofMillis(1000));
		}

		@PostMapping("/post")
		public void post(Flux<Container> input) {
			input.subscribe(Object::toString);
		}

		@PostMapping("/post-reply")
		public Flux<Container> postAndReplay(Flux<Container> input) {
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
