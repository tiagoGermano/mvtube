package br.com.gsoft.mvtube.video.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gsoft.mvtube.repository.VideoRepository;
import br.com.gsoft.mvtube.video.controller.dto.VideoDto;
import br.com.gsoft.mvtube.video.controller.form.VideoForm;
import br.com.gsoft.mvtube.video.model.Video;

@Service
public class VideoService {

	@Autowired
	private VideoRepository repository;
	
	@Transactional
	public VideoDto update(Long id, VideoForm videoForm){
		Optional<Video> optionalVideo = repository.findById(id);
		
		if(optionalVideo.isPresent()) {
			Video video = optionalVideo.get();
			video.setTitle(videoForm.getTitle());
			video.setDescription(videoForm.getDescription());
			video.setUrl(videoForm.getUrl());
			
			return new VideoDto(video);
		}
		
		return null;
	}
	
	@Transactional
	public void delete(Long id) {
		Optional<Video> optionalVideo = repository.findById(id);
		
		if(optionalVideo.isPresent()) {
			repository.delete(optionalVideo.get());
		}
		
	}
}
