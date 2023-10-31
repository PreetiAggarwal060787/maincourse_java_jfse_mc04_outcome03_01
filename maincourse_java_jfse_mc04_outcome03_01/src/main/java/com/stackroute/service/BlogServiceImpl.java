package com.stackroute.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.domain.Blog;
import com.stackroute.repository.BlogRepository;

/* Add annotation to declare this class as Service class.
 * Also it should implement BlogService Interface and override all the implemented methods.*/
@Service
public class BlogServiceImpl implements BlogService {

	private BlogRepository blogRepository;

	@Autowired
	public BlogServiceImpl(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	@Override
	public Blog saveBlog(Blog blog) {
		try {
			return blogRepository.save(blog);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public List<Blog> getAllBlogs() {
		return (List<Blog>) blogRepository.findAll();
	}

	@Override
	public Blog getBlogById(int id) {
		Optional<Blog> res = blogRepository.findById(id);
		if (res.isPresent()) {
			return res.get();
		}
		return null;
	}

	@Override
	public Blog deleteBlog(int id) {
		Optional<Blog> res = blogRepository.findById(id);
		if (res.isPresent()) {
			Optional<Blog> res1 = blogRepository.findById(id);
			blogRepository.deleteById(res1.get().getBlogId());
			return res.get();
		}
		return null;
	}

	@Override
	public Blog updateBlog(Blog blog) {
		Optional<Blog> res = blogRepository.findById(blog.getBlogId());
		if (res.isPresent()) {
			Blog res1 = blogRepository.findById(res.get().getBlogId()).get();
			res1.setAuthorName(blog.getAuthorName());
			res1.setBlogContent(blog.getBlogContent());
			res1.setBlogTitle(blog.getBlogTitle());
			return blogRepository.save(res1);
		}
		return null;
	}
}