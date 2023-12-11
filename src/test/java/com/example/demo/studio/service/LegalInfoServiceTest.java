package com.example.demo.studio.service;

import com.example.demo.studio.model.LegalInfo;
import com.example.demo.studio.model.Studio;
import com.example.demo.utils.UtilsObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.demo.utils.UtilsObjects.CLASSES_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LegalInfoServiceTest {
    @Mock
    private StudioService studioService;
    @InjectMocks
    private LegalInfoService legalInfoService;

    private final UtilsObjects utilsObjects = new UtilsObjects();
    @Test
    void getInfoByStudioNameOk() {
        Studio studio = utilsObjects.STUDIO;
        when(studioService.getStudioByName(studio.getName())).thenReturn(studio);
        LegalInfo result = legalInfoService.getInfoByStudioName(studio.getName());

        assertEquals(studio.getLegalInfo(), result);
    }
}
