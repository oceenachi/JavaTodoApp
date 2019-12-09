package com.kiki.demo.controllers;

import com.kiki.demo.controllers.models.Task;
import com.kiki.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;



    @GetMapping
    public MyResponse<List<Task>> getTask() {
//    public ResponseEntity<List<String>> getTask() {

        //  /api//v1/tasks GET
        //  /api/v1/tasks/{id} GET
        //  /api/v1/tasks/{id} PUT

        //  /api/v1/tasks POST
        //  /api/v1/tasks/{id} DELETE
//        List tasks = new ArrayList<>();
//        tasks.add("Learn Value annotations");
//        tasks.add("fix decadevs chlorine water");
//        tasks.add("build decagons conference app");
//        tasks.add("create an individual project");
//        ResponseEntity<List<String >> response = new ResponseEntity<>(tasks, HttpStatus.OK);


        List <Task>tasks = taskRepository.findAll();
        MyResponse<List<Task>> response = new MyResponse<> (HttpStatus.OK, "Tasks retrieved successfuly", tasks);

        return response;
    }

    @PostMapping
    public MyResponse<Task> createTask(@RequestBody Task task){
//        MyResponse<List<Task>> response = new MyResponse<>();
        Task task1 = taskRepository.save(task);
        return new MyResponse<Task>(HttpStatus.CREATED, "Task created successfuly", task1);
    }


   // /api/v1/tasks/id
    @RequestMapping(value = "{id}")
    public MyResponse<Optional<Task>> findSingleTask(@PathVariable("id") Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        MyResponse<Optional<Task>> response = new MyResponse<>(HttpStatus.OK, "Task retrieved successfully", task);
        return response;
    }

    @DeleteMapping("{id}")
    public String deleteTask(@PathVariable("id") Integer id) {
        taskRepository.deleteById(id);
//        return new MyResponse<Task>(HttpStatus.ACCEPTED, "Task deleted successfuly", task1);
        return "task deleted successfully";
    }


    @PutMapping("{id}")
    public MyResponse<Task> updateTask(@PathVariable("id") Integer id, @RequestBody Task newTaskUpdate ) throws Exception {
        Task newTask = taskRepository.findById(id)
                .orElseThrow(() -> new Exception());
        if(newTaskUpdate.getTitle() != null) {
            newTask.setTitle(newTaskUpdate.getTitle());
        }

        if(newTaskUpdate.getDescription() != null) {
            newTask.setDescription(newTaskUpdate.getDescription());
        }

        if(newTaskUpdate.getStatus() != null) {
            newTask.setStatus(newTaskUpdate.getStatus());
        }

        taskRepository.save(newTask);
         MyResponse<Task> update = new MyResponse<Task>(HttpStatus.OK, "task updated successfully", newTask );

        return update;
    }


}
