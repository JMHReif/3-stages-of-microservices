package com.jmhreif.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.ZonedDateTime;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}

@RestController
@RequestMapping("/neo")
@AllArgsConstructor
class ReviewController {
	private final ReviewRepository repo;

	@GetMapping
	Flux<Review> getReviews() { return repo.findFirst1000By(); }
}

interface ReviewRepository extends ReactiveNeo4jRepository<Review, String> {
	Flux<Review> findFirst1000By();
}

@Data
@Node
class Review {
	@Id
	private String review_id;
	private String book_id, review_text;
	private ZonedDateTime date_added;
	private Integer rating;
}