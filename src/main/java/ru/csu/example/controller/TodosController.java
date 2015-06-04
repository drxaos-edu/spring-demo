package ru.csu.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.csu.example.model.Todo;
import ru.csu.example.service.AuthService;
import ru.csu.example.service.TodoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TodosController {

    @Autowired
    TodoService todoService;

    @Autowired
    AuthService authService;

    @RequestMapping({"/list", "/"})
    public ModelAndView list() {
        ArrayList<Todo> list = todoService.getTodos();
        String name = authService.getUsername();

        Map<String, Object> m = new HashMap<>();
        m.put("now", new Date().toString());
        m.put("todos", list);
        m.put("username", name);
        return new ModelAndView("test", m);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(String desc) {
        todoService.addTodo(desc);
        return "redirect:/";
    }

}
