package br.com.gsoft.mvtube.controller.category;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gsoft.mvtube.controller.category.dto.CategoryDto;
import br.com.gsoft.mvtube.controller.category.dto.NewCategoryDto;
import br.com.gsoft.mvtube.model.category.Category;
import br.com.gsoft.mvtube.service.category.CategoryService;

@RestController
@RequestMapping("/categorias")
public class CategoryRestController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> findAll() {
		List<Category> categories = service.findAll();
		return ResponseEntity.ok(CategoryDto.converter(categories));
	}
	
	@PostMapping
	public ResponseEntity<CategoryDto> save(@Valid @RequestBody NewCategoryDto newCategoryDto, UriComponentsBuilder uriBuilder) {
		Category category = service.save(newCategoryDto);
		URI location = uriBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(location).body(new CategoryDto(category));
	}

}
