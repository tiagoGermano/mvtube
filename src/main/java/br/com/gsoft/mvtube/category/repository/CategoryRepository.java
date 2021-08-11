package br.com.gsoft.mvtube.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gsoft.mvtube.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
