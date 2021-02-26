package com.alkemy.springboot.challenge.data.app.models.service;

import java.util.List;

import com.alkemy.springboot.challenge.data.app.models.entity.Post;

public interface IPostService {

	public List<Post> findAll();

	public void save(Post post);

	public Post findOne(Long id);

	public void delete(Long id);

}
