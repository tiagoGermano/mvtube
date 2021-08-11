package br.com.gsoft.mvtube.video.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.gsoft.mvtube.video.model.Video;

public class VideoForm {

	@NotNull @NotEmpty @Size(min=10, max=40)
	private String title;

	private String description;

	@NotNull @NotEmpty @Size(min=5)
	private String url;

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
	
	public Video converter() {
		return new Video(this.title, this.description, this.url);
	}

}
