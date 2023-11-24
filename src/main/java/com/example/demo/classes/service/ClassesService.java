package com.example.demo.classes.service;

import com.example.demo.classes.dto.ClassesDTO;
import com.example.demo.classes.exceptions.ClassesNotFoundException;
import com.example.demo.classes.exceptions.ClassesStartTimeGreaterOrEqualsClassesEndTimeException;
import com.example.demo.classes.model.Classes;
import com.example.demo.classes.repository.ClassRepository;
import com.example.demo.studio.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@Slf4j
public class ClassesService {
    private ClassRepository classRepository;
    private PositionService positionService;
    @Autowired
    public ClassesService(
            ClassRepository classRepository,
            PositionService positionService
    ) {
        this.classRepository = classRepository;
        this.positionService = positionService;
    }

    public Classes getClassById(Long id) {
        return classRepository.findById(id).orElseThrow(() -> new ClassesNotFoundException(id));
    }

    public Classes addClass(ClassesDTO classesDTO) {
        if (classesDTO.getStart().getTime() >= classesDTO.getEnd().getTime()) {
            throw new ClassesStartTimeGreaterOrEqualsClassesEndTimeException(
                    classesDTO.getStart(), classesDTO.getEnd()
            );
        }
        return classRepository.save(
                new Classes(
                        null,
                        classesDTO.getName(),
                        new Date(classesDTO.getDate().getTime()),
                        classesDTO.getStart(),
                        classesDTO.getEnd(),
                        classesDTO.getAmount(),
                        positionService.getPositionById(classesDTO.getPosId()),
                        List.of()
                )
        );
    }

}
