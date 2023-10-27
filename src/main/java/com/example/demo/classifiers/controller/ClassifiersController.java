package com.example.demo.classifiers.controller;

import com.example.demo.classifiers.dto.ClassifierDTO;
import com.example.demo.classifiers.model.Classifier;
import com.example.demo.classifiers.service.ClassifiersService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classifiers")
@Slf4j
public class ClassifiersController {
    private ClassifiersService classifiersService;
    @Autowired
    public ClassifiersController(
            ClassifiersService classifiersService
    ) {
        this.classifiersService = classifiersService;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Classifier> getClassifierById(@PathVariable("id") Long id) {
        log.info("get /classifiers/{" + id + "}");
        return ResponseEntity.ok(classifiersService.getClassifierById(id));
    }

    @GetMapping(value = "/name/{name}/value/{value}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Classifier> getClassifierByNameAndValue(
            @PathVariable("name") String name, @PathVariable("value") String value) {
        log.info("get /classifiers/name/{" + name + "}/value{" + value + "}");
        return ResponseEntity.ok(classifiersService.getClassifierByNameAndValue(name, value));
    }

    @GetMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Classifier>> getAllClassifiers() {
        log.info("get /classifiers");
        return ResponseEntity.ok(classifiersService.getAllClassifiers());
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Classifier> addNewClassifier(@Valid @RequestBody ClassifierDTO classifierDTO) {
        log.info("post /classifiers");
        return ResponseEntity.ok(classifiersService.addNewClassifier(classifierDTO));
    }


}
