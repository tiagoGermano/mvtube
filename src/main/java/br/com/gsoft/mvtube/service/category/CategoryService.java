package br.com.gsoft.mvtube.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gsoft.mvtube.controller.category.dto.CreateUpdateCategoryDto;
import br.com.gsoft.mvtube.exceptions.ModelNotFoundException;
import br.com.gsoft.mvtube.model.category.Category;
import br.com.gsoft.mvtube.repository.category.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	 
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category save(CreateUpdateCategoryDto categoriaDto) {
		Category category = categoriaDto.converter();
		return repository.save(category);
	}
	
	public Optional<Category> findOne(Long id) {
		return repository.findById(id);
	}
	
	@Transactional
	public Category update(Long id, CreateUpdateCategoryDto categoryDto) throws ModelNotFoundException {
		Optional<Category> optional = repository.findById(id);
		
		if(!optional.isPresent()) {
			throw new ModelNotFoundException("Category with id "+id +" not found");
		}
		
		Category category = optional.get();
		category.setTitle(categoryDto.getTitle());
		category.setColor(categoryDto.getColor());
		
		return category;
	}
	
	
}
