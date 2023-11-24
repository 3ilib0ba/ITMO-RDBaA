package com.example.demo.studio.controller;

import com.example.demo.studio.dto.StudioDTO;
import com.example.demo.studio.model.Studio;
import com.example.demo.studio.service.StudioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studios")
@Slf4j
public class StudioController {
    private StudioService studioService;
    @Autowired
    public StudioController(
            StudioService studioService
    ) {
        this.studioService = studioService;
    }

    @GetMapping(value = "/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Studio> getStudioByName(@PathVariable("name") String name) {
        log.info("get /studios/name/{" + name + "}");
        return ResponseEntity.ok(studioService.getStudioByName(name));
    }

    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Studio> addStudioWithLegalInfo(@Valid @RequestBody StudioDTO studioDTO) {
        log.info("post /studios");
        return ResponseEntity.ok(studioService.addStudioAndLegalInfo(studioDTO));
    }

    @GetMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Studio>> getAllStudios() {
        log.info("get /studios");
        return ResponseEntity.ok(studioService.getAllStudios());
    }

}
