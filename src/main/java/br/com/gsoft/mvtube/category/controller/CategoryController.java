package br.com.gsoft.mvtube.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gsoft.mvtube.category.controller.dto.CategoryDto;
import br.com.gsoft.mvtube.category.model.Category;
import br.com.gsoft.mvtube.category.service.CategoryService;

@RestController
@RequestMapping("/categorias")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> findAll() {
		List<Category> categories = service.findAll();
		return ResponseEntity.ok(CategoryDto.converter(categories));
	}

}
