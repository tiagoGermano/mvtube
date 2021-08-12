package br.com.gsoft.mvtube.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gsoft.mvtube.controller.category.dto.NewCategoryDto;
import br.com.gsoft.mvtube.model.category.Category;
import br.com.gsoft.mvtube.repository.category.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	 
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category save(NewCategoryDto categoriaDto) {
		Category category = categoriaDto.converter();
		return repository.save(category);
	}
	
	
}
