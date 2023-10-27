package com.example.demo.classifiers.service;

import com.example.demo.classifiers.dto.ClassifierDTO;
import com.example.demo.classifiers.exceptions.ClassifierAlreadyExistsException;
import com.example.demo.classifiers.exceptions.ClassifierNotFoundException;
import com.example.demo.classifiers.model.Classifier;
import com.example.demo.classifiers.repository.ClassifierRepository;
import com.example.demo.client.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifiersService {
    @Autowired
    private ClassifierRepository classifierRepository;

    public Classifier getClassifierById(Long id) {
        return classifierRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    public Classifier getClassifierByNameAndValue(String name, String value) {
        Classifier result = classifierRepository.findByNameIgnoreCaseAndValueIgnoreCase(
                name,
                value);
        if (result == null)
            throw new ClassifierNotFoundException(
                    name,
                    value);
        return result;
    }

    public List<Classifier> getAllClassifiers() {
        return classifierRepository.findAll();
    }

    public Classifier addNewClassifier(ClassifierDTO classifierDTO) {
        try {
            getClassifierByNameAndValue(classifierDTO.getName(), classifierDTO.getValue());
            throw new ClassifierAlreadyExistsException(classifierDTO.getName(), classifierDTO.getValue());
        }
        catch(ClassifierNotFoundException e) {
            return classifierRepository.save(
                    new Classifier(
                            null,
                            classifierDTO.getName(),
                            classifierDTO.getValue())
            );
        }
    }

}
