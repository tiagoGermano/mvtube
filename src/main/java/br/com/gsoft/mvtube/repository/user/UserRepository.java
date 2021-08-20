package br.com.gsoft.mvtube.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gsoft.mvtube.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String userName);
	
}
