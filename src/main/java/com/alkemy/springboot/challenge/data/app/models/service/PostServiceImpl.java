package com.alkemy.springboot.challenge.data.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.springboot.challenge.data.app.models.dao.IPostDao;
import com.alkemy.springboot.challenge.data.app.models.entity.Post;

@Service
public class PostServiceImpl implements IPostService{

	@Autowired
	private IPostDao postDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Post> findAll() {
		
		return (List<Post>) postDao.findAll();
	}

	@Override
	@Transactional
	public void save(Post post) {
	
		postDao.save(post);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Post findOne(Long id) {
		// TODO Auto-generated method stub
		return postDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		//Luego se cambia logica
		postDao.deleteById(id);;
		
	}

}
