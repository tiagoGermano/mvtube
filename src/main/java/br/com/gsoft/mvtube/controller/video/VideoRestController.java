package br.com.gsoft.mvtube.controller.video;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gsoft.mvtube.controller.video.dto.CreateOrUpdateVideoDto;
import br.com.gsoft.mvtube.controller.video.dto.VideoDto;
import br.com.gsoft.mvtube.exceptions.BusinessLogicException;
import br.com.gsoft.mvtube.exceptions.ModelNotFoundException;
import br.com.gsoft.mvtube.model.video.Video;
import br.com.gsoft.mvtube.repository.VideoRepository;
import br.com.gsoft.mvtube.service.video.VideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoRestController {

	@Autowired
	private VideoRepository repository;
	
	@Autowired
	private VideoService service;
	
	@GetMapping
	public Page<VideoDto> findAll(@RequestParam(required = false) String title, 
			@PageableDefault(size = 5, sort = "title", direction = Direction.ASC) Pageable pagination) {
		
		if(title == null) {
			Page<Video> videos = repository.findAll(pagination);
			return VideoDto.converter(videos);
		} else {		
			Page<Video> videos = service.findByTitle(title, pagination);
			return VideoDto.converter(videos);
		}
	}
	
	@PostMapping
	public ResponseEntity<VideoDto> save(@RequestBody @Valid CreateOrUpdateVideoDto videoDto, UriComponentsBuilder uriBuilder) throws BusinessLogicException {
		
		try {
			Video video = service.create(videoDto);
			URI location = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
			return ResponseEntity.created(location).body(new VideoDto(video));
		} catch (BusinessLogicException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VideoDto> findById(@PathVariable Long id) {
		Optional<Video> optVideo = repository.findById(id);
		
		if(optVideo.isPresent()) {
			Video video = optVideo.get();
			return ResponseEntity.ok(new VideoDto(video));			
		}
		
		return ResponseEntity.notFound().build();			
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VideoDto> update(@PathVariable Long id, @RequestBody @Valid CreateOrUpdateVideoDto videoForm) throws BusinessLogicException {
		
		try {
			VideoDto videoUpdated = service.update(id, videoForm);
			return ResponseEntity.ok(videoUpdated);
			
		} catch (ModelNotFoundException e) {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<VideoDto> remove(@PathVariable Long id) {
		Optional<Video> optVideo = repository.findById(id);
		
		if(optVideo.isPresent()) {
			service.delete(id);		
			return ResponseEntity.ok().build();			
		}
		
		return ResponseEntity.notFound().build();			
	}

	@GetMapping("/free")
	public Page<VideoDto> freeVideos() throws BusinessLogicException {
		Page<Video> freeVideos = service.findFreeVideos();
		
		if (!freeVideos.isEmpty()) {
			return VideoDto.converter(freeVideos);
		}
		
		throw new BusinessLogicException("No free video available =("); 
	}	
}
