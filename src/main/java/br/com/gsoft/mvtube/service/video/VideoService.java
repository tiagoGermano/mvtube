package br.com.gsoft.mvtube.service.video;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Video> findByCategoryId(Long categoryId, Pageable pagination) throws BusinessLogicException {
		try {
			Category category = categoryService.findOne(categoryId);
			return repository.findByCategoryId(category.getId(), pagination);
			
		} catch (ModelNotFoundException e) {
			throw new BusinessLogicException("Category not found");
		}
	}
	
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
			throw new BusinessLogicException("Category not found");
		}
	}
	
	@Transactional
	public VideoDto update(Long id, CreateOrUpdateVideoDto videoForm) throws BusinessLogicException, ModelNotFoundException{
		if(videoForm.getCategoryId() == null) {
			videoForm.setCategoryId(UNKNOW_CATEGORY);
		}
		
		Optional<Video> optionalVideo = repository.findById(id);
		
		if (!optionalVideo.isPresent()) {
			throw new ModelNotFoundException("video not found");
		}
		
		try {
			Category category = categoryService.findOne(videoForm.getCategoryId());
			Video video = optionalVideo.get();
			video.setTitle(videoForm.getTitle());
			video.setDescription(videoForm.getDescription());
			video.setUrl(videoForm.getUrl());
			video.setCategory(category);
			return new VideoDto(video);
			
		} catch (ModelNotFoundException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}
	
	@Transactional
	public void delete(Long id) {
		Optional<Video> optionalVideo = repository.findById(id);
		
		if(optionalVideo.isPresent()) {
			repository.delete(optionalVideo.get());
		}
	}
	
	public Page<Video> findByTitle(String title, Pageable pagination) {
		return repository.findByTitleContainsIgnoreCase(title, pagination);
	}
}
