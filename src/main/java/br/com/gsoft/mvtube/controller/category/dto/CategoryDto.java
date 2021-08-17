package br.com.gsoft.mvtube.controller.category.dto;

import org.springframework.data.domain.Page;

import br.com.gsoft.mvtube.model.category.Category;

public class CategoryDto {

	private final Long id;

	private final String title;

	private final String color;

	public CategoryDto(Category category) {
		super();
		this.id = category.getId();
		this.title = category.getTitle();
		this.color = category.getColor();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getColor() {
		return color;
	}

	public static Page<CategoryDto> converter(Page<Category> categories){
		return categories.map(CategoryDto::new);
	}
}
