package me.espacodosautores.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.espacodosautores.login.model.SecUser;

public interface SecUserRepository extends JpaRepository<SecUser, Long> {
	
	SecUser findByUsername(String username);
	
}
