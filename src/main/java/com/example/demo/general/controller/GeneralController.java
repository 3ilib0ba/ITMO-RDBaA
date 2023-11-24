package com.example.demo.general.controller;

import com.example.demo.general.dto.ApplicationInfoDTO;
import com.example.demo.general.model.Ping;
import com.example.demo.general.service.PingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gen")
@Slf4j
public class GeneralController {
    private PingService pingService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.application.author_name}")
    private String authorName;
    @Autowired
    public GeneralController(
            PingService pingService
    ) {
        this.pingService = pingService;
    }

    @GetMapping(value = "/app-info",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationInfoDTO> getApplicationName() {
        log.info("get /gen/app-info");
        return ResponseEntity.ok(
                new ApplicationInfoDTO(
                        applicationName,
                        authorName
                )
        );
    }

    @PostMapping(value = "/pings",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ping> makePing() {
        log.info("post /gen/pings");
        return ResponseEntity.ok(pingService.addNewPingFromNow());
    }

    @GetMapping(value = "/pings",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ping>> getAllPings() {
        log.info("get /gen/pings");
        return ResponseEntity.ok(pingService.getAllPings());
    }

}
