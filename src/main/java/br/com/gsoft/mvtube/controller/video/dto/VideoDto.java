package br.com.gsoft.mvtube.controller.video.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gsoft.mvtube.model.video.Video;

public class VideoDto {

	private final Long id;
	private final String title;
	private final String description;
	private final String url;
	private final Long categoryId;

	public VideoDto(Video video) {
		super();
		this.id = video.getId();
		this.title = video.getTitle();
		this.description = video.getDescription();
		this.url = video.getUrl();
		this.categoryId = video.getCategory() == null ?  null : video.getCategory().getId();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}
	
	public static List<VideoDto> converter(List<Video> videos) {
		return videos.stream().map(VideoDto::new).collect(Collectors.toList());
	}

}
