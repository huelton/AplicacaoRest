package br.com.springboot.app.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.app.exception.ToDoException;
import br.com.springboot.app.model.Response;
import br.com.springboot.app.model.ToDo;
import br.com.springboot.app.service.ToDoService;
import br.com.springboot.app.util.PayloadValidator;

@RestController
public class ToDoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);

	@Autowired
	private ToDoService toDoService;
	
	@GetMapping(value="/todo")
	public ResponseEntity<List<ToDo>> getAllToDo(){
    	logger.info("Returning all the ToDo´s");
		return new ResponseEntity<List<ToDo>>(toDoService.getAllToDo(), HttpStatus.OK);
	}
	
    @GetMapping(value = "/todo/{id}")
	public ResponseEntity<Optional<ToDo>> getToDoById(@PathVariable("id") long id) throws ToDoException{
    	logger.info("ToDo id to return " + id);
    	Optional<ToDo> toDo = toDoService.getToDoById(id);
    	if (toDo == null || !toDo.isPresent()){
            throw new ToDoException("ToDo doesn´t exist");
    	}
    	return ResponseEntity.ok(toDo);
	}

    @DeleteMapping(value = "/todo/{id}")
	public ResponseEntity<Response> removeToDoById(@PathVariable("id") long id) throws ToDoException{
    	logger.info("ToDo id to remove " + id);
    	Optional<ToDo> toDo = toDoService.getToDoById(id);
    	if (toDo == null || !toDo.isPresent()){
            throw new ToDoException("ToDo to delete doesn´t exist");
    	}
		toDoService.removeToDo(toDo);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "ToDo has been deleted"), HttpStatus.OK);
	}
    
    @PostMapping(value = "/todo")
   	public ResponseEntity<ToDo> saveToDo(@RequestBody ToDo payload) throws ToDoException{
    	logger.info("Payload to save " + payload);
    	if (!PayloadValidator.validateCreatePayload(payload)){
            throw new ToDoException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<ToDo>(toDoService.saveToDo(payload), HttpStatus.OK);
   	}
    
    @PutMapping(value = "/todo")
   	public ResponseEntity<ToDo>  updateToDo(@RequestBody ToDo payload) throws ToDoException{
    	logger.info("Payload to update " + payload);
    	Optional<ToDo> toDo = toDoService.getToDoById(payload.getId());
    	if (toDo == null || !toDo.isPresent()){
            throw new ToDoException("ToDo to update doesn´t exist");
    	}
		return new ResponseEntity<ToDo>(toDoService.saveToDo(payload), HttpStatus.OK);
   	}
	
}
