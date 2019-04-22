package br.com.springboot.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.app.model.ToDo;
import br.com.springboot.app.repository.ToDoRepository;

@Service
public class ToDoServiceImpl implements ToDoService{

	@Autowired
	private ToDoRepository toDoRepository;
	
	@Override
	public List<ToDo> getAllToDo() {
		return toDoRepository.findAll();
	}

	@Override
	public Optional<ToDo> getToDoById(long id) {
		return toDoRepository.findById(id);
	}

	@Override
	public ToDo saveToDo(ToDo todo) {
		return toDoRepository.save(todo);
	}

	@Override
	public void removeToDo(ToDo todo) {
		toDoRepository.delete(todo);
	}

	@Override
	public void removeToDo(Optional<ToDo> toDo) {
		// TODO Auto-generated method stub
		
	}
}