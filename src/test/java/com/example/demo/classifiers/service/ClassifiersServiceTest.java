package com.example.demo.classifiers.service;

import com.example.demo.booking.model.Booking;
import com.example.demo.classes.exceptions.ClassesNotFoundException;
import com.example.demo.classes.model.Classes;
import com.example.demo.classifiers.dto.ClassifierDTO;
import com.example.demo.classifiers.exceptions.ClassifierAlreadyExistsException;
import com.example.demo.classifiers.exceptions.ClassifierNotFoundException;
import com.example.demo.classifiers.model.Classifier;
import com.example.demo.classifiers.repository.ClassifierRepository;
import com.example.demo.utils.UtilsObjects;
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
public class ClassifiersServiceTest {
    @Mock
    private ClassifierRepository classifierRepository;
    @InjectMocks
    private ClassifiersService classifiersService;
    private final Long ID = 1L;
    private final String NAME = "CLASSIFIER_NAME";
    private final String VALUE = "CLASSIFIER_VALUE";

    @Test
    void getClassifierByIdOk() {
        Classifier classifier = new Classifier();
        classifier.setId(ID);
        when(classifierRepository.findById(ID)).thenReturn(Optional.of(classifier));
        Classifier result = classifiersService.getClassifierById(ID);

        assertEquals(classifier, result);
        verify(classifierRepository, times(1)).findById(eq(ID));
    }

    @Test
    void getClassifierByIdClassifierNotFound() {
        assertThrows(ClassifierNotFoundException.class, () -> classifiersService.getClassifierById(ID));
    }

    @Test
    void getClassifierByNameAndValueOk() {
        Classifier classifier = new Classifier();
        classifier.setName(NAME);
        classifier.setValue(VALUE);

        when(classifierRepository.findByNameIgnoreCaseAndValueIgnoreCase(NAME, VALUE)).thenReturn(classifier);
        Classifier result = classifiersService.getClassifierByNameAndValue(NAME, VALUE);

        assertEquals(classifier, result);
        verify(classifierRepository, times(1)).findByNameIgnoreCaseAndValueIgnoreCase(eq(NAME), eq(VALUE));
    }

    @Test
    void getClassifierByNameAndValueClassifierNotFound() {
        assertThrows(ClassifierNotFoundException.class, () -> classifiersService.getClassifierByNameAndValue(NAME, VALUE));
    }

    @Test
    void getAllClassifiers() {
        Classifier classifier1 = new Classifier();
        classifier1.setId(ID);

        Classifier classifier2 = new Classifier();
        classifier2.setId(ID+1);

        when(classifierRepository.findAll()).thenReturn(List.of(classifier1, classifier2));

        List<Classifier> result = classifiersService.getAllClassifiers();
        assertEquals(List.of(classifier1, classifier2), result);
        verify(classifierRepository, times(1)).findAll();
    }

    @Test
    void addNewClassifierOk() {
        Classifier classifier = new Classifier(
                ID,
                NAME,
                VALUE
        );
        ClassifierDTO classifierDTO = new ClassifierDTO();
        classifierDTO.setName(NAME);
        classifierDTO.setValue(VALUE);

        when(classifierRepository.save(any())).thenReturn(classifier);
        assertEquals(classifier, classifiersService.addNewClassifier(classifierDTO));
        verify(classifierRepository, times(1)).save(any());
    }

    @Test
    void addNewClassifierClassifierAlreadyExists() {
        Classifier classifier = new Classifier(
                ID,
                NAME,
                VALUE
        );
        ClassifierDTO classifierDTO = new ClassifierDTO();
        classifierDTO.setName(NAME);
        classifierDTO.setValue(VALUE);

        when(classifierRepository.findByNameIgnoreCaseAndValueIgnoreCase(NAME, VALUE)).thenReturn(classifier);
        assertThrows(ClassifierAlreadyExistsException.class, () -> classifiersService.addNewClassifier(classifierDTO));
        verify(classifierRepository, times(1)).findByNameIgnoreCaseAndValueIgnoreCase(eq(NAME), eq(VALUE));
    }
}
