package app.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.spring.model.Language;


public interface LanguageRepository extends JpaRepository<Language, Long> {

}
