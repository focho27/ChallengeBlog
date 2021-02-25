package com.alkemy.springboot.challenge.data.app.controllers;




import java.util.Map;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alkemy.springboot.challenge.data.app.models.entity.Post;
import com.alkemy.springboot.challenge.data.app.models.service.IPostService;

@Controller
@SessionAttributes("post")
public class PostController {

	@Autowired
	private IPostService postService;

	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		
		Post post = new Post();
		model.put("post", post);
		model.put("titulo", "Formulario de posteo");
		return "form";
	}

	@GetMapping(value = "/posts")
	public String listar(Model model) {
		
		
		model.addAttribute("titulo", "List of posts");
		model.addAttribute("posts", postService.findAll());
		return "/posts";
	}
	@GetMapping(value = "/posts/refresh")
	public String refrescar(Model model) {
		
		
		model.addAttribute("titulo", "List of posts");
		model.addAttribute("posts", postService.findAll());
		return "redirect:/posts";
	}
	
	@GetMapping(value = "/posts/{id}")
	public String listarPorId(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		Post post = postService.findOne(id);
		if(post!=null) {
			
		return "redirect:/detalle/"+post.getId().toString();}
		
		flash.addFlashAttribute("error", "The post solicited not exist, try with other Id");
		return "redirect:/posts";
	}

	@GetMapping(value = "/detalle/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Post post = postService.findOne(id);
		if (post == null) {
			flash.addFlashAttribute("error", "The post solicited not exist, try with other Id");
			return "redirect:/posts";
		}

		model.put("post", post);

		model.put("titulo", "Detail of post: " + post.getTitulo());
		return "detalle";
	}
	
	@GetMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Post post = null;
		if(id>0) {
			post = postService.findOne(id);
		if (post == null) {
			flash.addFlashAttribute("error", "The post solicited not exist, try with other Id");
			return "redirect:/posts";
		}
		}else {
			flash.addFlashAttribute("error", "The ID of Post don´t be able zero or less!");
			return "redirect:/posts";
		}
		
		model.put("post", post);

		model.put("titulo", "Edit of post: " + post.getTitulo());
		return "form";
	}
	
	@PostMapping(value = "/form")
	public String guardar(@Valid Post post, BindingResult result, Map<String, Object> model, RedirectAttributes flash,
			SessionStatus status) {
		
		if (result.hasErrors()) {
			model.put("titulo", "Form of post");
			return "form";
		}
		
		String mensajeFlash = (post.getId()==null)?"Post create with success!" : "Post edit with success!";
		
		postService.save(post);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:posts";
	}
	
	@DeleteMapping(value="/posts/{id}")
	public String  delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		if (id > 0) {
			Post post = postService.findOne(id);
			if(post!=null) {
			
			flash.addFlashAttribute("success", "The Post: "+ post.getTitulo()+ " deleted with success!");
			postService.delete(id);
			

			}else {
				flash.addFlashAttribute("error", "The post solicited delete not exist, try with other Id");
				
			}
		}
		return "redirect:/posts/";
	}
	
	@GetMapping(value="/edicion")
	public String formEdition(Map<String, Object> model,RedirectAttributes flash) {
		
		model.put("titulo", "Enter the Id for search and edit!");
		
		return "edicion";
		
		
	}
	
	@GetMapping(value = "/formI/")
	public String redirectPost(@RequestParam("buscarPorId") Integer id) {
		
		return "redirect:/form/"+id;
		
	}
	
	
}
