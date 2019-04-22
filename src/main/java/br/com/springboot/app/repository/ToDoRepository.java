package br.com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.app.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long>{

}
