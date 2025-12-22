package com.reactive.api.demo.controller;

import java.util.Map;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/reactive")
public class ReactiveController {
	
    private final DatabaseClient databaseClient;

    public ReactiveController(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }
    
    @GetMapping("/activity/stream")
    public Flux<Map<String, Object>> streamAll() {
        return databaseClient.sql("SELECT * FROM user_activity")
                .fetch()
                .all()
                .limitRate(100);
    }
    
    @GetMapping("/activity/{id}")
    public Mono<Map<String, Object>> getByIdReactive(@PathVariable Long id) {
        return databaseClient.sql("SELECT * FROM user_activity WHERE id = :id")
                .bind("id", id)
                .fetch()
                .one();
    }
    
    @GetMapping("/activity/page")
    public Flux<Map<String, Object>> paged(
            @RequestParam int offset,
            @RequestParam int limit) {

        return databaseClient.sql(
            "SELECT * FROM user_activity OFFSET :offset LIMIT :limit"
        )
        .bind("offset", offset)
        .bind("limit", limit)
        .fetch()
        .all();
    }

}
