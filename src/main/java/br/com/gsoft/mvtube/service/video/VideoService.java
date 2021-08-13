package br.com.gsoft.mvtube.service.video;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gsoft.mvtube.controller.video.dto.CreateOrUpdateVideoDto;
import br.com.gsoft.mvtube.controller.video.dto.VideoDto;
import br.com.gsoft.mvtube.exceptions.BusinessLogicException;
import br.com.gsoft.mvtube.exceptions.ModelNotFoundException;
import br.com.gsoft.mvtube.model.category.Category;
import br.com.gsoft.mvtube.model.video.Video;
import br.com.gsoft.mvtube.repository.VideoRepository;
import br.com.gsoft.mvtube.service.category.CategoryService;

@Service
public class VideoService {

	private static final long UNKNOW_CATEGORY = 1L;
	
	@Autowired
	private VideoRepository repository;
	
	@Autowired
	private CategoryService categoryService;
	
	public Video create(CreateOrUpdateVideoDto videoDto) throws BusinessLogicException {
		if(videoDto.getCategoryId() == null) {
			videoDto.setCategoryId(UNKNOW_CATEGORY);
		}
		
		try {
			Category category = categoryService.findOne(videoDto.getCategoryId());
			Video video = videoDto.converter();
			video.setCategory(category);
			return repository.save(video);
			
		} catch (ModelNotFoundException e) {
			throw new BusinessLogicException("Invalid category provided");
		}
	}
	
	@Transactional
	public VideoDto update(Long id, CreateOrUpdateVideoDto videoForm){
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
