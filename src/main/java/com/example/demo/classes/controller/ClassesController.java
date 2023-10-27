package com.example.demo.classes.controller;

import com.example.demo.classes.dto.ClassesDTO;
import com.example.demo.classes.model.Classes;
import com.example.demo.classes.service.ClassesService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
@Slf4j
public class ClassesController {
    private ClassesService classesService;
    @Autowired
    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Classes> getClassById(@PathVariable("id") Long id) {
        log.info("get /classes/{" + id + "}");
        return ResponseEntity.ok(classesService.getClassById(id));
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Classes> addNewClass(@Valid @RequestBody ClassesDTO classesDTO) {
        log.info("post /classes");
        return ResponseEntity.ok(classesService.addClass(classesDTO));
    }

}
