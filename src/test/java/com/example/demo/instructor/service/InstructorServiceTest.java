package com.example.demo.instructor.service;

import com.example.demo.booking.exceptions.BookingNotFoundException;
import com.example.demo.booking.model.Booking;
import com.example.demo.common.enums.Gender;
import com.example.demo.common.exceptions.MailIsAlreadyExistException;
import com.example.demo.common.exceptions.PhoneIsAlreadyExistException;
import com.example.demo.instructor.dto.InstructorDTO;
import com.example.demo.instructor.exceptions.InstructorNotFoundException;
import com.example.demo.instructor.model.Instructor;
import com.example.demo.instructor.repository.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {
    @Mock
    private InstructorRepository instructorRepository;
    @InjectMocks
    private InstructorService instructorService;

    private final Long ID = 1L;
    private final String NAME = "NAME";
    private final String MAIL = "instructor@mail.ru";
    private final String PHONE = "+79527977524";
    private final String GENDER = Gender.MALE.name();

    @Test
    void getInstructorByIdOk() {
        Instructor instructor = new Instructor(
                ID,
                NAME,
                MAIL,
                PHONE,
                GENDER,
                List.of(),
                List.of()
        );

        when(instructorRepository.findById(ID)).thenReturn(Optional.of(instructor));
        Instructor result = instructorService.getInstructorById(ID);

        assertEquals(instructor, result);
        verify(instructorRepository, times(1)).findById(eq(ID));
    }

    @Test
    void getInstructorByIdInstructorNotFound() {
        assertThrows(InstructorNotFoundException.class, () -> instructorService.getInstructorById(ID));
    }

    @Test
    void addInstructorOk() {
        Instructor instructor = new Instructor(
                ID,
                NAME,
                MAIL,
                PHONE,
                GENDER,
                List.of(),
                List.of()
        );

        InstructorDTO instructorDTO = new InstructorDTO(
               NAME,
               MAIL,
               PHONE
        );
        instructorDTO.setGender(GENDER);

        when(instructorRepository.findByMail(MAIL)).thenReturn(null);
        when(instructorRepository.findByPhone(PHONE)).thenReturn(null);
        when(instructorRepository.save(any())).thenReturn(instructor);

        assertEquals(instructor, instructorService.addInstructor(instructorDTO));
        verify(instructorRepository, times(1)).findByMail(anyString());
        verify(instructorRepository, times(1)).findByPhone(anyString());
        verify(instructorRepository, times(1)).save(any());
    }

    @Test
    void addInstructorMailIsAlreadyExist() {
        Instructor instructor = new Instructor(
                ID,
                NAME,
                MAIL,
                PHONE,
                GENDER,
                List.of(),
                List.of()
        );

        InstructorDTO instructorDTO = new InstructorDTO(
                NAME,
                MAIL,
                PHONE
        );
        instructorDTO.setGender(GENDER);

        when(instructorRepository.findByMail(MAIL)).thenReturn(instructor);
        assertThrows(MailIsAlreadyExistException.class, () -> instructorService.addInstructor(instructorDTO));
    }

    @Test
    void addInstructorPhoneIsAlreadyExist() {
        Instructor instructor = new Instructor(
                ID,
                NAME,
                MAIL,
                PHONE,
                GENDER,
                List.of(),
                List.of()
        );

        InstructorDTO instructorDTO = new InstructorDTO(
                NAME,
                MAIL,
                PHONE
        );
        instructorDTO.setGender(GENDER);

        when(instructorRepository.findByMail(MAIL)).thenReturn(null);
        when(instructorRepository.findByPhone(PHONE)).thenReturn(instructor);
        assertThrows(PhoneIsAlreadyExistException.class, () -> instructorService.addInstructor(instructorDTO));
    }
}
