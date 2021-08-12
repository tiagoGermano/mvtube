package br.com.gsoft.mvtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gsoft.mvtube.model.video.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

}
