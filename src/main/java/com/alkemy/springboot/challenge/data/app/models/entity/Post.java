package com.alkemy.springboot.challenge.data.app.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "posts")
public class Post implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="The field 'title' no be able empty")
	private String titulo;
	@NotBlank(message="The field 'content' no be able empty")
	private String contenido;

	private String imagen;

	
	private String categoria;

	
	@Column(name = "create_at")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime createAt;
	
	@PrePersist
	public void prePersist() {
		createAt =LocalDateTime.now();
	}
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getContenido() {
		return contenido;
	}



	public void setContenido(String contenido) {
		this.contenido = contenido;
	}



	public String getImagen() {
		return imagen;
	}



	public void setImagen(String imagen) {
		this.imagen = imagen;
	}



	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public LocalDateTime getCreateAt() {
		return createAt;
	}



	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	



	private static final long serialVersionUID = 1L;
}
