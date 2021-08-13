package br.com.gsoft.mvtube.controller.video.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.gsoft.mvtube.model.video.Video;

public class CreateOrUpdateVideoDto {

	@NotNull
	@NotEmpty
	@Size(min = 10, max = 40)
	private String title;

	private String description;

	@NotNull
	@NotEmpty
	@Size(min = 5, max = 100)
	private String url;

	private Long categoryId;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Video converter() {
		return new Video(this.title, this.description, this.url);
	}

}
