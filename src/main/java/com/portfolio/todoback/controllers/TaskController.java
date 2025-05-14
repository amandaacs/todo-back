package com.portfolio.todoback.controllers;

import com.portfolio.todoback.dto.TaskResponse;
import com.portfolio.todoback.model.Task;
import com.portfolio.todoback.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody Task task) {
        Task savedTask = taskService.createTask(task);

        TaskResponse taskResponse = new TaskResponse(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(), savedTask.isCompleted());

        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasksByCurrentUser();
        List<TaskResponse> responses = tasks.stream().map(task ->
                new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.isCompleted()
                )
        ).toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        TaskResponse taskResponse =  new TaskResponse(updatedTask.getId(), updatedTask.getTitle(), updatedTask.getDescription(), updatedTask.isCompleted());
        return ResponseEntity.ok(taskResponse);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<TaskResponse> completeTask(@PathVariable Long id) {
        Task updatedTask = taskService.markTaskAsCompleted(id, true);
        return ResponseEntity.ok(new TaskResponse(
                updatedTask.getId(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.isCompleted()
        ));
    }

    @PutMapping("/{id}/undo")
    public ResponseEntity<TaskResponse> undoCompleteTask(@PathVariable Long id) {
        Task updatedTask = taskService.markTaskAsCompleted(id, false);
        return ResponseEntity.ok(new TaskResponse(
                updatedTask.getId(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.isCompleted()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
