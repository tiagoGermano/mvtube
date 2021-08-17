package br.com.gsoft.mvtube.service.category;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	 
	public Page<Category> findAll(Pageable pagination) {
		return repository.findAll(pagination);
	}
	
	public Category save(CreateUpdateCategoryDto categoriaDto) {
		Category category = categoriaDto.converter();
		return repository.save(category);
	}
	
	public Category findOne(Long id) throws ModelNotFoundException {
		Category category = findCategory(id);
		return category;
	}
	
	@Transactional
	public Category update(Long id, CreateUpdateCategoryDto categoryDto) throws ModelNotFoundException {
		Category category = findCategory(id);
		category.setTitle(categoryDto.getTitle());
		category.setColor(categoryDto.getColor());
		
		return category;
	}
	
	public void delete(Long id) throws ModelNotFoundException {
		Category category = findCategory(id);
		repository.delete(category);
	}
	
	private Category findCategory(Long id) throws ModelNotFoundException {
		Optional<Category> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new ModelNotFoundException("Category with id "+id +" not found");
	}
	
	
}
