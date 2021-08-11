package br.com.gsoft.mvtube.video.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.gsoft.mvtube.category.model.Category;

@Entity
public class Video {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String title;

	@NotNull
	private String description;

	@NotNull
	private String url;
	
	@ManyToOne
	private Category category;

	public Video() {
		super();
	}
	
	public Video(@NotNull String title, @NotNull String description, @NotNull String url) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
