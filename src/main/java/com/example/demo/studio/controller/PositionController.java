package com.example.demo.studio.controller;

import com.example.demo.studio.dto.PositionDTO;
import com.example.demo.studio.model.Position;
import com.example.demo.studio.service.PositionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
@Slf4j
public class PositionController {
    private PositionService positionService;
    @Autowired
    public PositionController(
            PositionService positionService
    ) {
        this.positionService = positionService;
    }

    @GetMapping(value = "/address/{address}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Position> getPositionByAddress(@PathVariable("address") String address) {
        log.info("get /positions/address/{" + address + "}");
        return ResponseEntity.ok(positionService.getPositionByAddress(address));
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Position> getPositionById(@PathVariable("id") Long id) {
        log.info("get /positions/{" + id + "}");
        return ResponseEntity.ok(positionService.getPositionById(id));
    }

    @GetMapping(value = "/studio/id/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Position>> getPositionsByStudioId(@PathVariable("id") Long id) {
        log.info("get /positions/studio/id/{" + id + "}");
        return ResponseEntity.ok(positionService.getAllPositionByStudioId(id));
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Position> addPositionToStudioById(@Valid @RequestBody PositionDTO positionDTO) {
        log.info("post /positions");
        return ResponseEntity.ok(positionService.addPositionToStudio(positionDTO));
    }

}
