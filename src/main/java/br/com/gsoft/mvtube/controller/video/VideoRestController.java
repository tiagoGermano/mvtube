package br.com.gsoft.mvtube.controller.video;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gsoft.mvtube.controller.video.dto.CreateOrUpdateVideoDto;
import br.com.gsoft.mvtube.controller.video.dto.VideoDto;
import br.com.gsoft.mvtube.exceptions.BusinessLogicException;
import br.com.gsoft.mvtube.model.video.Video;
import br.com.gsoft.mvtube.repository.VideoRepository;
import br.com.gsoft.mvtube.service.video.VideoService;

@RestController
@RequestMapping("videos")
public class VideoRestController {

	@Autowired
	private VideoRepository repository;
	
	@Autowired
	private VideoService service;
	
	@GetMapping
	public List<VideoDto> findAll() {
		List<Video> videos = repository.findAll();
		return VideoDto.converter(videos);
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
	public ResponseEntity<VideoDto> update(@PathVariable Long id, @RequestBody @Valid CreateOrUpdateVideoDto videoForm) {
		VideoDto videoUpdated = service.update(id, videoForm);
		
		if(videoUpdated != null) {
			return ResponseEntity.ok(videoUpdated);			
		}
		
		return ResponseEntity.notFound().build();			
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
	
}
