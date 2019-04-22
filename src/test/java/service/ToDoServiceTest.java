package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.springboot.app.model.ToDo;
import br.com.springboot.app.repository.ToDoRepository;
import br.com.springboot.app.service.ToDoServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ToDoServiceTest {

	@Mock
	private ToDoRepository toDoRepository;
	
	@InjectMocks
	private ToDoServiceImpl toDoServiceImpl;
	
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllToDo() {
		List<ToDo> toDoList = new ArrayList<ToDo>();
		toDoList.add(new ToDo(1,"ToDo Simples 1", true));
		toDoList.add(new ToDo(2,"ToDo Simples 2", false));
		toDoList.add(new ToDo(3,"ToDo Simples 3", true));

		when(toDoRepository.findAll()).thenReturn(toDoList);
		
		List<ToDo> result = toDoServiceImpl.getAllToDo();
		
		assertEquals(3, result.size());
		
	}
	
	@Test
	public void testGetToDoById() {
		Optional<ToDo> toDo = Optional.of(new ToDo(1,"Todo Sample 1", true));
		when(toDoRepository.findById(1L)).thenReturn(toDo);
		Optional<ToDo> result = toDoServiceImpl.getToDoById(1L);
		
		assertEquals(1, result.get().getId());
		assertEquals("Todo Sample 1", result.get().getText());
		assertEquals(true, result.get().isCompleted());
	}
	
	
	@Test
	public void testSaveToDo() {
		ToDo toDo = new ToDo(8, "Todo Sample 8", true);
		when(toDoRepository.save(toDo)).thenReturn(toDo);		
		ToDo result = toDoServiceImpl.saveToDo(toDo);
		
		assertEquals(8, result.getId());
		assertEquals("Todo Sample 8", result.getText());
		assertEquals(true, result.isCompleted());
	}
	
	@Test
	public void testRemoveToDo() {
		ToDo toDo = new ToDo(8, "Todo Sample 8", true);
		toDoServiceImpl.removeToDo(toDo);
		
		verify(toDoRepository, times(1)).delete(toDo);		
	}
	
}
