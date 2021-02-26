package com.alkemy.springboot.challenge.data.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.alkemy.springboot.challenge.data.app.models.entity.Post;

public interface IPostDao extends CrudRepository<Post, Long> {

}
