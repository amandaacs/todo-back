package com.portfolio.todoback.service;

import com.portfolio.todoback.model.Task;
import com.portfolio.todoback.model.User;
import com.portfolio.todoback.repositories.TaskRepository;
import com.portfolio.todoback.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task createTask(Task task) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasksByCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return taskRepository.findByUser(user);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());
        return taskRepository.save(task);
    }

    public Task markTaskAsCompleted(Long id, boolean completed) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setCompleted(completed);
        return taskRepository.save(task);
    }

}
