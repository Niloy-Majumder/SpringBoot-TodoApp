package com.niloy.FirstSpringBootWeb.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.niloy.FirstSpringBootWeb.Service.TodoService;
import com.niloy.FirstSpringBootWeb.model.Todo;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	@RequestMapping(value="/list-todos", method=RequestMethod.GET)
	public String showTodo(ModelMap model) {
		String name= getLoggedInUserName(model);
		model.put("todos",service.retrieveTodos(name));
		model.put("navClassTodo", "active");
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		return (String) model.get("name");
	}
	
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String showaddTodoPage(ModelMap model) {
		model.addAttribute("todo",new Todo(0,getLoggedInUserName(model) , "Default Description", new Date(), false));
		return "add-todo";
	}
	
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addTodo(@Valid Todo todo,BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		service.addTodo("Niloy", todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/delete-todo", method=RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
		Todo todo=service.retrieveTodo(id);
		model.addAttribute("todo", todo);
		return "add-todo";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo,BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		todo.setUser(getLoggedInUserName(model));
		service.updateTodo(todo);
		return "redirect:/list-todos";
	}
}
