package app.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
