package ru.csu.example.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Iterator;
import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    Page<Todo> findAll(Pageable pageable);

    @Query("select o from Todo o where user = :name")
    Iterable<Todo> findByUser(@Param("name") String username);
}
