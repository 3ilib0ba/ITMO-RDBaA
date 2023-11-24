package com.example.demo.instructor.service;

import com.example.demo.common.exceptions.MailIsAlreadyExistException;
import com.example.demo.common.exceptions.PhoneIsAlreadyExistException;
import com.example.demo.instructor.dto.InstructorDTO;
import com.example.demo.instructor.exceptions.InstructorNotFoundException;
import com.example.demo.instructor.model.Instructor;
import com.example.demo.instructor.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private InstructorRepository instructorRepository;
    @Autowired
    public InstructorService(
            InstructorRepository instructorRepository
    ) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).orElseThrow(() -> new InstructorNotFoundException(id));
    }

    public Instructor addInstructor(InstructorDTO instructorDTO) {
        Instructor instructorMail = instructorRepository.findByMail(instructorDTO.getMail());
        if (instructorMail != null)
            throw new MailIsAlreadyExistException(instructorDTO.getMail());
        Instructor instructorPhone = instructorRepository.findByPhone(instructorDTO.getPhone());
        if (instructorPhone != null)
            throw new PhoneIsAlreadyExistException(instructorDTO.getPhone());

        Instructor instructor = new Instructor(
                null,
                instructorDTO.getName(),
                instructorDTO.getMail(),
                instructorDTO.getPhone(),
                instructorDTO.getGender(),
                List.of(),
                List.of()
        );
        return instructorRepository.save(instructor);
    }




}
