package com.stackroute.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.domain.Blog;
import com.stackroute.service.BlogService;

/* Add annotation to declare this class as REST Controller */
@RestController
@RequestMapping("/api/v1")
public class BlogController {

    /* Provide implementation code for these methods */
	private BlogService blogService;
	
	@Autowired
    public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	/*This method should save blog and return savedBlog Object */
	@PostMapping("/blog")
    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blogInput) {
        return new ResponseEntity<>(blogService.saveBlog(blogInput), HttpStatus.CREATED);
    }

    /*This method should fetch all blogs and return the list of all blogs */
	@GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    /*This method should fetch the blog taking its id and return the respective blog */
	@GetMapping("/blog/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(blogService.getBlogById(id), HttpStatus.FOUND);
    }

    /*This method should delete the blog taking its id and return the deleted blog */
	@DeleteMapping("/blog/{id}")
    public ResponseEntity<Blog> getBlogAfterDeleting(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(blogService.deleteBlog(id), HttpStatus.OK);
    }

    /*This method should update blog and return the updatedBlog */
	@PutMapping("/blog")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blogInput) {
        return new ResponseEntity<>(blogService.updateBlog(blogInput), HttpStatus.OK);
    }
}