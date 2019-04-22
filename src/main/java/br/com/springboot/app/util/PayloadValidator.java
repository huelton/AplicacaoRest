package br.com.springboot.app.util;

import br.com.springboot.app.model.ToDo;

public class PayloadValidator {
	
	public static boolean validateCreatePayload(ToDo toDo){
		if (toDo.getId() > 0){
			return false;
		}
		return true;
	}

}
