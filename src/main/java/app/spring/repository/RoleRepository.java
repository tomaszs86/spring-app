package app.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.spring.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}