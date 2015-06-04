package ru.csu.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.csu.example.model.Todo;
import ru.csu.example.model.TodoRepository;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    AuthService authService;

    public ArrayList<Todo> getTodos() {
        String name = authService.getUsername();

        Iterable<Todo> todos = todoRepository.findByUser(name);

        // convert to list
        ArrayList<Todo> list = new ArrayList<>();
        for (Todo todo : todos) {
            list.add(todo);
        }

        return list;
    }

    public void addTodo(String desc) {
        // create new record
        Todo todo = new Todo();
        todo.setDate(new Date());
        todo.setDesc(desc);
        todo.setDone(false);
        todo.setUser(authService.getUsername());

        //save to db
        todoRepository.save(todo);
    }

}
