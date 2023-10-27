package com.example.demo.studio.service;

import com.example.demo.studio.model.LegalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegalInfoService {
    private StudioService studioService;
    @Autowired
    public LegalInfoService(
            StudioService studioService
    ) {
        this.studioService = studioService;
    }

    public LegalInfo getInfoByStudioName(String studioName) {
        return studioService.getStudioByName(studioName).getLegalInfo();
    }
}
