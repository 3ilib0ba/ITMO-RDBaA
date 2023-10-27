package com.example.demo.instructor.controller;

import com.example.demo.instructor.dto.InstructorDTO;
import com.example.demo.instructor.model.Instructor;
import com.example.demo.instructor.service.InstructorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructors")
@Slf4j
public class InstructorController {
    private InstructorService instructorService;
    @Autowired
    public InstructorController(
            InstructorService instructorService
    ) {
        this.instructorService = instructorService;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Instructor> getInstructorById(@PathVariable("id") Long id) {
        log.info("get /instructors/{" + id + "}");
        return ResponseEntity.ok(instructorService.getInstructorById(id));
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Instructor> addNewInstructor(@Valid @RequestBody InstructorDTO instructorDTO) {
        log.info("post /instructors");
        return ResponseEntity.ok(instructorService.addInstructor(instructorDTO));
    }

}
