package br.com.gsoft.mvtube.controller.category.dto;

import java.util.List;
import java.util.stream.Collectors;

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

	public static List<CategoryDto> converter(List<Category> categories){
		return categories.stream().map(CategoryDto::new).collect(Collectors.toList());
	}
}
