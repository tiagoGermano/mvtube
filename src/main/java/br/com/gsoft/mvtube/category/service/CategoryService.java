package br.com.gsoft.mvtube.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gsoft.mvtube.category.model.Category;
import br.com.gsoft.mvtube.category.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	 
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	
}
