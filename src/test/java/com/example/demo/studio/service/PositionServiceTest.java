package com.example.demo.studio.service;

import com.example.demo.common.exceptions.MailIsAlreadyExistException;
import com.example.demo.instructor.model.Instructor;
import com.example.demo.studio.dto.PositionDTO;
import com.example.demo.studio.exceptions.PositionsNotFoundException;
import com.example.demo.studio.model.Position;
import com.example.demo.studio.model.Studio;
import com.example.demo.studio.repository.PositionRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PositionServiceTest {
    @Mock
    private PositionRepository positionRepository;
    @Mock
    private StudioService studioService;
    @InjectMocks
    private PositionService positionService;
    private final UtilsObjects utilsObjects = new UtilsObjects();
    @Test
    void getPositionByAddressOk() {
        Position position = utilsObjects.POSITION;
        when(positionRepository.findByAddressIgnoreCase(position.getAddress())).thenReturn(position);
        Position result = positionService.getPositionByAddress(position.getAddress());

        assertEquals(position, result);
        verify(positionRepository, times(1)).findByAddressIgnoreCase(anyString());
    }
    @Test
    void getPositionByAddressPositionsNotFound() {
        Position position = utilsObjects.POSITION;
        when(positionRepository.findByAddressIgnoreCase(position.getAddress())).thenReturn(null);
        assertThrows(PositionsNotFoundException.class, () -> positionService.getPositionByAddress(position.getAddress()));
    }
    @Test
    void getPositionByIdOk() {
        Position position = utilsObjects.POSITION;

        when(positionRepository.findById(position.getId())).thenReturn(Optional.of(position));
        Position result = positionService.getPositionById(position.getId());

        assertEquals(position, result);
        verify(positionRepository, times(1)).findById(eq(position.getId()));
    }
    @Test
    void getPositionByIdPositionsNotFound() {
        Position position = utilsObjects.POSITION;

        when(positionRepository.findById(position.getId())).thenReturn(Optional.empty());
        assertThrows(PositionsNotFoundException.class, () -> positionService.getPositionById(position.getId()));
    }
    @Test
    void getAllPositionByStudioIdOk() {
        Position position1 = utilsObjects.POSITION;
        Position position2 = utilsObjects.POSITION;
        position2.setId(position1.getId() + 1);

        when(positionRepository.findByStudio_Id(utilsObjects.STUDIO.getId())).thenReturn(List.of(position1, position2));
        List<Position> result = positionService.getAllPositionByStudioId(utilsObjects.STUDIO.getId());

        assertEquals(List.of(position1, position2), result);
        verify(positionRepository, times(1)).findByStudio_Id(eq(utilsObjects.STUDIO.getId()));
    }
    @Test
    void getAllPositionByStudioIdPositionsNotFound() {
        when(positionRepository.findByStudio_Id(utilsObjects.STUDIO.getId())).thenReturn(null);
        assertThrows(PositionsNotFoundException.class, () -> positionService.getAllPositionByStudioId(utilsObjects.STUDIO.getId()));
    }
    @Test
    void addPositionToStudio() {
        Studio studio = utilsObjects.STUDIO;
        Position position = utilsObjects.POSITION;

        PositionDTO positionDTO = new PositionDTO(
                studio.getId(),
                position.getHours()
        );
        position.setAddress(position.getAddress());

        when(studioService.getStudioById(studio.getId())).thenReturn(studio);
        when(positionRepository.save(any())).thenReturn(position);
        Position result = positionService.addPositionToStudio(positionDTO);

        assertEquals(position, result);
        verify(positionRepository, times(1)).save(any());
    }
}
