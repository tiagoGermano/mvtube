package br.com.gsoft.mvtube.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gsoft.mvtube.model.video.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

	List<Video> findByCategoryId(Long categoryId);
}
