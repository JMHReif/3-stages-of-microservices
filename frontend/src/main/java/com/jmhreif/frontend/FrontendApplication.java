package com.jmhreif.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.ZonedDateTime;

@SpringBootApplication
public class FrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendApplication.class, args);
	}

	@Bean
	WebClient client() { return WebClient.create("http://localhost:8081"); }

}

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
class ReviewController {
	private final WebClient client;

	@GetMapping
	Flux<Review> getReviews() { return client.get().uri("/neo").retrieve().bodyToFlux(Review.class); }
}

@Data
class Review {
	private String review_id;
	private String book_id, review_text;
	private ZonedDateTime date_added;
	private Integer rating;
}