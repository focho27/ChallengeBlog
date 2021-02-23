package com.alkemy.springboot.challenge.data.app.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alkemy.springboot.challenge.data.app.models.entity.Post;
import com.alkemy.springboot.challenge.data.app.models.service.IPostService;


@Controller
public class PostController {

	@Autowired
	private IPostService postService;
	
	@GetMapping(value="/posts")
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de posts");
		model.addAttribute("posts",postService.findAll());
		return "posts";
	}
	@GetMapping(value="/posts/{id}")
	public String ver(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		Post post = postService.findOne(id);
		if(post == null) {
			flash.addFlashAttribute("error", "El post solicitado no existe, pruebe con otro Id");
			return "redirect:/posts";
		}
	
		model.addAttribute("post",post);
		
		model.addAttribute("titulo", "Detalle del post: " + post.getTitulo());
		return "posts";
	}
}
