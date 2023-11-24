package com.example.demo.studio.controller;

import com.example.demo.studio.model.LegalInfo;
import com.example.demo.studio.service.LegalInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/legal-infos")
@Slf4j
public class LegalInfoController {
    private LegalInfoService legalInfoService;
    @Autowired
    public LegalInfoController(
            LegalInfoService legalInfoService
    ) {
        this.legalInfoService = legalInfoService;
    }

    @GetMapping(value = "/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LegalInfo> getLegalInfo(@PathVariable("name") String name) {
        log.info("get /legal-infos/name/{" + name + "}");
        return ResponseEntity.ok(legalInfoService.getInfoByStudioName(name));
    }
}
