package br.com.gsoft.mvtube.controller.category;

import java.net.URI;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gsoft.mvtube.controller.category.dto.CategoryDto;
import br.com.gsoft.mvtube.controller.category.dto.CreateUpdateCategoryDto;
import br.com.gsoft.mvtube.controller.video.dto.VideoDto;
import br.com.gsoft.mvtube.exceptions.BusinessLogicException;
import br.com.gsoft.mvtube.exceptions.ModelNotFoundException;
import br.com.gsoft.mvtube.model.category.Category;
import br.com.gsoft.mvtube.model.video.Video;
import br.com.gsoft.mvtube.service.category.CategoryService;
import br.com.gsoft.mvtube.service.video.VideoService;

@RestController
@RequestMapping("/api/categorias")
public class CategoryRestController {
	
	@Autowired
	private CategoryService service;
	
	@Autowired
	private VideoService videoService;
	
	@GetMapping
	public ResponseEntity<Page<CategoryDto>> findAll(@PageableDefault(size = 10, sort = "title", direction = Direction.ASC) Pageable pagination) {
		Page<Category> categories = service.findAll(pagination);
		return ResponseEntity.ok(CategoryDto.converter(categories));
	}
	
	@PostMapping
	public ResponseEntity<CategoryDto> save(@Valid @RequestBody CreateUpdateCategoryDto newCategoryDto, UriComponentsBuilder uriBuilder) {
		Category category = service.save(newCategoryDto);
		URI location = uriBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(location).body(new CategoryDto(category));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> findOne(@PathVariable Long id) {
		try {
			Category category = service.findOne(id);
			return ResponseEntity.ok(new CategoryDto(category));			
		} catch (ModelNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryDto> delete(@PathVariable Long id) {
		try {
			service.delete(id);
			return ResponseEntity.ok().build();
			
		} catch (ModelNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> update(@PathVariable Long id, @Valid @RequestBody CreateUpdateCategoryDto categoryDto) {
		try {
			Category updatedCatogory = service.update(id, categoryDto);
			return ResponseEntity.ok(new CategoryDto(updatedCatogory));
			
		} catch (ModelNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}/videos")
	public ResponseEntity<Page<VideoDto>> findVideosByCategory(@PathVariable(name = "id") Long categoryId,
			@PageableDefault(size = 5, sort = "title", direction = Direction.ASC) Pageable pagination) throws BusinessLogicException {
		Page<Video> videos = videoService.findByCategoryId(categoryId, pagination);
		return ResponseEntity.ok(VideoDto.converter(videos));
	}
 
}
