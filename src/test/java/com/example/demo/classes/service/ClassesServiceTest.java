package com.example.demo.classes.service;

import com.example.demo.classes.dto.ClassesDTO;
import com.example.demo.classes.exceptions.ClassesNotFoundException;
import com.example.demo.classes.exceptions.ClassesStartTimeGreaterOrEqualsClassesEndTimeException;
import com.example.demo.classes.model.Classes;
import com.example.demo.classes.repository.ClassRepository;
import com.example.demo.studio.service.PositionService;
import com.example.demo.utils.UtilsObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.Optional;

import static com.example.demo.utils.UtilsObjects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClassesServiceTest {
    @Mock
    private ClassRepository classRepository;
    @Mock
    private PositionService positionService;
    @InjectMocks
    private ClassesService classesService;

    private final UtilsObjects utilsObjects = new UtilsObjects();

    @Test
    void getClassesByIdOk() {
        Classes classes = new Classes();
        classes.setId(CLASSES_ID);
        when(classRepository.findById(CLASSES_ID)).thenReturn(Optional.of(classes));
        Classes result = classesService.getClassById(CLASSES_ID);

        assertEquals(classes, result);
        verify(classRepository, times(1)).findById(eq(CLASSES_ID));
    }

    @Test
    void getClassesByIdClassNotFound() {
        assertThrows(ClassesNotFoundException.class, () -> classesService.getClassById(CLASSES_ID));
    }

    @Test
    void addClassesOk() {
        Classes classes = utilsObjects.CLASSES;

        ClassesDTO classesDTO = new ClassesDTO(
                classes.getAmount(),
                classes.getPosition().getId()
        );
        classesDTO.setName(classes.getName());
        classesDTO.setDate(classes.getDate());
        classesDTO.setStart(classes.getStartTime());
        classesDTO.setEnd(classes.getEndTime());

        when(positionService.getPositionById(POSITION_ID)).thenReturn(utilsObjects.POSITION);
        when(classRepository.save(any())).thenReturn(classes);

        assertEquals(classes, classesService.addClass(classesDTO));
        verify(classRepository, times(1)).save(any());
    }

    @Test
    void addClassesClassesStartTimeGreaterOrEqualsClassesEndTime() {
        Classes classes = utilsObjects.CLASSES;
        classes.setStartTime(Time.valueOf("12:00:00"));
        classes.setEndTime(Time.valueOf("10:00:00"));

        ClassesDTO classesDTO = new ClassesDTO(
                classes.getAmount(),
                classes.getPosition().getId()
        );
        classesDTO.setName(classes.getName());
        classesDTO.setDate(classes.getDate());
        classesDTO.setStart(classes.getStartTime());
        classesDTO.setEnd(classes.getEndTime());

        assertThrows(ClassesStartTimeGreaterOrEqualsClassesEndTimeException.class, () -> classesService.addClass(classesDTO));
    }
}
