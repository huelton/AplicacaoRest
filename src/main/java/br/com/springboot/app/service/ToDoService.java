package br.com.springboot.app.service;

import java.util.List;
import java.util.Optional;

import br.com.springboot.app.model.ToDo;

public interface ToDoService {
	public List<ToDo> getAllToDo();
	public Optional<ToDo> getToDoById(long id);
	public ToDo saveToDo(ToDo todo);
	public void removeToDo(Optional<ToDo> toDo);
	void removeToDo(ToDo todo);
}
