package com.portfolio.todoback.repositories;

import com.portfolio.todoback.model.Task;
import com.portfolio.todoback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

}
