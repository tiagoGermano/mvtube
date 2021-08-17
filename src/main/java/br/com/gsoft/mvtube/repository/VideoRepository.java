package br.com.gsoft.mvtube.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gsoft.mvtube.model.video.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

	Page<Video> findByCategoryId(Long categoryId, Pageable pagination);
	Page<Video> findByTitleContainsIgnoreCase(String title, Pageable pagination);
}
