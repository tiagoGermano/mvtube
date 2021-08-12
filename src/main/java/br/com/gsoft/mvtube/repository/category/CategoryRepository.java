package br.com.gsoft.mvtube.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gsoft.mvtube.model.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
