package br.com.gsoft.mvtube.controller.category.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.gsoft.mvtube.model.category.Category;

public class NewCategoryDto {

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 40)
	private String title;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 10)
	private String color;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Category converter() {
		Category category = new Category();
		category.setTitle(this.title);
		category.setColor(this.color);
		
		return category;
	}

}
