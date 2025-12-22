package com.imperative.api.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/imperative")
public class ImperativeController {
	  private final JdbcTemplate jdbcTemplate;

	    public ImperativeController(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }

	    @GetMapping("/activity/stream")
	    public List<Map<String, Object>> streamAll() {
	        return jdbcTemplate.queryForList(
	            "SELECT * FROM user_activity"
	        );
	    }

	    @GetMapping("/activity/{id}")
	    public Map<String, Object> getById(@PathVariable Long id) {
	        return jdbcTemplate.queryForMap(
	            "SELECT * FROM user_activity WHERE id = ?",
	            id
	        );
	    }

	    @GetMapping("/activity/page")
	    public List<Map<String, Object>> paged(
	            @RequestParam int offset,
	            @RequestParam int limit) {

	        return jdbcTemplate.queryForList(
	            "SELECT * FROM user_activity OFFSET ? LIMIT ?",
	            offset,
	            limit
	        );
	    }
}
